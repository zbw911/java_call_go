// 可以设置pool、timeout
// client可以当作缓存也可以当作队列，不影响同时使用
package cache

import (
	"context"
	"fmt"
	"github.com/go-redis/redis/v8"
	"sync"
	"time"
	_ "unsafe"
)

type Client struct {
	*redis.ClusterClient
}

func NewClusterCustomClient(addrs []string, password string) (r *Client, err error) {
	r = &Client{ClusterClient: redis.NewClusterClient(&redis.ClusterOptions{
		Addrs:       addrs,
		PoolSize:    100,
		MaxRetries:  3,
		ReadTimeout: time.Second * 10,
		Password:    password,
		DialTimeout: time.Second * 3,
	})}

	return r, r.Ping(context.Background()).Err()
}

type slot struct {
	hashSlot int
	position []int
	keys     []string
	result   []interface{}
	err      error
}

//go:linkname hashtagSlot  github.com/go-redis/redis/v8/internal/hashtag.Slot
func hashtagSlot(key string) int

func (c *Client) MultiMget(ctx context.Context, keys ...string) (result []interface{}, err error) {
	if len(keys) == 1 {
		return c.MGet(ctx, keys...).Result()
	}
	var slots = map[int]*slot{}
	for k, v := range keys {
		hashSlot := hashtagSlot(v)
		if _, ok := slots[hashSlot]; !ok {
			slots[hashSlot] = &slot{
				hashSlot: hashSlot,
				position: []int{},
				keys:     []string{},
				result:   nil,
			}
		}

		slots[hashSlot].keys = append(slots[hashSlot].keys, v)
		slots[hashSlot].position = append(slots[hashSlot].position, k)
	}

	var group sync.WaitGroup
	group.Add(len(slots))

	for _, v := range slots {
		go func(v *slot) {
			result, err := c.MGet(ctx, v.keys...).Result()
			v.err = err
			v.result = result
			group.Done()
		}(v)
	}

	group.Wait()

	result = make([]interface{}, len(keys))
	for _, slot := range slots {
		if slot.err != nil {
			fmt.Println("on error:", c.Options().Addrs, c.Options().Password)
			fmt.Println(slot.err)
			return nil, slot.err
		}

		for k, v := range slot.result {
			result[slot.position[k]] = v
		}
	}

	return result, err
}

var mu sync.Mutex

var clusterSlot []redis.ClusterSlot = nil

func (c *Client) getClusterSlots() *[]redis.ClusterSlot {

	if clusterSlot == nil {
		mu.Lock()
		defer mu.Unlock()
		slot, err := c.ClusterSlots(context.Background()).Result()
		if err != nil {
			return nil
		}
		clusterSlot = slot
	}

	return &clusterSlot

}

func (c *Client) findSlot(key string) int {
	hashSlot := hashtagSlot(key)
	slots := c.getClusterSlots()
	if slots == nil {
		fmt.Println("getClusterSlots is nil")
		return hashSlot
	}
	for _, r := range *slots {
		if r.Start <= hashSlot && hashSlot <= r.End {
			start := r.Start
			return start
		}
	}
	return -1
}

////
//func (c *Client) MultiMset(ctx context.Context, kv map[string]interface{}) error {
//	var slots = make(map[*redis.ClusterSlot][]interface{})
//	for k, v := range kv {
//		inSlot := findInSlots(c, k)
//		if _, ok := slots[inSlot]; !ok {
//			slots[inSlot] = make([]interface{}, 0)
//		}
//
//		slots[inSlot] = append(slots[inSlot], k, v)
//	}
//
//	var done int
//	var doneCh = make(chan bool, 0)
//	var errCh = make(chan error, 0)
//	ctx, cancel := context.WithCancel(context.Background())
//	defer cancel()
//
//	for k := range slots {
//		go func(v []interface{}) {
//			_, err := c.MSet(ctx, v...).Result()
//			if err != nil {
//				errCh <- err
//			}
//			doneCh <- true
//		}(slots[k])
//	}
//
//	for {
//		select {
//		case <-ctx.Done():
//			return ctx.Err()
//		case err := <-errCh:
//			return err
//		case <-doneCh:
//			done++
//			if done == len(slots)-1 {
//				return nil
//			}
//		}
//	}
//
//}
