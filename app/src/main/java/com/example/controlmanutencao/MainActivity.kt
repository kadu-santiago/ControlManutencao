package com.example.controlmanutencao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val viewModel: ServicoViewModel by viewModels {
        ServicoViewModelFactory((application as ServicoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ServicoAdapter { servico ->
            viewModel.removerServico(servico)
        }

        val rv = findViewById<RecyclerView>(R.id.rvHistorico)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        viewModel.listaServicos.observe(this) { lista ->
            adapter.submitList(lista)
        }

        viewModel.custoTotal.observe(this) { total ->
            findViewById<TextView>(R.id.txtTotalGastos).text = "R$ ${total ?: 0.0}"
        }

        findViewById<Button>(R.id.btnFiltroCarros).setOnClickListener {
            viewModel.filtrarPorTipo("Carro").observe(this) { lista ->
                adapter.submitList(lista)
            }
        }

        findViewById<Button>(R.id.btnFiltroTodos).setOnClickListener {
            viewModel.listaServicos.observe(this) { lista ->
                adapter.submitList(lista)
            }
        }

        findViewById<Button>(R.id.btnNovoServico).setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }
}