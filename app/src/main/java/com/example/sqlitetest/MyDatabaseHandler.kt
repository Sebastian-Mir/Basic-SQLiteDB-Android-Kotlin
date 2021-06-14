package com.example.sqlitetest

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


/*DB with BASIC functionality*/
class MyDatabaseHandler(
        context: Context,
        name: String?,
        version : Int ) : SQLiteOpenHelper(
                            context,
                            name,
                            null,
                            version) {

    /*onCreate initializes the exercise table with the two parameters defined on Exercise.kt*/
    override fun onCreate(db: SQLiteDatabase?) {
        val sqlTableExercise = "CREATE TABLE exercise (name TEXT, repOrTime INTEGER)"
        db?.execSQL(sqlTableExercise)
        val sqlTableRoutine = "CREATE TABLE routine (dateYear INTEGER, dateMonth INTEGER, dateDay INTEGER, " +
                "idExercise INTEGER, duration INTEGER)"
        db?.execSQL(sqlTableRoutine)
    }


/**/
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
/*Add data to the DB, receives a exercise object to insert on the table created previously*/
    fun addExercise(exe : Exercise) : String {
        try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put("name", exe.name)
            contentValues.put("repOrTime", exe.repOrTime)
            db.insert("exercise", null, contentValues)
            return "Success!!"
        } catch (e : SQLiteException) {
            return e.message.toString()
        }
        //return false
    }
/*In charge of the select query to retrieve the data in the DB
the result is an exercise object list*/
    fun viewExercise(): List<Exercise> {
        val exeList : ArrayList<Exercise> = ArrayList()
        val selectExercise = "SELECT * FROM exercise"
        val db = this.readableDatabase
        var cursor : Cursor? = null
        try {
            cursor = db.rawQuery(selectExercise, null)
        } catch (e : SQLiteException){
            return emptyList()
        }
        //var exerciseData: String
        var nombre : String
        var repOrTime: Int
        if (cursor.moveToFirst()) {
            do {
                nombre = cursor.getString(cursor.getColumnIndex("name"))
                repOrTime =  cursor.getInt(cursor.getColumnIndex("repOrTime"))
                val exerciseObject = Exercise(nombre, repOrTime)
                exeList.add(exerciseObject)
            } while (cursor.moveToNext())
        }
        return exeList
    }

}