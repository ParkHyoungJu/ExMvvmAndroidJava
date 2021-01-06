package com.example.exmvvmjava.apiclient.model;

import com.google.gson.annotations.SerializedName;

public class ExampleResponse {

    @SerializedName("EX")
    private String ex;

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }
}
