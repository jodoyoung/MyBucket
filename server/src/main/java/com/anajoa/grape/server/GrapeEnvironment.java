package com.anajoa.grape.server;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class GrapeEnvironment {

    public String CONFIG_FILE_PATH = System.getProperty("user.home") + File.separator + ".grape";

}
