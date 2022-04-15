package com.segalahal.moneytrackerapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.segalahal.moneytrackerapp.R
import com.segalahal.moneytrackerapp.data.entity.RecordEntity
import com.segalahal.moneytrackerapp.databinding.ItemTrackerBinding
import kotlin.math.absoluteValue

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var listRecords = ArrayList<RecordEntity>()
    var onItemClick: ((RecordEntity) -> Unit)? = null

    fun setRecords(records: List<RecordEntity>?) {
        if (records == null) return
        this.listRecords.clear()
        this.listRecords.addAll(records)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val itemTrackerBinding = ItemTrackerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemTrackerBinding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        val data = listRecords[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listRecords.size

    inner class MainViewHolder(private val binding: ItemTrackerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recordEntity: RecordEntity){
            with(binding){
                tvName.text = recordEntity.name
                tvAmount.text = recordEntity.amount.toString()
                tvDate.text = recordEntity.date
                if (recordEntity.amount < 0){
                    tvAmount.text = itemView.resources.getString(R.string.str_rp_expense, recordEntity.amount.absoluteValue.toString())
                }else{
                    tvAmount.text = itemView.resources.getString(R.string.str_rp, recordEntity.amount.toString())
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listRecords[adapterPosition])
            }
        }
    }
}