package com.jmdev.marvelheroes.data.models.characters

import com.google.gson.annotations.SerializedName


data class CharacterResponseModel (
    @SerializedName("code")val code :Int,
    @SerializedName("status")val status:String,
    @SerializedName("copyright")val copyRight:String,
    @SerializedName("attributionText")val attributionText:String,
    @SerializedName("data")val dataModel: DataModel,
    )