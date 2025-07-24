package br.rafael.simple_restassured_project.core;

import io.restassured.http.ContentType;

public class Constants {

    public static final String APP_BASE_URL = "https://dummyjson.com";

    public static final ContentType APP_CONTENT_TYPE = ContentType.JSON;
    public static final long MAX_TIMEOUT = 1000L;

    private Constants() {}


}