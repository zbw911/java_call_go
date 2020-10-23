package cache

import (
	"context"
	"fmt"
	"strconv"
	"testing"
	"time"
)

var keys []string
var client *Client

func TestMain(t *testing.M) {
	client, _ = NewClusterCustomClient([]string{":7000"}, "passwd123")
	fmt.Println(client.Ping(context.Background()))
	setRandomKeys(100)
	t.Run()
}

func TestSlot(t *testing.T) {

	var data = map[int][]int{}
	for i := 0; i < 16384; i++ {
		l := client.findSlot(strconv.Itoa(i))
		if _, ok := data[l]; !ok {
			data[l] = []int{}
		}

		data[l] = append(data[l], i)
	}

	for _, ints := range data {
		fmt.Println(ints)
	}

}

func TestBatGet(t *testing.T) {
	keys := make([]string, 100)
	for i := 0; i < 100; i++ {
		key := "key:" + strconv.Itoa(i)
		client.Set(context.Background(), key, strconv.Itoa(i), time.Hour)
		keys[i] = key
	}

	mget, err := client.MultiMget(context.Background(), keys...)

	if err != nil {
		fmt.Println(err)
		return
	}

	for i, i2 := range mget {
		if i2 != strconv.Itoa(i) {
			print("error")
		}
	}

	fmt.Println(mget)

	fmt.Println("OK")
}

func TestM(t *testing.T) {

	mget, _ := client.MultiMget(context.Background(), "key:46",
		"key:51",
		"key:20",
		"key:4",
		"key:33",
		"key:55",
		"key:59",
		"key:15",
		"key:19",
		"key:11",
		"key:24",
		"key:28",
		"key:42",
		"key:37",
		"key:0")

	fmt.Println(mget)
}

func setRandomKeys(n int) {
	keys = make([]string, n)
	//r := rand.New(rand.NewSource(time.Now().UnixNano()))
	//for k, _ := range keys {
	//	keys[k] = strconv.FormatInt(r.Int63n(100000), 10)
	//}

	for i := 0; i < n; i++ {
		keys[i] = strconv.Itoa(i)
	}
}

func TestSlots(t *testing.T) {
	slots := client.ClusterSlots(context.Background())
	result, _ := slots.Result()
	for _, x := range result {
		fmt.Println(x)
	}

	ticker := time.NewTicker(time.Second * 5)
	go func() {
		for _ = range ticker.C {
			slots := client.ClusterSlots(context.Background())
			result, _ := slots.Result()
			for _, x := range result {
				fmt.Println(x)
			}
			fmt.Println(time.Now())
		}
	}()

	time.Sleep(time.Minute)
}

func TestGetAndSet(t *testing.T) {
	_, err := client.MultiMget(context.Background(), "key1", "key2", "key3", "key4")
	if err != nil {
		t.Error(err)
		return
	}
}

//func TestClient_MultiMset(t *testing.T) {
//	kv := map[string]interface{}{}
//	for _, v := range keys {
//		kv[v] = v
//	}
//
//	if err := client.MultiMset(context.Background(), kv); err != nil {
//		t.Error(err)
//	}
//}

func TestMultiMget(t *testing.T) {
	result, err := client.MultiMget(context.Background(), keys...)
	if err != nil {
		t.Error(err)
		return
	}
	for k, v := range result {
		if v.(string) != keys[k] {
			t.Error("k != v")
		}
	}
	t.Log("keys", keys)
	t.Log("result", result)
}
