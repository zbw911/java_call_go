// Copyright 2015 gRPC authors.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.25.0
// 	protoc        v3.4.0
// source: grpcredis.proto

package grpcredis

import (
	context "context"
	proto "github.com/golang/protobuf/proto"
	grpc "google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

// This is a compile-time assertion that a sufficiently up-to-date version
// of the legacy proto package is being used.
const _ = proto.ProtoPackageIsVersion4

// The request message containing the user's name.
type MGetRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Keys []string `protobuf:"bytes,1,rep,name=keys,proto3" json:"keys,omitempty"`
}

func (x *MGetRequest) Reset() {
	*x = MGetRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_grpcredis_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *MGetRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*MGetRequest) ProtoMessage() {}

func (x *MGetRequest) ProtoReflect() protoreflect.Message {
	mi := &file_grpcredis_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use MGetRequest.ProtoReflect.Descriptor instead.
func (*MGetRequest) Descriptor() ([]byte, []int) {
	return file_grpcredis_proto_rawDescGZIP(), []int{0}
}

func (x *MGetRequest) GetKeys() []string {
	if x != nil {
		return x.Keys
	}
	return nil
}

// The response message containing the greetings
type MGetReply struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Message []string `protobuf:"bytes,1,rep,name=message,proto3" json:"message,omitempty"`
}

func (x *MGetReply) Reset() {
	*x = MGetReply{}
	if protoimpl.UnsafeEnabled {
		mi := &file_grpcredis_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *MGetReply) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*MGetReply) ProtoMessage() {}

func (x *MGetReply) ProtoReflect() protoreflect.Message {
	mi := &file_grpcredis_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use MGetReply.ProtoReflect.Descriptor instead.
func (*MGetReply) Descriptor() ([]byte, []int) {
	return file_grpcredis_proto_rawDescGZIP(), []int{1}
}

func (x *MGetReply) GetMessage() []string {
	if x != nil {
		return x.Message
	}
	return nil
}

var File_grpcredis_proto protoreflect.FileDescriptor

var file_grpcredis_proto_rawDesc = []byte{
	0x0a, 0x0f, 0x67, 0x72, 0x70, 0x63, 0x72, 0x65, 0x64, 0x69, 0x73, 0x2e, 0x70, 0x72, 0x6f, 0x74,
	0x6f, 0x12, 0x09, 0x67, 0x72, 0x70, 0x63, 0x72, 0x65, 0x64, 0x69, 0x73, 0x22, 0x21, 0x0a, 0x0b,
	0x4d, 0x47, 0x65, 0x74, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x12, 0x12, 0x0a, 0x04, 0x6b,
	0x65, 0x79, 0x73, 0x18, 0x01, 0x20, 0x03, 0x28, 0x09, 0x52, 0x04, 0x6b, 0x65, 0x79, 0x73, 0x22,
	0x25, 0x0a, 0x09, 0x4d, 0x47, 0x65, 0x74, 0x52, 0x65, 0x70, 0x6c, 0x79, 0x12, 0x18, 0x0a, 0x07,
	0x6d, 0x65, 0x73, 0x73, 0x61, 0x67, 0x65, 0x18, 0x01, 0x20, 0x03, 0x28, 0x09, 0x52, 0x07, 0x6d,
	0x65, 0x73, 0x73, 0x61, 0x67, 0x65, 0x32, 0x3f, 0x0a, 0x05, 0x52, 0x65, 0x64, 0x69, 0x73, 0x12,
	0x36, 0x0a, 0x04, 0x4d, 0x47, 0x65, 0x74, 0x12, 0x16, 0x2e, 0x67, 0x72, 0x70, 0x63, 0x72, 0x65,
	0x64, 0x69, 0x73, 0x2e, 0x4d, 0x47, 0x65, 0x74, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x1a,
	0x14, 0x2e, 0x67, 0x72, 0x70, 0x63, 0x72, 0x65, 0x64, 0x69, 0x73, 0x2e, 0x4d, 0x47, 0x65, 0x74,
	0x52, 0x65, 0x70, 0x6c, 0x79, 0x22, 0x00, 0x42, 0x42, 0x0a, 0x1b, 0x69, 0x6f, 0x2e, 0x67, 0x72,
	0x70, 0x63, 0x2e, 0x65, 0x78, 0x61, 0x6d, 0x70, 0x6c, 0x65, 0x73, 0x2e, 0x68, 0x65, 0x6c, 0x6c,
	0x6f, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x42, 0x0f, 0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x57, 0x6f, 0x72,
	0x6c, 0x64, 0x50, 0x72, 0x6f, 0x74, 0x6f, 0x50, 0x01, 0x5a, 0x10, 0x67, 0x6f, 0x74, 0x65, 0x73,
	0x74, 0x2f, 0x67, 0x72, 0x70, 0x63, 0x72, 0x65, 0x64, 0x69, 0x73, 0x62, 0x06, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x33,
}

var (
	file_grpcredis_proto_rawDescOnce sync.Once
	file_grpcredis_proto_rawDescData = file_grpcredis_proto_rawDesc
)

func file_grpcredis_proto_rawDescGZIP() []byte {
	file_grpcredis_proto_rawDescOnce.Do(func() {
		file_grpcredis_proto_rawDescData = protoimpl.X.CompressGZIP(file_grpcredis_proto_rawDescData)
	})
	return file_grpcredis_proto_rawDescData
}

var file_grpcredis_proto_msgTypes = make([]protoimpl.MessageInfo, 2)
var file_grpcredis_proto_goTypes = []interface{}{
	(*MGetRequest)(nil), // 0: grpcredis.MGetRequest
	(*MGetReply)(nil),   // 1: grpcredis.MGetReply
}
var file_grpcredis_proto_depIdxs = []int32{
	0, // 0: grpcredis.Redis.MGet:input_type -> grpcredis.MGetRequest
	1, // 1: grpcredis.Redis.MGet:output_type -> grpcredis.MGetReply
	1, // [1:2] is the sub-list for method output_type
	0, // [0:1] is the sub-list for method input_type
	0, // [0:0] is the sub-list for extension type_name
	0, // [0:0] is the sub-list for extension extendee
	0, // [0:0] is the sub-list for field type_name
}

func init() { file_grpcredis_proto_init() }
func file_grpcredis_proto_init() {
	if File_grpcredis_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_grpcredis_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*MGetRequest); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_grpcredis_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*MGetReply); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
	}
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_grpcredis_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   2,
			NumExtensions: 0,
			NumServices:   1,
		},
		GoTypes:           file_grpcredis_proto_goTypes,
		DependencyIndexes: file_grpcredis_proto_depIdxs,
		MessageInfos:      file_grpcredis_proto_msgTypes,
	}.Build()
	File_grpcredis_proto = out.File
	file_grpcredis_proto_rawDesc = nil
	file_grpcredis_proto_goTypes = nil
	file_grpcredis_proto_depIdxs = nil
}

