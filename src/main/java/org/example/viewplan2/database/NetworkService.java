package org.example.viewplan2.database;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;

public class NetworkService {

    private static String BASE_URL = "http://192.168.0.107:8080";
    private final Retrofit retrofit;
    private static NetworkService networkService;


    public NetworkService() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static NetworkService getInctance(){
        if (networkService == null){
            networkService = new NetworkService();
        }
        return networkService;
    }

    public JSONPlaceHolderAPI getApi(){
        return retrofit.create(JSONPlaceHolderAPI.class);
    }


}
