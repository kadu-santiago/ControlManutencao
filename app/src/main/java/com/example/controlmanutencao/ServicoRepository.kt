package com.example.controlmanutencao

import kotlinx.coroutines.flow.Flow

class ServicoRepository(private val dao: ServicoDao) {
    val historico: Flow<List<Servico>> = dao.getHistorico()
    val total: Flow<Double?> = dao.getTotalGastos()

    suspend fun salvar(servico: Servico) = dao.insert(servico)
    suspend fun excluir(servico: Servico) = dao.delete(servico)
    fun filtrar(tipo: String) = dao.getPorTipo(tipo)
}