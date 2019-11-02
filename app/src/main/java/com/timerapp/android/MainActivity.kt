package com.timerapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.timerapp.android.ui.TaskTimerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,TaskTimerFragment.newInstance())
                .commitNow()
        }
    }
}
