package com.example.dictionary.feature_dictionary.data.local

import androidx.room.*
import com.example.dictionary.feature_dictionary.data.local.entity.WorldInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(wordInfos: List<WorldInfoEntity>)

    @Query("DELETE FROM worldinfoentity WHERE word IN(:words)")
    suspend fun deleteWordsInfos(words: List<String>)

    @Query("SELECT * FROM worldinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun searchWordInfos(word: String): List<WorldInfoEntity>

}