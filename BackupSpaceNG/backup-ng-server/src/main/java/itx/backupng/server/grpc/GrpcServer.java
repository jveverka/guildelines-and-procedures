package itx.backupng.server.grpc;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import itx.backupng.server.controller.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class GrpcServer {

    final private static Logger LOG = LoggerFactory.getLogger(GrpcServer.class);

    private Server server;
    private int port;
    private String host;
    private Services services;

    public GrpcServer(String host, int port, Services services) {
        this.host = host;
        this.port = port;
        this.services = services;
    }

    public void start() throws IOException {
        server = NettyServerBuilder.forAddress(new InetSocketAddress(host, port))
                .addService(new BackupServiceImpl(services))
                .build()
                .start();
        LOG.info("Server started, listening on {}:{}", host, port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class BackupServiceImpl extends BackupServiceGrpc.BackupServiceImplBase {

        private Services services;

        public BackupServiceImpl(Services services) {
            this.services = services;
        }

        @Override
        public void getVersion(EmptyMessage request, StreamObserver<VersionReply> responseObserver) {
            VersionReply versionReply = VersionReply.newBuilder()
                    .setVersion("1.0.0")
                    .build();
            responseObserver.onNext(versionReply);
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<WrapperRequest> dataChannel(StreamObserver<WrapperResponse> responseObserver) {
            return new ServerDataChannelStreamObserver<>(responseObserver, services);
        }

    }

}
