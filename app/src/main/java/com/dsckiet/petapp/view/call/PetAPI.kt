package com.dsckiet.petapp.view.call

import com.dsckiet.petapp.view.model.LoginResponse
import com.dsckiet.petapp.view.model.PostLogin
import com.dsckiet.petapp.view.model.PostOwnerData
import com.dsckiet.petapp.view.model.body.CreateEventBody
import com.dsckiet.petapp.view.model.body.FeedPostBody
import com.dsckiet.petapp.view.model.body.ForumBody
import com.dsckiet.petapp.view.model.get.AllEventTypes
import com.dsckiet.petapp.view.model.get.PetBreed
import com.dsckiet.petapp.view.model.get.PetCategory
import com.dsckiet.petapp.view.model.get.feeds.FeedDataCheck
import com.dsckiet.petapp.view.model.get.logout.Logout
import com.dsckiet.petapp.view.model.post.*
import retrofit2.Call
import okhttp3.MultipartBody

import okhttp3.RequestBody

import okhttp3.OkHttpClient
import retrofit2.http.*
import retrofit2.http.POST

import retrofit2.http.Multipart
import java.io.File


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

    @GET("/login/logout/")
    fun getLogout(
        @Header("token")
        token: String = "ok"
    ): Call<Logout>

    @GET("/pet/get_category/")
    fun getCategory(): Call<PetCategory>

    @GET("/pet/get_breed/?category=1")
    fun getBreed(): Call<PetBreed>

    @POST("")
    fun postFileUpload(
        @Body file: String,
        @Header("Authorization") auth: String = "Basic",
        @Header("Cookie") cookie: String
    ): Call<FileUpload>

    @GET("/login/accounts/google/login/")
    fun getFacebookAuth()

    @GET("/login/accounts/google/login/")
    fun getGoogleAuth()

    @GET("/event/get_events/")
    fun getAllEvents(): Call<AllEventTypes>

    @POST("/event/event_create/")
    fun postEvent(
        @Body eventBody: CreateEventBody,
        @Header("Cookie") cookie: String
    ): Call<CreateEvent>

    @POST("/user/forum_create/")
    fun postForum(
        @Body forumBody: ForumBody,
        @Header("Cookie") cookie: String
    ): Call<CreateForum>

    @Multipart
    @POST("/user/feed_img_upload/")
    fun postImageUpload(
        @Part("file") file: RequestBody,
        @Part multiPartFile : MultipartBody.Part,
        @Header("Cookie") cookie: String
    ): Call<FeedImageUpload>

    @POST("/user/feed_create/")
    fun postFeedPost(
        @Body feedPostBody: FeedPostBody,
        @Header("Cookie") cookie: String
    ): Call<FeedPost>

    @GET("/user/get_all_feed/")
    fun getFeed(
        @Header("Cookie")
        cookie: String
    ): Call<FeedDataCheck>

    @POST("/location/save_loc/")
    fun postLocation(
        @Body location: Location
    )

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part part: MultipartBody.Part?,
        @Part("somedata") requestBody: RequestBody?
    ): Call<RequestBody?>?

}