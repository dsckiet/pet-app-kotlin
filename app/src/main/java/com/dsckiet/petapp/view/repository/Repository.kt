package com.dsckiet.petapp.view.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dsckiet.petapp.view.call.RetrofitInstance
import com.dsckiet.petapp.view.model.*
import com.dsckiet.petapp.view.model.body.CreateEventBody
import com.dsckiet.petapp.view.model.body.FeedPostBody
import com.dsckiet.petapp.view.model.body.ForumBody
import com.dsckiet.petapp.view.model.get.AllEventTypes
import com.dsckiet.petapp.view.model.get.PetBreed
import com.dsckiet.petapp.view.model.get.PetCategory
import com.dsckiet.petapp.view.model.get.feeds.FeedDataCheck
import com.dsckiet.petapp.view.model.get.logout.Logout
import com.dsckiet.petapp.view.model.post.*
import com.dsckiet.petapp.view.util.LocalKeyStorage
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository constructor(val application: Application) {

    val localKeyStorage: LocalKeyStorage = LocalKeyStorage(application)
    val registerData = MutableLiveData<Response<RegisterResponse>>()
    val loginData = MutableLiveData<Response<LoginResponse>>()
    val categoryData = MutableLiveData<PetCategory>()
    val breedData = MutableLiveData<PetBreed>()
    val allEventsData = MutableLiveData<AllEventTypes>()
    val createEventData = MutableLiveData<CreateEvent>()
    val createForumData = MutableLiveData<CreateForum>()
    val feedImageData = MutableLiveData<FeedImageUpload>()
    val feedPostData = MutableLiveData<FeedPost>()
    val chatData = MutableLiveData<ChatsItem>()
    val feedsData = MutableLiveData<FeedDataCheck>()
    val upcomingData = MutableLiveData<ItemUpcoming>()
    val recentData = MutableLiveData<ItemRecent>()
    val TAG = "Repository"

    fun postRegister(postRegister: PostOwnerData) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.postRegister(registerBody = postRegister)

        callAPI.enqueue(object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("Signup", "onResponse: ${t.message}")
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                Log.d("Signup", "onResponse: ${response.raw()}")
                Log.d(TAG, "onResponse: ")
                Log.d("Signup", "onResponse: ${response.body()}")
                Log.d(TAG, "onResponse: $response")
                Log.d("Signup", "onResponse: ${response.errorBody()}")
                val registerResponse = response.body()
                registerData.value = response
            }
        })

    }

    fun postLogin(password: String, username: String) {

        val login = PostLogin(password, username)
        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.postLogin(auth = "Basic", loginBody = login)

        callAPI.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Toast.makeText(application, "${response.message()}", Toast.LENGTH_SHORT).show()
                Log.d("loginResponse", "onResponse: ${response.body()}")
                val loginResponse = response.body()
                val responseCode: Int = response.code()
                var header: String? = response.headers().get("Set-Cookie")
                Log.d(TAG, "onResponse: ${header}")
                var p: Int? = header?.indexOf(';')
                header = header?.subSequence(10, p!!).toString()
                localKeyStorage.saveValue(LocalKeyStorage.COOKIE, header)
                localKeyStorage.getValue(LocalKeyStorage.COOKIE)

                Log.d(TAG, "onResponse: ${header}")
                loginData.value = response
                Log.d("zx", "onResponse: $response")

                if (loginResponse != null) {
                    val msg = loginResponse.response
                }
            }
        })
    }

    fun getLogout(cookie: String) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callApi = retrofitService.getLogout(cookie)

        callApi.enqueue(object : Callback<Logout> {
            override fun onFailure(call: Call<Logout>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Logout>, response: Response<Logout>) {
                Log.d("Logout", "onResponse: ${response.message()}")
            }
        })
    }

    fun getCategory() {
        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getCategory()

        callAPI.enqueue(object : Callback<PetCategory> {
            override fun onFailure(call: Call<PetCategory>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PetCategory>, response: Response<PetCategory>) {
                categoryData.value = response.body()
            }
        })
    }

    fun getBreed() {
        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getBreed()

        callAPI.enqueue(object : Callback<PetBreed> {
            override fun onFailure(call: Call<PetBreed>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PetBreed>, response: Response<PetBreed>) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postFileUpload() {}

    fun getFacebookAuth() {}

    fun getGoogleAuth() {
        val retrofitService = RetrofitInstance.getClient(application)
        val callApi = retrofitService.getGoogleAuth()
    }

    fun getAllEvents() {
        val retrofitService = RetrofitInstance.getClient(application)
        val callApi = retrofitService.getAllEvents()

        callApi.enqueue(object : Callback<AllEventTypes> {
            override fun onFailure(call: Call<AllEventTypes>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<AllEventTypes>, response: Response<AllEventTypes>) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postEvent(eventBody: CreateEventBody, cookie: String) {
        val retrofitService = RetrofitInstance.getClient(application)
        val callApi = retrofitService.postEvent(eventBody, cookie)

        callApi.enqueue(object : Callback<CreateEvent> {
            override fun onFailure(call: Call<CreateEvent>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CreateEvent>, response: Response<CreateEvent>) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postForum(forumBody: ForumBody, cookie: String) {
        val retrofitService = RetrofitInstance.getClient(application)
        val callApi = retrofitService.postForum(forumBody, cookie)

        callApi.enqueue(object : Callback<CreateForum> {
            override fun onFailure(call: Call<CreateForum>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CreateForum>, response: Response<CreateForum>) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postImageUpload(file: RequestBody, multipartFile: MultipartBody.Part, cookie: String) {
        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.postImageUpload(file, multipartFile, cookie)

        callAPI.enqueue(object : Callback<FeedImageUpload> {
            override fun onFailure(call: Call<FeedImageUpload>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("FeedImageUpload", "onFailure: ${t.message}")
                Log.d("FeedImageUpload", "onFailure: ${t.cause}")
            }

            override fun onResponse(
                call: Call<FeedImageUpload>,
                response: Response<FeedImageUpload>
            ) {
                Log.d("FeedImageUpload", "onResponse: $response")
            }
        })
    }

    fun postFeedPost(feedPostBody: FeedPostBody, cookie: String) {
        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.postFeedPost(feedPostBody, cookie)

        callAPI.enqueue(object : Callback<FeedPost> {
            override fun onFailure(call: Call<FeedPost>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FeedPost>, response: Response<FeedPost>) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postLocation() {}

    //extra functions
    fun getFeedsList(cookie: String) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getFeed(cookie)

        callAPI.enqueue(object : Callback<FeedDataCheck> {
            override fun onFailure(call: Call<FeedDataCheck>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FeedDataCheck>, response: Response<FeedDataCheck>) {
                Log.d("FeedData", "onResponse: ${response}")
                Log.d("FeedData", "onResponse: ${response.body()}")
                val feedData = response.body()
                feedsData.value = feedData
            }
        })

    }

    fun getChatList() {}

    fun getUpcomingList() {}

    fun getRecentList() {}
}