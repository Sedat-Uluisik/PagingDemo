package com.sedat.pagingdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sedat.pagingdemo.model.myClass

@Database(entities = [myClass::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun Dao(): Dao

    companion object{

        /*
        Buradaki room initialize yöntemini neden kullanmalıyım:
        eğer çok fazla threading işlemi varsa ve aynı anda room'a sağdan soldan ulaşılabilme ihtimalini öngörüyorsan
        (büyük uygulamalarda küçük ihtimal de olsa başına gelebilir) bu yöntem daha iyidir.
         */

        @Volatile private var instance: com.sedat.pagingdemo.room.Database ?= null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                com.sedat.pagingdemo.room.Database::class.java,
                "PagingDemoDb"
        ).build()
    }

}