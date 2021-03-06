package io.grpc.examples.grpcredis;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.2)",
    comments = "Source: grpcredis.proto")
public final class RedisGrpc {

  private RedisGrpc() {}

  public static final String SERVICE_NAME = "grpcredis.Redis";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.grpcredis.MGetRequest,
      io.grpc.examples.grpcredis.MGetReply> getMGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MGet",
      requestType = io.grpc.examples.grpcredis.MGetRequest.class,
      responseType = io.grpc.examples.grpcredis.MGetReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.grpcredis.MGetRequest,
      io.grpc.examples.grpcredis.MGetReply> getMGetMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.grpcredis.MGetRequest, io.grpc.examples.grpcredis.MGetReply> getMGetMethod;
    if ((getMGetMethod = RedisGrpc.getMGetMethod) == null) {
      synchronized (RedisGrpc.class) {
        if ((getMGetMethod = RedisGrpc.getMGetMethod) == null) {
          RedisGrpc.getMGetMethod = getMGetMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.grpcredis.MGetRequest, io.grpc.examples.grpcredis.MGetReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MGet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.grpcredis.MGetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.grpcredis.MGetReply.getDefaultInstance()))
              .setSchemaDescriptor(new RedisMethodDescriptorSupplier("MGet"))
              .build();
        }
      }
    }
    return getMGetMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RedisStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RedisStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RedisStub>() {
        @java.lang.Override
        public RedisStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RedisStub(channel, callOptions);
        }
      };
    return RedisStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RedisBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RedisBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RedisBlockingStub>() {
        @java.lang.Override
        public RedisBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RedisBlockingStub(channel, callOptions);
        }
      };
    return RedisBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RedisFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RedisFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RedisFutureStub>() {
        @java.lang.Override
        public RedisFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RedisFutureStub(channel, callOptions);
        }
      };
    return RedisFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class RedisImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void mGet(io.grpc.examples.grpcredis.MGetRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.grpcredis.MGetReply> responseObserver) {
      asyncUnimplementedUnaryCall(getMGetMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.grpc.examples.grpcredis.MGetRequest,
                io.grpc.examples.grpcredis.MGetReply>(
                  this, METHODID_MGET)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class RedisStub extends io.grpc.stub.AbstractAsyncStub<RedisStub> {
    private RedisStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RedisStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RedisStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void mGet(io.grpc.examples.grpcredis.MGetRequest request,
        io.grpc.stub.StreamObserver<io.grpc.examples.grpcredis.MGetReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMGetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class RedisBlockingStub extends io.grpc.stub.AbstractBlockingStub<RedisBlockingStub> {
    private RedisBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RedisBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RedisBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public io.grpc.examples.grpcredis.MGetReply mGet(io.grpc.examples.grpcredis.MGetRequest request) {
      return blockingUnaryCall(
          getChannel(), getMGetMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class RedisFutureStub extends io.grpc.stub.AbstractFutureStub<RedisFutureStub> {
    private RedisFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RedisFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RedisFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.grpcredis.MGetReply> mGet(
        io.grpc.examples.grpcredis.MGetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMGetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MGET = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RedisImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RedisImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MGET:
          serviceImpl.mGet((io.grpc.examples.grpcredis.MGetRequest) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.grpcredis.MGetReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RedisBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RedisBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.grpcredis.RedisProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Redis");
    }
  }

  private static final class RedisFileDescriptorSupplier
      extends RedisBaseDescriptorSupplier {
    RedisFileDescriptorSupplier() {}
  }

  private static final class RedisMethodDescriptorSupplier
      extends RedisBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RedisMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RedisGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RedisFileDescriptorSupplier())
              .addMethod(getMGetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
