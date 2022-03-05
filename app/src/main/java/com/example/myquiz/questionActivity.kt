package com.example.myquiz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.example.myquiz.databinding.ActivityQuestionBinding
import com.example.myquiz.databinding.ActivityResaultBinding

class questionActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityQuestionBinding

    var questionList=constant.getQuestion()
    var currentPosition=1
    var selectedOPT =0
    var totalCorrect=0
    var takedname:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        takedname=intent.getStringExtra(constant.username)

        setQuestion()
        binding.opt1.setOnClickListener(this)
        binding.opt2.setOnClickListener(this)
        binding.opt3.setOnClickListener(this)
        binding.opt4.setOnClickListener(this)
        binding.submitBtn.setOnClickListener(this)
    }

    fun setQuestion(){

        val soal=questionList.get(currentPosition -1)
        setDefault()

        if (currentPosition == questionList.size){
          binding.submitBtn.text="finish"
        }else{
            binding.submitBtn.text="submit"
        }

        binding.TVQuestion.text=soal.question
        binding.image.setImageResource(soal.image)
        binding.progressBar.progress=currentPosition
        binding.progressTxt.text="$currentPosition" + "/" + "${binding.progressBar.max}"
        binding.opt1.text=soal.answer1
        binding.opt2.text=soal.answer2
        binding.opt3.text=soal.answer3
        binding.opt4.text=soal.answer4
    }

   fun setDefault(){
       val questionList=ArrayList<TextView>()
       questionList.add(binding.opt1)
       questionList.add(binding.opt2)
       questionList.add(binding.opt3)
       questionList.add(binding.opt4)

       for (i in questionList){
           i.setTextColor(Color.parseColor("#656565"))
           i.typeface= Typeface.DEFAULT
           i.background=ContextCompat.getDrawable(this,R.drawable.tv_box_background)
       }




   }

    override fun onClick(v:View){
        when(v.id){
            R.id.opt1 -> setSelected(binding.opt1,1)
            R.id.opt2 -> setSelected(binding.opt2,2)
            R.id.opt3 -> setSelected(binding.opt3,3)
            R.id.opt4 -> setSelected(binding.opt4,4)
            R.id.submit_btn ->
                if (selectedOPT==0){
                    currentPosition++


                  if (currentPosition<= questionList.size){
                      setQuestion()
                  }else{
                      val intent= Intent(this,resault::class.java)
                      intent.putExtra(constant.username,takedname)
                      intent.putExtra(constant.correct_answers,totalCorrect)
                      intent.putExtra(constant.total_question,questionList.size)
                      startActivity(intent)
                      finish()
                  }

                }else{
                    val soal=questionList.get(currentPosition -1)

                    if (soal.correctAnswer!=selectedOPT){
                            answerView(selectedOPT,R.drawable.wronganswer)
                    }else{
                        totalCorrect++
                    }
                    answerView(soal.correctAnswer,R.drawable.correctacnser)


                    if (currentPosition == questionList!!.size){
                        binding.submitBtn.text = "finish"
                    }else{
                        binding.submitBtn.text="next question"
                    }

                    selectedOPT=0
                }




        }
    }


    fun setSelected(tv:TextView , position:Int){
        setDefault()
        selectedOPT = position
        tv.setTextColor(Color.parseColor("#000000"))
        tv.typeface= Typeface.DEFAULT_BOLD
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_box_background)

    }

    fun answerView(answer:Int , drawable:Int){
        when(answer){
            1->binding.opt1.background=ContextCompat.getDrawable(this,drawable)
            2->binding.opt1.background=ContextCompat.getDrawable(this,drawable)
            3->binding.opt1.background=ContextCompat.getDrawable(this,drawable)
            4->binding.opt1.background=ContextCompat.getDrawable(this,drawable)
        }
    }
}


