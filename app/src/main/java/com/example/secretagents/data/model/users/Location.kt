package com.example.secretagents.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
)