package com.ali.tasbeat.quizapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.ali.tasbeat.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(LayoutInflater.from(this))
        var view = binding.root
        setContentView(view)
    }

    fun loginButton(view: View){
        view as Button

        if (binding.inputUsername.text.toString().isEmpty() ||
            binding.inputUsername.text.toString().contains("1") ||
            binding.inputUsername.text.toString().contains("2") ||
            binding.inputUsername.text.toString().contains("3") ||
            binding.inputUsername.text.toString().contains("4") ||
            binding.inputUsername.text.toString().contains("5") ||
            binding.inputUsername.text.toString().contains("6") ||
            binding.inputUsername.text.toString().contains("7") ||
            binding.inputUsername.text.toString().contains("8") ||
            binding.inputUsername.text.toString().contains("9") ||
            binding.inputUsername.text.toString().contains("0") ){
            Toast.makeText(this, "please enter right username", Toast.LENGTH_SHORT).show()
        }else {
            var intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("username" , binding.inputUsername.text.toString())
            startActivity(intent)
        }
    }
}
