package com.kucingselfie.kadesubmission.di

import com.kucingselfie.kadesubmission.api.ApiClient
import com.kucingselfie.kadesubmission.data.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
//    @Provides
//    @Singleton
//    fun provideApiService() : ApiService {
//        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
//            .create(ApiService::class.java)
//    }

    @Provides
    @Singleton
    fun provideRepository() : RemoteRepository {
        return RemoteRepository(ApiClient)
    }
}