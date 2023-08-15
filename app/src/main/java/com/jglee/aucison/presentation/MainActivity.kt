package com.jglee.aucison.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jglee.aucison.R
import com.jglee.aucison.data.main.Screen
import com.jglee.aucison.presentation.main.MainPage
import com.jglee.aucison.presentation.root.Drawer
import com.jglee.aucison.presentation.root.RightModalDrawer
import com.jglee.aucison.ui.theme.AucisonTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    AucisonTheme {
        RightModalDrawer(
            drawerState = scaffoldState.drawerState,
            drawerContent = { Drawer(navController) }
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    Toolbar(searchQuery, onQueryChanged = {}, onClickMenu = {
                        coroutineScope.launch(Dispatchers.Main) {
                            scaffoldState.drawerState.run {
                                if (isOpen) close()
                                else open()
                            }
                        }

                    })
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.MAIN.name,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(route = Screen.MAIN.name) {
                        MainPage()
                    }
                    composable(route = Screen.MARKET.name) {
//                        MainPage()
                    }
                    composable(route = Screen.SELL.name) {
//                        MainPage()
                    }
                    composable(route = Screen.MY_PAGE.name) {
//                        MainPage()
                    }
                }
            }

        }
    }
}

@Composable
fun Toolbar(query: String, onQueryChanged: (String) -> Unit, onClickMenu: () -> Unit) {
    Column {
        Row(
            Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .height(30.dp)
                .fillMaxWidth()
        ) {
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
                modifier = Modifier.weight(1f),
            )

            Spacer(Modifier.width(10.dp))

            Button(
                onClick = onClickMenu,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(0.dp, Color.White),
                shape = RectangleShape,
                elevation = ButtonDefaults.elevation(0.dp),
                contentPadding = PaddingValues(5.dp),
                modifier = Modifier.size(30.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_menu),
                    contentDescription = "open drawer"
                )
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RootScreen()
}