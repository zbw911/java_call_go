// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpcredis.proto

package io.grpc.examples.grpcredis;

public interface MGetReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:grpcredis.MGetReply)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string message = 1;</code>
   */
  java.util.List<java.lang.String>
      getMessageList();
  /**
   * <code>repeated string message = 1;</code>
   */
  int getMessageCount();
  /**
   * <code>repeated string message = 1;</code>
   */
  java.lang.String getMessage(int index);
  /**
   * <code>repeated string message = 1;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes(int index);
}