package com.notiflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // set the content view to the existing layout so R is referenced and generated
    setContentView(R.layout.activity_main)
  }
}
