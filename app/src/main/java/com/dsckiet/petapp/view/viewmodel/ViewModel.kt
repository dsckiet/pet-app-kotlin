package com.dsckiet.petapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dsckiet.petapp.view.model.*
import com.dsckiet.petapp.view.model.get.AllEventTypes
import com.dsckiet.petapp.view.model.get.PetBreed
import com.dsckiet.petapp.view.model.get.PetCategory
import com.dsckiet.petapp.view.model.get.feeds.FeedDataCheck
import com.dsckiet.petapp.view.model.post.RegisterResponse
import com.dsckiet.petapp.view.repository.Repository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class ViewModel(application: Application) : AndroidViewModel(application) {

    val registerData: LiveData<Response<RegisterResponse>>
    val loginData: LiveData<Response<LoginResponse>>
    val categoryData: LiveData<PetCategory>
    val breedData: LiveData<PetBreed>
    val allEventsData: LiveData<AllEventTypes>
    val chatData: LiveData<ChatsItem>
    val feedData: LiveData<FeedDataCheck>
    val upcomingData: LiveData<ItemUpcoming>
    val recentData: LiveData<ItemRecent>

    private val repository = Repository(application)

    init {
        this.registerData = repository.registerData
        this.loginData = repository.loginData
        this.categoryData = repository.categoryData
        this.breedData = repository.breedData
        this.allEventsData = repository.allEventsData
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

    fun getLogout(cookie: String) {
        repository.getLogout(cookie)
    }

    fun getCategory() {
        repository.getCategory()
    }

    fun getBreed() {
        repository.getBreed()
    }

    fun getAllEvents() {
        repository.getAllEvents()
    }

    fun chatsList() {
        repository.getChatList()
    }

    fun feedsList(cookie: String) {
        repository.getFeedsList(cookie)
    }

    fun upcomingList() {
        repository.getUpcomingList()
    }

    fun recentList() {
        repository.getRecentList()
    }

    fun googleAuth() {
        repository.getGoogleAuth()
    }

    fun postFeedImage(file: RequestBody, multipartFile: MultipartBody.Part, cookie: String) {
        repository.postImageUpload(file, multipartFile, cookie)
    }
}