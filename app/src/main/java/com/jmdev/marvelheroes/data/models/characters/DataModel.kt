package com.jmdev.marvelheroes.data.models.characters

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("total") val total : Int,
    @SerializedName("count") val count : Int,
    @SerializedName("results") val characters : List<CharacterModel>
)
