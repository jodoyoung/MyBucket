package com.anajoa.grape.server;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public final class GrapeConfig {

    private static GrapeConfig instance;

    private static final Path CONFIG_FILE_PATH = Paths.get(GrapeEnvironment.CONFIG_FILE_PATH);

    private Admin admin;

    private GrapeConfig() {}

    public static Admin getAdmin() {
        return instance.admin;
    }

    public static synchronized Mono<GrapeConfig> create(Admin admin) {
        try {
            Files.createFile(CONFIG_FILE_PATH);

            GrapeConfig config = new GrapeConfig();
            config.admin = admin;

            instance = config;

            return write(config);
        } catch (IOException e) {
            log.error("config file creation failed.", e);
            return Mono.empty();
        }
    }

    private static synchronized Mono<GrapeConfig> write(GrapeConfig config) {
        try {
            Files.writeString(CONFIG_FILE_PATH, JsonUtil.toJsonString(config));
            return Mono.just(config);
        } catch (IOException e) {
            log.error("config file write failed.", e);
            return Mono.empty();
        }
    }

}
