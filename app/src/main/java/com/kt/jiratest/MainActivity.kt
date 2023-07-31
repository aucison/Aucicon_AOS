package com.kt.jiratest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kt.jiratest.ui.theme.AucisonTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (searchQuery, queryChanged) = remember { mutableStateOf("") }
            AucisonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row {
                        Toolbar(searchQuery, queryChanged)
                        Box(Modifier.fillMaxHeight())
                        BottomNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun Toolbar(query: String, onQueryChanged: (String) -> Unit) {
    Column(Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
        Image(painter = painterResource(R.drawable.ic_logo), modifier = Modifier.size(31.dp), contentDescription = "logo")
        OutlinedTextField(query, onQueryChanged)
    }
}

@Composable
fun BottomNavigation() {

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name! JJTJvhcT")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AucisonTheme {
        Greeting("Android")
    }
}