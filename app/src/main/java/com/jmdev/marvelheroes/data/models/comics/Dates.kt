package com.jmdev.marvelheroes.data.models.comics

import com.google.gson.annotations.SerializedName


data class Dates (

	@SerializedName("type") val type : String,
	@SerializedName("date") val date : String
)