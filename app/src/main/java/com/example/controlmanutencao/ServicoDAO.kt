package com.example.controlmanutencao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicoDao {
    @Query("SELECT * FROM servicos ORDER BY data DESC")
    fun getHistorico(): Flow<List<Servico>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(servico: Servico)

    @Delete
    suspend fun delete(servico: Servico)

    @Query("SELECT SUM(custo) FROM servicos")
    fun getTotalGastos(): Flow<Double?>

    @Query("SELECT * FROM servicos WHERE tipoVeiculo = :tipo ORDER BY data DESC")
    fun getPorTipo(tipo: String): Flow<List<Servico>>
}