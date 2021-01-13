package com.example.startactivityforresult

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    companion object {
        private const val FIRST_ACTIVITY_REQUEST_CODE = 1
        private const val SECOND_ACTIVITY_REQUEST_CODE = 2

        const val NAME = "name"
        const val EMAIL = "email"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        snackBarBtn.setOnClickListener {
            Snackbar.make(linearLayout, "Hello there :)", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

        launch_first_activity.setOnClickListener {
            intent = Intent(this, FirstActivity::class.java)
            startActivityForResult(intent, FIRST_ACTIVITY_REQUEST_CODE)
        }

        launch_second_activity.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FIRST_ACTIVITY_REQUEST_CODE) {
                tv_first_activity_result.text = "First activity result success"
            } else if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                if (data != null) {
                    val name = data.getStringExtra(NAME)
                    val email = data.getStringExtra(EMAIL)

                    tv_second_activity_result.text = "Name: $name \nEmail: $email"

                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Cancelled", "Cancelled")
            Snackbar.make(
                linearLayout,
                "Do you want to see what toast says? ;)",
                Snackbar.LENGTH_LONG
            )
                .setAction("Show Now!") {
                    Toast.makeText(this, "Result Cancelled", Toast.LENGTH_SHORT).show()
                }.show()
        }
    }
}