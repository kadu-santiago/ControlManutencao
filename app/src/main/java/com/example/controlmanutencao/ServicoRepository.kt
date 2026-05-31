package com.example.controlmanutencao

class ServicoRepository(private val dao: ManutencaoDao) {
    val historico: Flow<List<Manutencao>> = dao.getHistorico()
    val total: Flow<Double?> = dao.getTotalGastos()

    suspend fun salvar(manutencao: Manutencao) = dao.insert(manutencao)
    suspend fun excluir(manutencao: Manutencao) = dao.delete(manutencao)
    fun filtrar(tipo: String) = dao.getPorTipo(tipo)
}