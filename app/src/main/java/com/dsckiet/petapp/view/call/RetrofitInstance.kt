package com.dsckiet.petapp.view.call

import android.content.Context
import android.util.Log
import com.dsckiet.petapp.view.call.PetAPI.Companion.baseURL
import com.dsckiet.petapp.view.util.InternetConnectivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val cacheSize: Long = 50 * 1024 * 1024
    fun getClient(context: Context): PetAPI {
        val cache: Cache = Cache(context.cacheDir, cacheSize)

        val REWRITE_RESPONSE_INTERCEPTOR = Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            val cacheControl = originalResponse.header("Cache-Control")
            var maxAge = 60
            originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
        val REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = Interceptor { chain ->
            var request: Request = chain.request()
            var maxStale = 60 * 60 * 24 * 30
            if (!InternetConnectivity.isNetworkAvailable(context)!!) {
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
            .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()


        val retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(baseURL)
                .client(okHttpClient)
                .build()
        }

        val api: PetAPI by lazy {
            retrofit.create(PetAPI::class.java)
        }
        Log.d("petAPI", "getClient: $api")

        return api
    }
}