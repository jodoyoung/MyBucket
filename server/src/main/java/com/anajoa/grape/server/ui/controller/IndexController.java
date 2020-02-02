package com.anajoa.grape.server.ui.controller;

import com.anajoa.grape.server.ui.view.Index;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @GetMapping("/get")
    public Mono<String> test() {
        return Mono.just(Index.template("World").render().toString());
    }
}
