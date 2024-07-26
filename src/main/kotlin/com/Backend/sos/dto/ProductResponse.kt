package com.Backend.sos.dto

data class ProductResponse(
    val id: Long?,
    val tipo: String,
    val descripcion: String,
    val precio: Double?
)

