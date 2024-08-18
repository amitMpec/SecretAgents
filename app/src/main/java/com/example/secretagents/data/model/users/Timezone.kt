package com.example.secretagents.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Timezone(
    val description: String,
    val offset: String
)