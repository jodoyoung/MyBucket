package com.anajoa.grape.server;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GrapeServerTest {

    @Test
    public void createConfig() {
        // given
        Path configFilePath = Paths.get(GrapeEnvironment.CONFIG_FILE_PATH);

        if(Files.exists(configFilePath)) {
            Assert.assertTrue("config file already exists.", true);
            return;
        }

        // when
        GrapeConfig.create(Admin.of("admin", "admin"));

        // then
        Assert.assertTrue("config file created.", Files.exists(configFilePath));
        Assert.assertEquals(GrapeConfig.getAdmin(), Admin.of("admin", "password"));
    }
}
