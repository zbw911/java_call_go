package main

import (
	"context"
	"fmt"
	pb "testcgo/grpc/grpcredis"
	cache "testcgo/redis"

	"google.golang.org/grpc"
	"log"
	"net"
	"strings"
	"time"

	//增加这个引用，可以自动设定CPU个数
	_ "go.uber.org/automaxprocs"
)

const (
	port = ":7777"
	//port = ":50052"
)

// server is used to implement helloworld.GreeterServer.
type server struct {
	pb.UnimplementedRedisServer
}

var client *cache.Client

func GetFromRedis(keys []string) []interface{} {
	if client == nil {
		redislist := "localhost:7000,localhost:7001"
		password := "passwd123"

		c, err := cache.NewClusterCustomClient(strings.Split(redislist, ","), password)
		if err != nil {
			log.Println("getclient", err)
			return nil
		}

		client = c
	}

	result, err := client.MultiMget(context.Background(), keys...)
	if err != nil {
		fmt.Println(err)
	}
	return result
}

func (s *server) MGet(ctx context.Context, in *pb.MGetRequest) (*pb.MGetReply, error) {

	t := time.Now()
	result := GetFromRedis(in.Keys)

	message := make([]string, len(in.Keys))
	for i := range message {
		if result[i] != nil {
			message[i] = result[i].(string)
		}
	}
	ts := time.Since(t)
	if ts > time.Millisecond*2 {
		log.Printf("ts: %v Received: %v ", ts, in)
	}

	return &pb.MGetReply{Message: message}, nil
}

func main() {
	log.Println("server start")
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := grpc.NewServer()
	pb.RegisterRedisServer(s, &server{})
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
