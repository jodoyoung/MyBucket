module com.anajoa.grape.domain {
    exports com.anajoa.grape.domain.drive;

    requires java.desktop;
    requires org.slf4j;
    requires org.apache.commons.io;
    requires imgscalr.lib;
    requires metadata.extractor;
}