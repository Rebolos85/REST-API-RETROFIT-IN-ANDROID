package com.example.sqlapipractice.service;

import com.example.sqlapipractice.model.RequestUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("/api/person")
    Call<RequestUser> createUserRequest(@Body RequestUser requestUser);

    @GET("/api/person")
    Call<List<RequestUser>> getRequestData();

}
