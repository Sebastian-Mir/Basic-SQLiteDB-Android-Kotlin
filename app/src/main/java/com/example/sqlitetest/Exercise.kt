package com.example.sqlitetest

/*This one is a class to represent the Exercises to store in the DB*/
class Exercise(val name: String, val repOrTime : Int)
/*As of now, the class is only used during the upload
* The idea is to expand this idea into a full functional program
* I am working right now
*
* name is the name of the exercise and
* repOrTime is an int which indicates the type of exercise
* I am not an expert, so I wanted to use an int instead of a boolean
* in case there are more than two ways to measure the exercise
* so the program can be expanded*/