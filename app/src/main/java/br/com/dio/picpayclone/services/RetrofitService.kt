package br.com.dio.picpayclone.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL = "http://192.168.56.1:8080"

object RetrofitService {

    val service = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create() = service.create(T::class.java)

    // fun createUsuarioClient() = service.create(ApiService::class.java)
}
