package com.example.sqlapipractice.retrofit2;

import com.example.sqlapipractice.service.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /*
         We need to call add Interceptor method in order that we could register an application on OkHttpClient
         Builder
         */
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://spring-api-online-demo.herokuapp.com")
                    // Para ni siya makahandle og any types si JSON
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    // If wala sa client or clientfactory is called OKHTTPClient will be created and used
                    .build();
        }
        return retrofit;

    }


    public static UserService getUserRequestData() {
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

    public static  UserService addUserRequestData() {
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }


}
