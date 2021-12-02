package com.dsckiet.petapp.view.call

import com.dsckiet.petapp.view.model.LoginResponse
import com.dsckiet.petapp.view.model.PostLogin
import com.dsckiet.petapp.view.model.PostOwnerData
import com.dsckiet.petapp.view.model.RegisterResponse
import com.dsckiet.petapp.view.model.get.feeds.FeedsData
import retrofit2.Call
import retrofit2.http.*

interface PetAPI {

    companion object {
        const val baseURL = "https://ancient-earth-36601.herokuapp.com"
        const val sampleToken = "gbff0078ajg4vt10oc6s56fp2rprdpze"
    }

    @POST("/login/register/")
    fun postRegister(
        @Body registerBody: PostOwnerData
    ): Call<RegisterResponse>

    @POST("/login/login/")
    fun postLogin(
        @Header("Authorization") auth: String = "Basic",
        @Body loginBody: PostLogin
    ): Call<LoginResponse>

//    @GET("/login/logout/")
//    fun getLogout(
//        @Header("token")
//        token: String = "ok"
//    )

    @GET("/user/get_all_feed/")
    fun getFeed(
        @Header("Cookie")
        cookie: String
    ) : Call<FeedsData>

    @GET("https://ancient-earth-36601.herokuapp.com/user/get_all_feed/")
    fun getFeedTest(
        @Header("Cookie")
        cookie: String
    ) : Call<FeedsData>
}