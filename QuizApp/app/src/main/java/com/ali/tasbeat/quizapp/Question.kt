package com.ali.tasbeat.quizapp

data class Question(
    val id : Int
    , val description : String
    , val image : Int
    , val selectorOne: String
    , val selectorTwo: String
    , val selectorThree: String
    , val selectorFour: String
    , val trueSelector : Int

)
