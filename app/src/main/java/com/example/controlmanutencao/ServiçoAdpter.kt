package com.example.controlmanutencao

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import com.google.android.filament.View


class ServicoAdapter(private val onDeleteClick: (Servico) -> Unit) :
    ListAdapter<Servico, ServicoAdapter.ServicoViewHolder>(ServicoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ServicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        val servico = getItem(position)
        holder.bind(servico, onDeleteClick)
    }

    class ServicoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTipo: TextView = itemView.findViewById(R.id.txtTipoItem)
        private val txtDescricao: TextView = itemView.findViewById(R.id.txtDescricaoItem)
        private val txtCusto: TextView = itemView.findViewById(R.id.txtCustoItem)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(servico: Servico, onDeleteClick: (Servico) -> Unit) {
            txtTipo.text = servico.tipoVeiculo
            txtDescricao.text = servico.descricao
            txtCusto.text = "R$ ${servico.custo}"

            btnDelete.setOnClickListener {
                onDeleteClick(servico)
            }
        }
    }

    class ServicoDiffCallback : DiffUtil.ItemCallback<Servico>() {
        override fun areItemsTheSame(oldItem: Servico, newItem: Servico): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Servico, newItem: Servico): Boolean {
            return oldItem == newItem
        }
    }
}