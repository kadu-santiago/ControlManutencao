package com.example.controlmanutencao

import android.app.Application

class ServicoApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ServicoRepository(database.servicoDao()) }
}