package com.segalahal.moneytrackerapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.segalahal.moneytrackerapp.R
import com.segalahal.moneytrackerapp.data.entity.RecordEntity
import com.segalahal.moneytrackerapp.databinding.ActivityDetailBinding
import com.segalahal.moneytrackerapp.ui.main.MainActivity
import com.segalahal.moneytrackerapp.viewmodel.ViewModelFactory
import kotlin.math.absoluteValue

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel
    private lateinit var recordData : RecordEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.title_record_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recordId = intent.getIntExtra(EXTRA_RECORD_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]

        viewModel.getRecordById(recordId).observe(this) {record ->
            recordData = record

            binding.tvRecordName.text = record.name
            binding.tvRecordAmount.text = getString(R.string.str_rp, record.amount.absoluteValue.toString())
            if (record.amount < 0){
                binding.tvRecordExpenseIncome.text = getString(R.string.str_expense)
            }else{
                binding.tvRecordExpenseIncome.text = getString(R.string.str_income)
            }
            binding.tvRecordDate.text = record.date
            binding.tvRecordNote.text = record.notes
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete ->{
                viewModel.deleteRecord(recordData)
                val i = Intent(this, MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
                finish()
                return true
            }
            else -> false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_RECORD_ID = "extra_record_id"
    }
}