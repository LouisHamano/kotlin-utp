package com.example.appcolegio.retrofit.entidades

data class MenuResponse<T>(
    val sucess: Boolean,
    val mensaje: String,
    val data:T
) {
}