// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpcredis.proto

package io.grpc.examples.grpcredis;

public final class RedisProto {
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_grpcredis_MGetRequest_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_grpcredis_MGetRequest_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_grpcredis_MGetReply_descriptor;
    static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_grpcredis_MGetReply_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\017grpcredis.proto\022\tgrpcredis\"\033\n\013MGetRequ" +
                        "est\022\014\n\004keys\030\001 \003(\t\"\034\n\tMGetReply\022\017\n\007messag" +
                        "e\030\001 \003(\t2?\n\005Redis\0226\n\004MGet\022\026.grpcredis.MGe" +
                        "tRequest\032\024.grpcredis.MGetReply\"\000B<\n\032io.g" +
                        "rpc.examples.grpcredisB\nRedisProtoP\001Z\020go" +
                        "test/grpcredisb\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
        internal_static_grpcredis_MGetRequest_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_grpcredis_MGetRequest_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_grpcredis_MGetRequest_descriptor,
                new java.lang.String[]{"Keys",});
        internal_static_grpcredis_MGetReply_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_grpcredis_MGetReply_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_grpcredis_MGetReply_descriptor,
                new java.lang.String[]{"Message",});
    }
    private RedisProto() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    // @@protoc_insertion_point(outer_class_scope)
}
