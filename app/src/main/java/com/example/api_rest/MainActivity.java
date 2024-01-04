package com.example.api_rest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import com.example.api_rest.io.ApiService;
import com.example.api_rest.io.Café;
import com.example.api_rest.io.ChuckNorris;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    private Button btnChuckNorris;
    private Button btnCoffee;
    private ImageView imageView;
    private TextView textResult;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChuckNorris = findViewById(R.id.btnChuckNorris);
        btnCoffee = findViewById(R.id.btnCafe);
        imageView = findViewById(R.id.imageView);
        textResult = findViewById(R.id.textResult);

        btnChuckNorris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChuckNorrisJoke();
            }
        });

        btnCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCoffeeInfo();
            }
        });
    }

    private void getChuckNorrisJoke() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<ChuckNorris> call = apiService.getRandomJoke();

        call.enqueue(new retrofit2.Callback<ChuckNorris>() {
            @Override
            public void onResponse(Call<ChuckNorris> call, Response<ChuckNorris> response) {
                if (response.isSuccessful()) {
                    ChuckNorris joke = response.body();
                    if (joke != null) {
                        textResult.setText(joke.getValue());
                    }
                } else {
                    textResult.setText("Error al obtener el chiste ");
                }
            }

            @Override
            public void onFailure(Call<ChuckNorris> call, Throwable t) {
                textResult.setText("Error al obtener el chiste");
            }
        });
    }

    private void getCoffeeInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coffee.alexflipnote.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        Call<Café> call = apiService.getCoffeeInfo();

        call.enqueue(new retrofit2.Callback<Café>() {
            @Override
            public void onResponse(Call<Café> call, Response<Café> response) {
                if (response.isSuccessful()) {
                    Café café = response.body();
                    if (café != null) {
                        textResult.setText(café.getTitle() + "\n" + café.getDescription());

                        // Cargar la imagen usando Picasso
                        Picasso.get()
                                .load(café.getImageUrl())
                                .into(imageView);
                    }
                } else {
                    textResult.setText("Error al obtener información del café. Código de error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Café> call, Throwable t) {
                textResult.setText("Error al obtener información del café: " + t.getMessage());
            }
        });
    }

}