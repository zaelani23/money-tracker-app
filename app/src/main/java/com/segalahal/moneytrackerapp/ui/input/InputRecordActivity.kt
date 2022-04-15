package com.segalahal.moneytrackerapp.ui.input

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.segalahal.moneytrackerapp.R
import com.segalahal.moneytrackerapp.data.entity.RecordEntity
import com.segalahal.moneytrackerapp.databinding.ActivityInputRecordBinding
import com.segalahal.moneytrackerapp.ui.main.MainActivity
import com.segalahal.moneytrackerapp.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class InputRecordActivity : AppCompatActivity() {

    private var _binding : ActivityInputRecordBinding? = null
    private val binding get() = _binding!!
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInputRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.title_input_record)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[InputViewModel::class.java]

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        binding.recordDateEditText.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(
                    this@InputRecordActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        binding.btnSave?.setOnClickListener {
            val name = binding.recordNameEditText.text
            val amount = binding.recordAmountEditText.text
            val answerOptions = binding.answerOptions.checkedRadioButtonId
            val date = binding.recordDateEditText.text
            val note = binding.recordNoteEditText.text

            if (name!!.isEmpty() || amount!!.isEmpty() || date!!.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua form", Toast.LENGTH_SHORT).show()
            }else{
                var myAmount = amount.toString().toInt()

                if (answerOptions == binding.optionExpense.id){
                    myAmount = -myAmount
                }

                val data = RecordEntity(
                    null,
                    name.toString(),
                    myAmount,
                    date.toString(),
                    note.toString()
                )

                viewModel.setRecord(data)

                val i = Intent(this, MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
                finish()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.recordDateEditText.setText(sdf.format(cal.getTime()))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}