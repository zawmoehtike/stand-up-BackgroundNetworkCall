package com.example.stand_up.networks

import com.example.stand_up.data.networkvos.BreedVO
import com.example.stand_up.data.responses.ResourcesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface JojoCustomerApi {
    @Headers("Content-Type: application/json")
    @GET("/dummy_breed_list")
    fun callDummyBreedList(): Observable<List<BreedVO>>
}