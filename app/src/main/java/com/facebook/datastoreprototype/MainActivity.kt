package com.facebook.datastoreprototype


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var textView:TextView
    private lateinit var button: Button
    private lateinit var editText: EditText

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView=findViewById(R.id.textView)
        button=findViewById(R.id.save_btn)
        editText=findViewById(R.id.name_et)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.firstName.observe(this, {
            textView.text = it.firstName+"\n"+it.lastName+"\n"+it.age
            Log.d("FirstName", it.firstName)
            Log.d("LastName", it.lastName)
            Log.d("AgeName", it.age.toString())
        })

        button.setOnClickListener {
            val firstName = editText.text.toString()
            mainViewModel.updateValue(firstName, "Jovanovic", 25)
        }

    }
}

