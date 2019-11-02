package com.anajoa.grape.server;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class Admin {

    private String id;
    private String password;

    private Admin() {}

    public static Admin of(String id, String password) {
        Admin admin = new Admin();
        admin.id = id;
        admin.password = password;
        return admin;
    }
}
