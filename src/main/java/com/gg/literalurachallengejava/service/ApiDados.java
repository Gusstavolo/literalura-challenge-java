package com.gg.literalurachallengejava.service;

public interface ApiDados {

        <T> T obterDados(String json, Class <T> classe);

}
