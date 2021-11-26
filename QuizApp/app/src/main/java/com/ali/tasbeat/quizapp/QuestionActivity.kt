package com.ali.tasbeat.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ali.tasbeat.quizapp.databinding.ActivityQuestionBinding


class QuestionActivity : AppCompatActivity() {
    
    lateinit var binding : ActivityQuestionBinding
    var questionCounter = 0
    var questionSelected = 0
    lateinit var selectedOption : TextView
    lateinit var questionList : ArrayList<Question>
    var arrayListOfOptions = ArrayList<TextView>()
    var correctAnswer = 0
    var wrongAnswer = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(LayoutInflater.from(this))
        var questionView  = binding.root
        setContentView(questionView)

        questionList = Constants.getQuestions()

        questionList()

        arrayListOfOptions.add(binding.Option1)
        arrayListOfOptions.add(binding.Option2)
        arrayListOfOptions.add(binding.Option3)
        arrayListOfOptions.add(binding.Option4)

        val username = intent.getStringExtra("username")
        binding.buttonResult.setOnClickListener {
            var intent = Intent(this@QuestionActivity , ResultActivity::class.java)
            intent.putExtra("username" , username )
            intent.putExtra("correctAnswer" , correctAnswer )
            intent.putExtra("wrongAnswer" , wrongAnswer )
            intent.putExtra("questionSize", questionList.size )
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    fun questionList(){

        binding.questionTitle.text = questionList[questionCounter].description
        binding.imageView.setImageResource(questionList[questionCounter].image)
        binding.textViewProgressBar.text = "${questionCounter + 1} / ${binding.progressBar.max}"
        binding.Option1.text = questionList[questionCounter].selectorOne
        binding.Option2.text = questionList[questionCounter].selectorTwo
        binding.Option3.text = questionList[questionCounter].selectorThree
        binding.Option4.text = questionList[questionCounter].selectorFour
        binding.progressBar.progress += 1

    }

    @SuppressLint("SetTextI18n")
    fun buttonSubmit(view : View){
        view as Button
        if (questionCounter == questionList.size - 1 && questionSelected == 0){
            binding.buttonSubmit.visibility = View.GONE
            binding.buttonResult.visibility = View.VISIBLE
        } else {
            if ( questionCounter == questionList.size - 1 && questionSelected != 0){
                if (questionList[questionCounter].trueSelector == questionSelected) {
                    selectedOption.setBackgroundResource(R.drawable.correct_answer)
                    correctAnswer+=1

                } else {
                    selectedOption.setBackgroundResource(R.drawable.wrong_answer)
                    var correctAnswer = questionList[questionCounter].trueSelector
                    var correctOptionInTextView = arrayListOfOptions[correctAnswer - 1]
                    correctOptionInTextView.setBackgroundResource(R.drawable.correct_answer)
                    wrongAnswer += 1
                }
                binding.buttonSubmit.visibility = View.GONE
                binding.buttonResult.visibility = View.VISIBLE
            }
        }

        if (questionSelected == 0 && questionCounter < questionList.size - 1 ){
            questionCounter += 1
            questionList()
            setDefaultOptions()
            resetSubmitButton(binding.buttonSubmit)
        } else {
            if (questionList[questionCounter].trueSelector == questionSelected) {
                selectedOption.setBackgroundResource(R.drawable.correct_answer)
                binding.buttonSubmit.text = "Next Question"
                questionSelected = 0
                correctAnswer += 1

            } else {
                if (questionCounter == questionList.size - 1) return
                selectedOption.setBackgroundResource(R.drawable.wrong_answer)
                var correctAnswer = questionList[questionCounter].trueSelector
                var correctOptionInTextView = arrayListOfOptions[correctAnswer - 1]
                correctOptionInTextView.setBackgroundResource(R.drawable.correct_answer)
                binding.buttonSubmit.text = "Next Question"
                questionSelected = 0
                wrongAnswer += 1

            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun resetSubmitButton(view: View){
        view as Button
        view.text = "SUBMIT"

    }
    fun setDefaultOptions(){

        for (index in arrayListOfOptions) {
            index.typeface = Typeface.DEFAULT
            index.setBackgroundResource(R.drawable.background_format)
        }
    }

    fun options(view: View){
        setDefaultOptions()
        selectedOption = view as TextView
            when (selectedOption.tag.toString()) {
                "optionOne" -> {
                    questionSelected = 1
                }
                "optionTwo" -> {
                    questionSelected = 2
                }
                "optionThree" -> {
                    questionSelected = 3
                }
                "optionFour" -> {
                    questionSelected = 4
                }
            }
            selectedOption.typeface = Typeface.DEFAULT_BOLD
            selectedOption.setBackgroundResource(R.drawable.selector_background)
    }
}