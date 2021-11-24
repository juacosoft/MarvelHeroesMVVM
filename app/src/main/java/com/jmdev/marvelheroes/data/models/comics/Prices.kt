package com.jmdev.marvelheroes.data.models.comics

import com.google.gson.annotations.SerializedName



data class Prices (

	@SerializedName("type") val type : String,
	@SerializedName("price") val price : Int
)