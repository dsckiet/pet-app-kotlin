package com.dsckiet.petapp.view.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dsckiet.petapp.view.call.PetAPI
import com.dsckiet.petapp.view.call.RetrofitInstance
import com.dsckiet.petapp.view.model.*
import com.dsckiet.petapp.view.model.get.feeds.FeedsData
import com.dsckiet.petapp.view.util.LocalKeyStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository constructor(val application: Application) {

    val localKeyStorage: LocalKeyStorage = LocalKeyStorage(application)
    val registerData = MutableLiveData<Response<RegisterResponse>>()
    val loginData = MutableLiveData<Response<LoginResponse>>()
    val chatData = MutableLiveData<ChatsItem>()
    val feedsData = MutableLiveData<FeedsData>()
    val upcomingData = MutableLiveData<ItemUpcoming>()
    val recentData = MutableLiveData<ItemRecent>()
    val TAG = "Repository"

    fun postRegister(postRegister: PostOwnerData) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.postRegister(registerBody = postRegister)

        callAPI.enqueue(object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
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
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
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

    fun getChatList() {}

    fun getFeedsList(cookie: String) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getFeed(cookie)

        callAPI.enqueue(object : Callback<FeedsData>{
            override fun onFailure(call: Call<FeedsData>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FeedsData>, response: Response<FeedsData>) {
                Log.d("FeedData", "onResponse: ${response}")
            }
        })

    }

    fun getFeedTest(cookie: String){

       /* val callAPI = getFeedTest(cookie = cookie)

        callAPI.enqueue(object : Callback<FeedsData>{
            override fun onFailure(call: Call<FeedsData>, t: Throwable) {
                Toast.makeText(application, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FeedsData>, response: Response<FeedsData>) {
                Log.d("FeedDataTest", "onResponse: ${response}")
            }
        })*/

    }

    fun getUpcomingList() {}

    fun getRecentList() {}
}