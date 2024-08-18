package com.example.secretagents.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Int,
    val location: Location,
    val login: Login,
    var name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)