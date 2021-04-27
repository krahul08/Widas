package com.example.widas.model


data class DataResponse(val results: List<Result>, val info: Info)

data class Result(
    val gender: String,
    val name: Name,
    val location: Location,
    val picture: Picture,
)

data class Info(val seed: String, val results: Int, val page: Int)
data class Location(val coordinates: Coordinates)
data class Coordinates(val latitude: String, val longitude: String)
data class Name(val title: String, val first: String, val last: String)
data class Picture(val thumbnail: String)








