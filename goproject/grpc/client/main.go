/*
 *
 * Copyright 2015 gRPC authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

// Package main implements a client for Greeter service.
package main

import (
	"context"
	"fmt"
	"google.golang.org/grpc"
	"log"
	"os"
	pb "testcgo/grpc/grpcredis"
	"time"
)

const (
	address = "localhost:7777"
)

//hot:club:31614188:pv hot:club:89771544:pv hot:club:89648779:pv hot:club:89394578:pv hot:club:89583908:pv hot:club:83852571:pv hot:club:89584798:pv hot:club:79776138:pv hot:club:81961072:pv hot:club:85900967:pv hot:club:89752311:pv hot:club:81414876:pv hot:club:89746706:pv hot:club:83504175:pv hot:club:82695259:pv hot:club:81075423:pv hot:club:76177350:pv hot:club:57031505:pv hot:club:48341173:pv hot:club:82686221:pv

func main() {
	currentTime := time.Now()
	for i := 0; i < 10; i++ {
		getDatas(1000)
	}
	fmt.Println(time.Now().Sub(currentTime))
}

func getDatas(count int) {
	// Set up a connection to the server.
	conn, err := grpc.Dial(address, grpc.WithInsecure(), grpc.WithBlock())

	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()

	// Contact the server and print out its response.
	keys := []string{"1", "2"}
	if len(os.Args) > 1 {
		keys = os.Args[1:]
		log.Println(keys)
	}
	ctx, cancel := context.WithTimeout(context.Background(), time.Second*1000)
	defer cancel()
	c := pb.NewRedisClient(conn)

	for i := 0; i < count; i++ {
		r, err := c.MGet(ctx, &pb.MGetRequest{Keys: keys})
		if err != nil {
			log.Fatalf("could not greet: %v", err)
		}
		log.Printf("Greeting: %s", r.GetMessage())
		log.Printf("Greeting: %v", len(r.GetMessage()))
	}

}
