package com.jmdev.marvelheroes.data.models.comics

import com.google.gson.annotations.SerializedName


data class Creators (

	@SerializedName("available") val available : Int,
	@SerializedName("collectionURI") val collectionURI : String,
	@SerializedName("items") val items : List<Items>,
	@SerializedName("returned") val returned : Int
)