package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button
    private lateinit var questionTextView: TextView
    private var currentIndex = 0
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
           checkAnswer(true)
            currentIndex++
            updateQuestion()
        }
        falseButton.setOnClickListener {
          checkAnswer(false)
            currentIndex++
            updateQuestion()
        }
        nextButton.setOnClickListener {
            if (currentIndex == questionBank.size - 1) {
                currentIndex = 0
            }
            (currentIndex++) % questionBank.size
            updateQuestion()
        }

        backButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.size - 1
            }
            (currentIndex--) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }


    private fun updateQuestion() {
        val questionTextRestId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextRestId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageAnswer = if (userAnswer==correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this,messageAnswer,Toast.LENGTH_SHORT).show()
    }
}