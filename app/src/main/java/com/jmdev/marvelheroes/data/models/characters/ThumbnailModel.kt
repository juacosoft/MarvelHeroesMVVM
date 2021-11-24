package com.jmdev.marvelheroes.data.models.characters

import com.google.gson.annotations.SerializedName

data class ThumbnailModel(
    @SerializedName("path") val path : String,
    @SerializedName("extension") val extension : String
)
