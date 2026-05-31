package com.example.controlmanutencao

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button

class CadastroActivity : AppCompatActivity() {

    private val viewModel: ServicoViewModel by viewModels {
        ServicoViewModelFactory((application as ServicoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val edtDescricao = findViewById<EditText>(R.id.edtDescricao)
        val edtCusto = findViewById<EditText>(R.id.edtCusto)

        findViewById<Button>(R.id.btnSalvar).setOnClickListener {
            val tipo = if (findViewById<RadioButton>(R.id.rbCarro).isChecked) "Carro" else "Moto"
            val desc = edtDescricao.text.toString()
            val custo = edtCusto.text.toString().toDoubleOrNull() ?: 0.0
            val data = System.currentTimeMillis()

            if (desc.isNotEmpty()) {
                viewModel.adicionarServico(tipo, desc, custo, data)
                finish()
            }
        }
    }
}