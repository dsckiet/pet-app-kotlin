package com.dsckiet.petapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dsckiet.petapp.view.model.*
import com.dsckiet.petapp.view.model.get.feeds.FeedsData
import com.dsckiet.petapp.view.repository.Repository
import retrofit2.Response

class ViewModel(application: Application) : AndroidViewModel(application) {

    val registerData: LiveData<Response<RegisterResponse>>
    val loginData: LiveData<Response<LoginResponse>>
    val chatData: LiveData<ChatsItem>
    val feedData: LiveData<FeedsData>
    val upcomingData: LiveData<ItemUpcoming>
    val recentData: LiveData<ItemRecent>

    private val repository = Repository(application)

    init {
        this.registerData = repository.registerData
        this.loginData = repository.loginData
        this.chatData = repository.chatData
        this.feedData = repository.feedsData
        this.upcomingData = repository.upcomingData
        this.recentData = repository.recentData
    }

    fun postRegister(postRegister: PostOwnerData) {
        repository.postRegister(postRegister)
    }

    fun postLogin(password: String, username: String) {
        repository.postLogin(password, username)
    }

    fun chatsList(){
        repository.getChatList()
    }

    fun feedsList(cookie: String){
        repository.getFeedsList(cookie)
    }

    fun upcomingList(){
        repository.getUpcomingList()
    }

    fun recentList(){
        repository.getRecentList()
    }
}