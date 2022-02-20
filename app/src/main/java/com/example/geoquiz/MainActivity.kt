package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.Exception

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"


class MainActivity : AppCompatActivity() {


    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }


    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var cheatButton: Button
    private var currentResult = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState?.putInt(KEY_INDEX, quizViewModel.currentIndex)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)


        trueButton.setOnClickListener {

            checkAnswer(true)

        }
        falseButton.setOnClickListener {
            checkAnswer(false)


        }
        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()


        }
        cheatButton.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            startActivity(intent)
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextRestId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextRestId)


    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageAnswer = if (userAnswer == correctAnswer) {
            currentResult += 10
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageAnswer, Toast.LENGTH_SHORT).show()
    }


}