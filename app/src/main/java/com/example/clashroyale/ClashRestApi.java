package com.example.clashroyale;

import com.example.clashroyale.model.RestClashResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClashRestApi {

    //On définit notre interface
    //Avec une méthod getListPokemon() qui retourne un objet
    //Call<RestPokemonResponse>
    @GET("clashApi.json")
    Call<RestClashResponse> getListClashAPI();

//    @GET("/abilities")
//    Call<RestPokemonResponse> getListAbilities();


}
