package com.jglee.aucison.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jglee.aucison.R
import com.jglee.aucison.data.main.Screen
import com.jglee.aucison.presentation.main.MainPage
import com.jglee.aucison.presentation.market.MarketPage
import com.jglee.aucison.presentation.root.Drawer
import com.jglee.aucison.presentation.root.RightModalDrawer
import com.jglee.aucison.presentation.search.SearchPage
import com.jglee.aucison.ui.theme.AucisonTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootScreen()
        }
    }
}

@Composable
fun RootScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    AucisonTheme {
        RightModalDrawer(
            drawerState = scaffoldState.drawerState,
            drawerContent = {
                Drawer(navController) {
                    coroutineScope.launch(Dispatchers.Main) {
                        scaffoldState.drawerState.close()
                    }
                }
            }
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    Toolbar(
                        onSearched = {
                            navController.navigate("${Screen.SEARCH}/$it")
                        },
                        onClickMenu = {
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
                        MarketPage()
                    }
                    composable(route = Screen.SELL.name) {
                        //                        MainPage()
                    }
                    composable(route = Screen.MY_PAGE.name) {
                        //                        MainPage()
                    }
                    composable(
                        route = "${Screen.SEARCH.name}/{query}",
                        arguments = listOf(
                            navArgument("query") { type = NavType.StringType }
                        )
                    ) {
                        val searchQuery = it.arguments?.getString("query") ?: return@composable
                        SearchPage(query = searchQuery)
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Toolbar(onSearched: (String) -> Unit, onClickMenu: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 10.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            modifier = Modifier.size(30.dp),
            contentDescription = "app_logo"
        )

        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp)
                ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = stringResource(R.string.toolbar_search_placeholder),
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearched(searchQuery)
            })
        )

        Button(
            onClick = onClickMenu,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(
                0.dp,
                Color.White
            ),
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

    Divider(
        color = Color.LightGray,
        thickness = 1.dp
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RootScreen()
}