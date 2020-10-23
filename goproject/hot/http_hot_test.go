package hot

import (
	_ "encoding/json"
	"fmt"
	"testing"
)

func TestOnce(t *testing.T) {
	for i := 0; i < 3; i++ {
		println(i)
		once.Do(func() {
			fmt.Println("create client")
		})
	}

}

func a() {
	if 1 == 1 {
		defer func() { fmt.Println("out") }()

		fmt.Println("in")
	}

	defer func() { fmt.Println("out out") }()

	fmt.Println("out in")
}

func TestDo(t *testing.T) {
	a()
}

//
//func Test11(t *testing.T) {
//	msg := "[\"h1\",\"h2\",\"h3\"]"
//	var j []string
//	err := json.Unmarshal([]byte(msg), &j)
//	fmt.Println("********************")
//	if err != nil {
//		fmt.Println(err)
//	}
//
//	redis, _ := GetFromRedis(j)
//
//	message := make([]string, len(j))
//	for i := range message {
//		if redis[i] != nil {
//			message[i] = redis[i].(string)
//		}
//	}
//
//	marshal, err := json.Marshal(message)
//	if err != nil {
//		fmt.Println(err)
//	}
//
//	str := string(marshal)
//
//	fmt.Println(str)
//}
