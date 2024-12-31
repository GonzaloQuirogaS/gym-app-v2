package com.microservice.client.util.constant;

public class PathConstants {

    public static final String API_V2_CLIENTS = "/api/v2/clients";
    public static final String SAVE = "/save";
    public static final String GET_BY_ID = "/{id}";
    public static final String UPDATE = "/update/{id}";
    public static final String DELETE = "/delete/{id}";
    public static final String SEARCH_BY_ACTIVITY_ID = "/search-by-activity/{id}";
    public static final String SET_ACTIVITY_TO_CLIENT_BY_ID = "/set-activity/client/{idClient}/activity/{idActivity}";
    public static final String DELETE_CLIENT_ACTIVITY = "/delete-activity/client/{idClient}/activity/{idActivity}";
}
