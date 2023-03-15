package com.example.calenderdemo

import android.media.metrics.Event
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var calendarView : CalendarView
    private lateinit var buttonSave : Button
    private lateinit var editText : EditText
    private lateinit var calenderTV : TextView
    private var selectedDate : String  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.calendarView)
        buttonSave = findViewById(R.id.buttonSave)
        editText = findViewById(R.id.editText)
        calenderTV = findViewById(R.id.calenderTV)

        selectedDate = System.currentTimeMillis().toString()


        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener{
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                selectedDate = p1.toString() + (p2+1).toString() + p3.toString()
                Log.e("selectedDate", "onSelectedDayChange: "+selectedDate )
            }
        })

        EventDB.getDB(application).getDao().getAllData().observe(this) {
            val str = StringBuilder()
            it.forEach {
                Log.e("name", "onCreate: "+it.name )
                str.append(it.name+"\n")
            }
            calenderTV.text = str
        }


        buttonSave.setOnClickListener {
            var eventName = editText.text.toString()
            if(eventName!=null && selectedDate!=null){
                var evenModel = EvenModel(name = eventName, date = selectedDate)

                CoroutineScope(Dispatchers.Main).launch {
                    EventDB.getDB(application).getDao().insertEvent(evenModel)

                }
            }else{
                Toast.makeText(application, "Hello"+eventName, Toast.LENGTH_SHORT).show()
            }
        }

    }
}