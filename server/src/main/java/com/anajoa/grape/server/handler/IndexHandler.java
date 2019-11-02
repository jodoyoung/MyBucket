package com.anajoa.grape.server.handler;

import lombok.experimental.UtilityClass;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

import java.util.function.BiFunction;

@UtilityClass
public class IndexHandler {

    public BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> index
            = (request, response)
            -> response.sendString(Mono.just("Hello World!"));

}
