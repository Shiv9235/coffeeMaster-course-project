package com.shivraj.coffeemasters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.shivraj.coffeemasters.ui.theme.CoffeeMastersTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var dataManager = ViewModelProvider(this).get(DataManager::class.java)
        enableEdgeToEdge()
        setContent {
            CoffeeMastersTheme {
                Surface( ){
                    App(dataManager)
                }
            }
        }
    }
}

