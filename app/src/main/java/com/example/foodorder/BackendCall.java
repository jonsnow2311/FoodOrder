package com.example.foodorder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BackendCall {

    @GET("/api/users")
    Call<List<UserModel>> getUsers();

    @POST("api/signup")
    Call<UserModel> creatUser(@Body SignUpModel signUpModel);

    @POST("api/login")
    Call<UserModel> loginUser(@Body LoginModel loginModel);


}
