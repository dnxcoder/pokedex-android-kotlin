package com.example.tibiaclone
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.tibiaclone.ui.navigation.AppNavHost
import com.example.tibiaclone.ui.theme.TibiaCloneTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TibiaCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    PokedexApp()
                }
            }
        }
    }
}

@Composable
fun PokedexApp() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TibiaCloneTheme {

    }
}