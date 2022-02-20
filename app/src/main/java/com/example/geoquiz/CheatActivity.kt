package com.example.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity




private const val EXTRA_ANSWER_IS_TRUE ="com.example.geoquiz.answer_is_true"
class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
      answerTextView = findViewById(R.id.lox)
        showAnswerButton = findViewById(R.id.show_answer_button)
        intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)





        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> "Правда"
                else -> "Не правда"
            }
            answerTextView.text = answerText
        }

    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent =
            Intent(packageContext, CheatActivity::class.java)
                .apply { putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue) }
    }
}