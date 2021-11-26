package com.ali.tasbeat.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.ali.tasbeat.quizapp.databinding.ActivityResultBinding
import java.util.zip.Inflater

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(LayoutInflater.from(this))
        var view = binding.root
        setContentView(view)

        var correctIndex = intent.getIntExtra("correctAnswer" , 0)
        val wrongIndex = intent.getIntExtra("wrongAnswer" , 0)
        val username = intent.getStringExtra("username")
        val questionSize = intent.getIntExtra("questionSize", 0)

        for (index in 0..9 step 1){
            if (index == wrongIndex && index % 3 == 0){
                correctIndex -= 1
            }
        }
        if (correctIndex == questionSize){
            binding.cup.visibility = View.VISIBLE
        }

        var percentShow : Float = ( wrongIndex.toFloat() / correctIndex.toFloat() ) * 100
        binding.percent.text = percentShow.toString()

        if(wrongIndex >= correctIndex){
            binding.username.setBackgroundColor(Color.RED)
        }else binding.username.setBackgroundColor(Color.GREEN)

        binding.correctAnswer.text = correctIndex.toString()
        binding.wrongAnswer.text = wrongIndex.toString()
        binding.username.text = username

    }
}