package com.arthurlamberti.videoplataform.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE) //acesso privado para ninguem conseguir instanciar
public class IdUtils {


    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }

}
