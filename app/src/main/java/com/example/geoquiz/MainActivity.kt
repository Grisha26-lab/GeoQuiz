package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEATERS = 0


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
            val answerTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEATERS)
        }

        updateQuestion()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEATERS) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    private fun updateQuestion() {
        val questionTextRestId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextRestId)


    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageId = when {
            quizViewModel.isCheater -> resources.getString(R.string.judgment_toast)
            userAnswer == correctAnswer -> resources.getString(R.string.incorrect_toasts)
            else -> resources.getString(R.string.incorrect_toasts)
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }


}