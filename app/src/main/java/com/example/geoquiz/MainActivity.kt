package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {


    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView
    private var currentIndex = 0
    private var currentResult = 0
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_america_money,false),
        Question(R.string.question_russia_money,false),
        Question(R.string.question_russia,true),
        Question(R.string.question_armenia,true)


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
            nextQuestion()
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
            nextQuestion()

        }
        nextButton.setOnClickListener {
            if (currentIndex == questionBank.size - 1) {
                currentIndex = 0
                updateQuestion()
            }
            nextQuestion()
        }

        backButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.size - 1
                updateQuestion()
            }
            backQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextRestId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextRestId)


    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageAnswer = if (userAnswer == correctAnswer) {
            currentResult += 10
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageAnswer, Toast.LENGTH_SHORT).show()
    }

    private fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        updateQuestion()
    }

    private fun backQuestion() {
        currentIndex = (currentIndex - 1) % questionBank.size
        updateQuestion()
    }
}