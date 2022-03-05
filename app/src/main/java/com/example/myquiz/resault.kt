package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.myquiz.databinding.ActivityQuestionBinding

import com.example.myquiz.databinding.ActivityResaultBinding

class resault : AppCompatActivity() {
    private lateinit var binding: ActivityResaultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResaultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val finalname=intent.getStringExtra(constant.username)
        binding.username.text=finalname
        val finalCorrect=intent.getIntExtra(constant.correct_answers,0)
        val finalTotal=intent.getIntExtra(constant.total_question,0)
        binding.score.text="yore score is $finalCorrect out of $finalTotal"

        binding.finish.setOnClickListener {
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}