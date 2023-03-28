package com.vashu.dob_calc

import android.app.DatePickerDialog
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

     private var tvSelectedDate: TextView? = null

    private var tvAgeInMinutes:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDataPicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)


        btnDataPicker.setOnClickListener {
            clickDatePicker()
        }


    }



  private  fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = Calendar.YEAR
        val month = Calendar.MONTH
        val day  = Calendar.DAY_OF_MONTH

        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{_,selected_year,selected_month,selected_day->

            Toast.makeText(this,"Date picker$selected_year and month ${selected_month+1} " +
                    "and day $selected_day",Toast.LENGTH_LONG).show()

            val selectedDate = "$selected_day/${selected_month+1}/$selected_year"

            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)


            theDate?.let {


                val selectedDateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000


                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    tvAgeInMinutes?.text = differenceInMinutes.toString()
                }
            }






        }, year,month,day)



        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()

    }





}