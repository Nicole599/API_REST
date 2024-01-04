package com.example.api_rest.io;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("jokes/random")
    Call<ChuckNorris> getRandomJoke();

    @GET("coffee")
    Call<Café> getCoffeeInfo();

}
