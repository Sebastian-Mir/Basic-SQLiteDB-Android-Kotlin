package com.example.sqlitetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    /*Variables to link the views and kotlin code
    the complete program uses a binder but this is the simple method*/
    private lateinit var myDatabaseHandler : MyDatabaseHandler
    private lateinit var name : EditText
    private lateinit var repOrTime : RadioGroup
    private lateinit var insertButton : Button
    private lateinit var status : TextView
    private lateinit var readExercises : Button

    /*Lifecycle start onCreate function*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*initializes the DB handler and the UI views*/
        myDatabaseHandler = MyDatabaseHandler(this, "Exercise", 1)
        name = findViewById(R.id.exercise_name)
        repOrTime = findViewById(R.id.rep_or_time)
        insertButton = findViewById(R.id.insert_exercise)
        status = findViewById(R.id.status)
        readExercises = findViewById(R.id.read_exercise)
        /*Buttons listeners to insert and retrieve data from the SQLite DB*/
        insertButton.setOnClickListener { insertExercise() }
        readExercises.setOnClickListener { retrieveExercise() }

    }

    /*Retrieve function called by the click listener*/
    private fun retrieveExercise() {
        val exeList : List<Exercise> = myDatabaseHandler.viewExercise()
        var results : String = ""
        if (exeList.isNullOrEmpty()){
            status.text = "Empty List"
        } else {
            for (e in exeList){
                results += e.name.toString().plus(" ").plus(e.repOrTime.toString())
            }
            status.text = results
        }
    }

    /*Insert function called by the click listener*/
    private fun insertExercise(){
        val exeName = name.text.toString()
        val radioValue = repOrTime.checkedRadioButtonId
        var rep = 0
        when(radioValue){
            R.id.repetitions -> rep = 0
            R.id.time -> rep = 1
            else -> rep = 2
        }

        val exerciseObject = Exercise(exeName, rep)
        val insertStatus = myDatabaseHandler.addExercise(exerciseObject)
        status.text = insertStatus

    }
}