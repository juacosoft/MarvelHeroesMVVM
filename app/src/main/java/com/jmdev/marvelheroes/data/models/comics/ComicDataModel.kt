package com.jmdev.marvelheroes.data.models.comics

import com.google.gson.annotations.SerializedName

data class ComicDataModel (

	@SerializedName("offset") val offset : Int,
	@SerializedName("limit") val limit : Int,
	@SerializedName("total") val total : Int,
	@SerializedName("count") val count : Int,
	@SerializedName("results") val comics : List<ComicsModel>
)