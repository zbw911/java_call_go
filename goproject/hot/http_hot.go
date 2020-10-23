package hot

import (
	"context"
	"fmt"
	"log"
	"strings"
	"sync"
	cache "testcgo/redis"
)

var (
	client   *cache.Client
	nodes    string
	password string
	once     sync.Once
)

func CreateRedisClient(n string, p string) {
	nodes = n
	password = p
	fmt.Println("setting client", nodes, password)
}

func getClient() *cache.Client {
	once.Do(func() {
		fmt.Println("create client", nodes, password)
		c, err := cache.NewClusterCustomClient(strings.Split(nodes, ","), password)
		if err != nil {
			log.Println("getclient", err)
		}
		client = c
	})

	return client
}

func GetFromRedis(keys []string) ([]interface{}, error) {
	result, err := getClient().MultiMget(context.Background(), keys...)
	if err != nil {
		fmt.Println(err)
		return result, err
	}
	return result, nil
}
