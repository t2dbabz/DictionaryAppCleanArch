package com.example.dictionary.di

import android.app.Application
import androidx.room.Room
import com.example.dictionary.feature_dictionary.data.local.Converters
import com.example.dictionary.feature_dictionary.data.local.WordInfoDao
import com.example.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.example.dictionary.feature_dictionary.data.util.Constants
import com.example.dictionary.feature_dictionary.data.util.GsonParser
import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.example.dictionary.feature_dictionary.domain.use_case.GetWordInfoUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGetWordInfoUseCase(wordInfoRepository: WordInfoRepository): GetWordInfoUseCase {
        return GetWordInfoUseCase(wordInfoRepository)
    }

    @Provides
    @Singleton
    fun providesWordInfoRepository(db: WordInfoDatabase, api: DictionaryApi ): WordInfoRepository {
        return WordInfoRepositoryImpl(db.wordInfoDao, api)
    }

    @Provides
    @Singleton
    fun providesWordInfoDatabase(  application: Application): WordInfoDatabase {
        return Room.databaseBuilder(application, WordInfoDatabase::class.java, "word_info_db")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideHttpClientForAuth(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS )
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    @Singleton
    fun providesDictionaryApi(okHttpClient: OkHttpClient): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(DictionaryApi::class.java)
    }
}