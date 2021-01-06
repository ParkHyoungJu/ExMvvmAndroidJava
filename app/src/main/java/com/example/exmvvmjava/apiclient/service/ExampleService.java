package com.example.exmvvmjava.apiclient.service;

import com.example.exmvvmjava.apiclient.model.ExampleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ExampleService {

    @GET("/example")
    public Call<ExampleResponse> ex(
            @Header("X-Embarcadero-Session-Token") String sessionToken,
            @Path("user_id") String userId);

}
