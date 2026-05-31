package com.example.controlmanutencao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Servico::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // Deixamos apenas UM DAO, com o tipo de retorno correto:
    abstract fun servicoDao(): ServicoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "servico_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}