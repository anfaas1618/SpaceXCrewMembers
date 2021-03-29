package com.anfaas.assignment.Network;

import com.anfaas.assignment.Modal.CrewDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DetailsApi {

    @GET("/v4/crew")
    Call<List<CrewDetail>> getAllCrewDetails();
}
