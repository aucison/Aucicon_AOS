package com.kt.jiratest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Dimension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kt.jiratest.ui.theme.AucisonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootScreen()
        }
    }
}

@Composable
fun RootScreen() {
    val searchQuery by remember { mutableStateOf("검색 필드") }
    AucisonTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(Modifier.fillMaxSize()) {
                Toolbar(searchQuery) {

                }
                Box(Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    Text("Main Page Area")
                }
                BottomNavigation()
            }
        }
    }
}

@Composable
fun Toolbar(query: String, onQueryChanged: (String) -> Unit) {
    Row(Modifier
        .padding(horizontal = 10.dp, vertical = 10.dp)
        .height(30.dp)
        .fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            modifier = Modifier.size(30.dp),
            contentDescription = "app_logo"
        )

        Spacer(Modifier.width(10.dp))

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
            modifier = Modifier.fillMaxWidth(),
        )

    }
}

@Composable
fun BottomNavigation() {
    Row(Modifier.padding(10.dp).height(50.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
            Text("Market", fontSize = 14.sp, color = Color.Black)
        }
        Box(Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
            Text("Sell", fontSize = 14.sp, color = Color.Black)
        }
        Box(Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
            Text("My Page", fontSize = 14.sp, color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RootScreen()
}