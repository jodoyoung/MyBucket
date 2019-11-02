module com.anajoa.grape.server {
    requires jdk.unsupported;
    requires java.desktop;
    requires org.reactivestreams;
    requires reactor.core;
    requires reactor.netty;
    requires slf4j.api;
    requires org.apache.commons.io;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
    requires imgscalr.lib;
    requires metadata.extractor;

    exports com.anajoa.grape.server;
    opens com.anajoa.grape.server to com.fasterxml.jackson.databind;
}