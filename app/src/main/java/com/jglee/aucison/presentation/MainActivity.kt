package com.jglee.aucison.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jglee.aucison.R
import com.jglee.aucison.data.main.Screen
import com.jglee.aucison.presentation.buy.BuyProductPage
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
                        navController = navController,
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
                    val onClickProduct = { id: Int ->
                        navController.navigate("${Screen.BUY}/$id")
                    }

                    composable(route = Screen.MAIN.name) {
                        MainPage(onClickProduct)
                    }
                    composable(route = Screen.MARKET.name) {
                        MarketPage(onClickProduct)
                    }
                    composable(route = Screen.SELL.name) {
                        //                        MainPage()
                    }
                    composable(route = Screen.MY_PAGE.name) {
                        //                        MainPage()
                    }
                    composable(
                        route = "${Screen.SEARCH}/{query}",
                        arguments = listOf(
                            navArgument("query") { type = NavType.StringType }
                        )
                    ) {
                        val searchQuery = it.arguments?.getString("query") ?: return@composable
                        SearchPage(
                            query = searchQuery,
                            onClick = onClickProduct
                        )
                    }
                    composable(
                        route = "${Screen.BUY}/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) {
                        val productId = it.arguments?.getInt("id") ?: return@composable
                        BuyProductPage(
                            navController = navController,
                            id = productId
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun Toolbar(navController: NavController, onClickMenu: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(key1 = currentRoute) {
        Log.d(
            "screen",
            "$currentRoute"
        )
        if (currentRoute?.contains(Screen.SEARCH.name) == true) return@LaunchedEffect
        searchQuery = ""
    }

    Column {
        Row(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "app_logo",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate(Screen.MAIN.name)
                    },
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
                    navController.navigate("${Screen.SEARCH}/$searchQuery")
                    focusManager.clearFocus()
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
            thickness = 1.dp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RootScreen()
}