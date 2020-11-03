package net.bedev.testlumut.rest

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("todos/")
    fun get_todos(): Call<ResponseBody>



    @GET("todos/")
    fun post_todos(
        @Query("id") id: Int
    ): Call<ResponseBody>


}