// Reference imports to suppress errors if they are not otherwise used.
var _ context.Context
var _ grpc.ClientConnInterface

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
const _ = grpc.SupportPackageIsVersion6

// RedisClient is the client API for Redis service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://godoc.org/google.golang.org/grpc#ClientConn.NewStream.
type RedisClient interface {
	// Sends a greeting
	MGet(ctx context.Context, in *MGetRequest, opts ...grpc.CallOption) (*MGetReply, error)
}

type redisClient struct {
	cc grpc.ClientConnInterface
}

func NewRedisClient(cc grpc.ClientConnInterface) RedisClient {
	return &redisClient{cc}
}

func (c *redisClient) MGet(ctx context.Context, in *MGetRequest, opts ...grpc.CallOption) (*MGetReply, error) {
	out := new(MGetReply)
	err := c.cc.Invoke(ctx, "/grpcredis.Redis/MGet", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

// RedisServer is the server API for Redis service.
type RedisServer interface {
	// Sends a greeting
	MGet(context.Context, *MGetRequest) (*MGetReply, error)
}

// UnimplementedRedisServer can be embedded to have forward compatible implementations.
type UnimplementedRedisServer struct {
}

func (*UnimplementedRedisServer) MGet(context.Context, *MGetRequest) (*MGetReply, error) {
	return nil, status.Errorf(codes.Unimplemented, "method MGet not implemented")
}

func RegisterRedisServer(s *grpc.Server, srv RedisServer) {
	s.RegisterService(&_Redis_serviceDesc, srv)
}

func _Redis_MGet_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(MGetRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(RedisServer).MGet(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/grpcredis.Redis/MGet",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(RedisServer).MGet(ctx, req.(*MGetRequest))
	}
	return interceptor(ctx, in, info, handler)
}

var _Redis_serviceDesc = grpc.ServiceDesc{
	ServiceName: "grpcredis.Redis",
	HandlerType: (*RedisServer)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "MGet",
			Handler:    _Redis_MGet_Handler,
		},
	},
	Streams:  []grpc.StreamDesc{},
	Metadata: "grpcredis.proto",
}
