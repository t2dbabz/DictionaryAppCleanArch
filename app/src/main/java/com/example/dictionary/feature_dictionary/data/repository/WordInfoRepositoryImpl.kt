package com.example.dictionary.feature_dictionary.data.repository

import com.example.dictionary.core.util.Resource
import com.example.dictionary.feature_dictionary.data.local.WordInfoDao
import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val wordInfoDao: WordInfoDao,
    private val dictionaryApi: DictionaryApi
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = wordInfoDao.searchWordInfos(word).map { it.toWordInfo() }

        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = dictionaryApi.getWord(word)
            wordInfoDao.deleteWordsInfos(remoteWordInfos.map { it.word })
            wordInfoDao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        }catch (e: HttpException) {
            emit(Resource.Error(message = "Oops Message something went wrong", data = wordInfos))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection"))
        }


        val newWordInfos = wordInfoDao.searchWordInfos(word).map { it.toWordInfo() }

        emit(Resource.Success(data = newWordInfos))
    }
}