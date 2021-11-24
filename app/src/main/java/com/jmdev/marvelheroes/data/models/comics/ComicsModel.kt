package com.jmdev.marvelheroes.data.models.comics

import com.google.gson.annotations.SerializedName



data class ComicsModel (

	@SerializedName("id") val id : Int,
	//@SerializedName("digitalId") val digitalId : Int,
	@SerializedName("title") val title : String,
	//@SerializedName("issueNumber") val issueNumber : Int,
	//@SerializedName("variantDescription") val variantDescription : String,
	@SerializedName("description") val description : String,
	@SerializedName("modified") val modified : String,
	//@SerializedName("isbn") val isbn : String,
	//@SerializedName("upc") val upc : Int,
	/*@SerializedName("diamondCode") val diamondCode : String,
	@SerializedName("ean") val ean : String,
	@SerializedName("issn") val issn : String,
	@SerializedName("format") val format : String,
	@SerializedName("pageCount") val pageCount : Int,
	@SerializedName("textObjects") val textObjects : List<String>,
	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("urls") val urls : List<Urls>,
	@SerializedName("series") val series : Series,
	@SerializedName("variants") val variants : List<Variants>,
	@SerializedName("collections") val collections : List<String>,
	@SerializedName("collectedIssues") val collectedIssues : List<String>,
	@SerializedName("dates") val dates : List<Dates>,
	@SerializedName("prices") val prices : List<Prices>,*/
	@SerializedName("thumbnail") val thumbnail : Thumbnail,
	/*@SerializedName("images") val images : List<String>,
	@SerializedName("creators") val creators : Creators,
	@SerializedName("characters") val characters : Characters,
	@SerializedName("stories") val stories : Stories,
	@SerializedName("events") val events : Events*/
)