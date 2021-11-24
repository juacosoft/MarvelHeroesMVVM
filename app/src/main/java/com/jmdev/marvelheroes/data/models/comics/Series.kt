package com.jmdev.marvelheroes.data.models.comics

import com.google.gson.annotations.SerializedName


data class Series (

	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("name") val name : String
)