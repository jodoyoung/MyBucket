module com.anajoa.grape.server {
    requires com.anajoa.grape.domain;

    requires jdk.unsupported;

    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.webflux;

    requires org.reactivestreams;
    requires reactor.core;
    requires reactor.netty;
    requires io.netty.codec.http;

    requires org.slf4j;
    requires com.fasterxml.classmate;

    opens com.anajoa.grape.server to spring.core, spring.beans, spring.context;
    opens com.anajoa.grape.server.ui.controller to spring.beans, spring.webflux;
    opens com.anajoa.grape.server.ui.view to rocker.runtime;
}