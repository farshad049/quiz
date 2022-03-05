package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.myquiz.databinding.ActivityMainBinding
import com.example.myquiz.databinding.ActivityQuestionBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.startBtn.setOnClickListener {
            val intent=Intent(this,questionActivity::class.java)
            intent.putExtra(constant.username,binding.editText.text.toString())
            if (binding.editText.text.toString().isEmpty()){
                Toast.makeText(this,"please enter your name",Toast.LENGTH_SHORT).show()
            }else {
                startActivity(intent)
                finish()
            }
        }
    }
}