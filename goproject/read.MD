    
# windows:
      go build -buildmode=c-shared -o ./bin/win32-x86-64/libgoredis.dll main.go
 #linux:
      go build -buildmode=c-shared -o ./bin/linux-x86-64/liblibgoredis.so main.go
      
      
      
  #本机
      
     wsl go build -buildmode=c-shared -o ../javaproject/src/main/resources/linux-x86-64/liblibgoredis.so main.go
      go build -buildmode=c-shared -o ..\javaproject\src\main\resources\win32-x86-64\libgoredis.dll main.go