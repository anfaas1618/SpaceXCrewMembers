package com.anfaas.assignment.Network;

import com.anfaas.assignment.Modal.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("v4/crew")
    Call<List<Crew>> getAllCrew();
}
