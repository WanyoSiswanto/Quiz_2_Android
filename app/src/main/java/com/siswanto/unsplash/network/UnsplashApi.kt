package com.siswanto.unsplash.network

import com.siswanto.unsplash.model.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = "nouCW6ksATjagi2UHbAeV8hxeZt0_LdHAVhneweTx6w"
    ): Call<List<Photo>>
}
