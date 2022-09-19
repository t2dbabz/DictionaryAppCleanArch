package com.example.dictionary.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.dictionary.feature_dictionary.data.local.entity.WorldInfoEntity


@Database(entities = [WorldInfoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase(): RoomDatabase() {
    abstract val wordInfoDao: WordInfoDao
}