package com.example.controlmanutencao

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData


class ServicoViewModel(private val repository: ServicoRepository) : ViewModel() {

    val listaManutencoes: LiveData<List<Servico>> = repository.historico.asLiveData()
    val custoTotal: LiveData<Double?> = repository.total.asLiveData()

    fun adicionarManutencao(veiculo: String, servico: String, valor: Double, data: Long) {
        viewModelScope.launch {
            val nova = Servico(tipoVeiculo = veiculo, descricao = servico, custo = valor, data = data)
            repository.salvar(nova)
        }
    }

    fun removerManutencao(servico: Servico) {
        viewModelScope.launch {
            repository.excluir(servico)
        }
    }
}