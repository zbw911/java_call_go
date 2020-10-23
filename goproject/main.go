package main

import (
	"C"
	"encoding/json"
	"fmt"
	_ "go.uber.org/automaxprocs"
	"runtime/debug"
	"testcgo/hot"
)

//export CreateRedisClient
func CreateRedisClient(nodes *C.char, password *C.char) {
	hot.CreateRedisClient(C.GoString(nodes), C.GoString(password))
}

//export MGet
func MGet(keys *C.char) *C.char {
	defer func() {
		if err := recover(); err != nil {
			fmt.Printf("Runtime panic caught: %v\n", err)
			debug.PrintStack()
		}
	}()
	//fmt.Println(C.GoString(keys), C.GoString(nodes), C.GoString(password))
	var j []string
	err := json.Unmarshal([]byte(C.GoString(keys)), &j)
	if err != nil {
		fmt.Println("json Unmarshal error:")
		fmt.Println(err)
		return C.CString(err.Error())
	}

	redis, err := hot.GetFromRedis(j)
	if err != nil {
		fmt.Println("GetFromRedis", err)
		//os.Exit(-5)
		return C.CString(err.Error())
	}
	message := make([]string, len(j))
	if len(j) != len(redis) {
		fmt.Println("传入与返回不相同，", keys, len(j), len(redis))
	} else {
		for i := range message {
			if redis[i] != nil {
				message[i] = redis[i].(string)
			}
		}
	}

	marshal, err := json.Marshal(message)
	if err != nil {
		fmt.Println(err)
		return C.CString(err.Error())
	}

	str := string(marshal)

	return C.CString(str)
}

func main() {
}
