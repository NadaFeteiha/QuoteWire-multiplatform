package com.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.myapplication.ui.compose.QuoteNavGraph
import com.myapplication.ui.compose.START_DESTINATION

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            QuoteNavGraph(navController = navController, START_DESTINATION)
        }
    }
}