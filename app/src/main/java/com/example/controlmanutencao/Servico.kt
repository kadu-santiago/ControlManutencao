package com.example.controlmanutencao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicos")
    data class Servico(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipoVeiculo: String, // "Carro" ou "Moto"
    val descricao: String,
    val custo: Double,
    val data: Long // Armazenado como Timestamp para ordenação
)