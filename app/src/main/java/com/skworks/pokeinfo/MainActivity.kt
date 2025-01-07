package com.skworks.pokeinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.skworks.pokeinfo.ui.compose.MainNavGraph
import com.skworks.pokeinfo.ui.theme.PokeInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeInfoTheme {
                Surface {
                    MainNavGraph(navController = rememberNavController())
                }
            }
        }
    }
}