package com.jmdev.marvelheroes.data.network



import com.jmdev.marvelheroes.constants.ServerUrls.SERVER_API
import com.jmdev.marvelheroes.constants.ServerUrls.SERVER_HASH
import com.jmdev.marvelheroes.constants.ServerUrls.SERVER_TS
import com.jmdev.marvelheroes.data.models.characters.CharacterResponseModel
import com.jmdev.marvelheroes.data.models.comics.ComicResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesMarveApi {
    @GET("characters?ts=$SERVER_TS&apikey=$SERVER_API&hash=$SERVER_HASH")
    suspend fun getAllCharacters(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ): Response<CharacterResponseModel>

    @GET("characters?ts=$SERVER_TS&apikey=$SERVER_API&hash=$SERVER_HASH")
    suspend fun seacrhWithName(
        @Query("nameStartsWith")searchQuery:String,
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ): Response<CharacterResponseModel>

    @GET("comics?ts=$SERVER_TS&apikey=$SERVER_API&hash=$SERVER_HASH")
    suspend fun getAllComics(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ):Response<ComicResponseModel>
}