package com.example.secretagents.data.model.users

data class Data(
    val currentPageItems: Int,
    val `data`: List<DataX>,
    val limit: Int,
    val nextPage: Boolean,
    val page: Int,
    val previousPage: Boolean,
    val totalItems: Int,
    val totalPages: Int
)