package com.segalahal.moneytrackerapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.segalahal.moneytrackerapp.R
import com.segalahal.moneytrackerapp.databinding.ActivityMainBinding
import com.segalahal.moneytrackerapp.ui.detail.DetailActivity
import com.segalahal.moneytrackerapp.ui.input.InputRecordActivity
import com.segalahal.moneytrackerapp.viewmodel.ViewModelFactory
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInput.setOnClickListener {
            val i = Intent(this, InputRecordActivity::class.java)
            startActivity(i)
        }

        mainAdapter = MainAdapter()
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.getRecords().observe(this) { data ->
            mainAdapter.setRecords(data)
            mainAdapter.notifyDataSetChanged()
        }
        viewModel.getEarnings().observe(this) {earnings ->
            binding.tvEarnings.text = getString(R.string.str_rp, earnings.toString())
        }
        viewModel.getExpense().observe(this) {expense ->
            binding.tvExpense.text = getString(R.string.str_rp, expense.absoluteValue.toString())
        }
        viewModel.getMyMoney().observe(this) {myMoney ->
            binding.tvMyMoney.text = getString(R.string.str_rp, myMoney.toString())
        }

        mainAdapter.onItemClick = {selected ->
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra(DetailActivity.EXTRA_RECORD_ID, selected.id)
            startActivity(i)
        }

        with(binding.rvTracker){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRecords().observe(this) { data ->
            mainAdapter.setRecords(data)
            mainAdapter.notifyDataSetChanged()
        }
        viewModel.getEarnings().observe(this) {earnings ->
            binding.tvEarnings.text = getString(R.string.str_rp, earnings.toString())
        }
        viewModel.getExpense().observe(this) {expense ->
            binding.tvExpense.text = getString(R.string.str_rp, expense.absoluteValue.toString())
        }
        viewModel.getMyMoney().observe(this) {myMoney ->
            binding.tvMyMoney.text = getString(R.string.str_rp, myMoney.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}