package com.example.superheroapp.network

import com.example.superheroapp.model.Superhero
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //Pengambilan data
    @GET("api.php/50a6edb5bf443ea34bbe5f647d3cedd9/621")
    fun getSuperhero(): Call<Superhero>
}