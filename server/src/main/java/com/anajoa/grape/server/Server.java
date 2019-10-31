package com.anajoa.grape.server;

import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class Server {

    public static void main(String[] args) {
        // required add jvm options
        // --add-opens java.base/jdk.internal.misc=io.netty.common --add-opens java.base/java.nio=io.netty.common
        System.setProperty("io.netty.tryReflectionSetAccessible", "true");

        DisposableServer server =
                HttpServer.create()
                        .port(80)
                        .compress(true)
                        .route(routes -> {
                            routes.file("/static/{path}", "/data/SAM_2568.JPG");
                        })
                        .bindNow();

        server.onDispose()
                .block();
    }
}
