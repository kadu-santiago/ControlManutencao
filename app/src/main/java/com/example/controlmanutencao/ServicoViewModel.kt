package com.example.controlmanutencao

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData

class ServicoViewModel(private val repository: ServicoRepository) : ViewModel() {

    val listaServicos: LiveData<List<Servico>> = repository.historico.asLiveData()
    val custoTotal: LiveData<Double?> = repository.total.asLiveData()

    fun adicionarServico(veiculo: String, desc: String, valor: Double, data: Long) {
        viewModelScope.launch {
            val nova = Servico(tipoVeiculo = veiculo, descricao = desc, custo = valor, data = data)
            repository.salvar(nova)
        }
    }

    fun removerServico(servico: Servico) {
        viewModelScope.launch {
            repository.excluir(servico)
        }
    }

    fun filtrarPorTipo(tipo: String): LiveData<List<Servico>> {
        return repository.filtrar(tipo).asLiveData()
    }
}