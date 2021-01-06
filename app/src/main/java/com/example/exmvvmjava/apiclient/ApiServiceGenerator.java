package com.example.exmvvmjava.apiclient;

import android.os.Build;

import com.example.exmvvmjava.BuildConfig;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceGenerator {

    private static ApiServiceGenerator INSTANCE;

    public static ApiServiceGenerator getInstance(){
        if(INSTANCE == null) INSTANCE = new ApiServiceGenerator();
        return INSTANCE;
    }

    public ApiServiceGenerator(){}

    public <S> S createService( Class<S> serviceClass ) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BASIC:HttpLoggingInterceptor.Level.NONE);
        httpClient.addInterceptor(loggingInterceptor);

        // Accept Language Header 추가
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                String locale = Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
                Request request = original.newBuilder()
                        // .header("Accept-Language",  Locale.getDefault().getLanguage())
                        .header("Accept-Language",  locale)
                        .build();

                return chain.proceed(request);
            }
        });

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}
