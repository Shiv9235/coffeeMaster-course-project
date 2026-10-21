package com.shivraj.coffeemasters

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shivraj.coffeemasters.pages.InfoPage
import com.shivraj.coffeemasters.pages.MenuPage
import com.shivraj.coffeemasters.pages.OffersPage
import com.shivraj.coffeemasters.pages.OrderPage
import com.shivraj.coffeemasters.ui.theme.CoffeeMastersTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Preview(showBackground = true)
@Composable
fun App_Preview() {
    val sampleMenu = listOf(
        Category(
            name = "Hot Coffee",
            products = mutableListOf(
                Product(1, "Latte", 2.99, "cappuccino.png"),
                Product(2, "Cappuccino", 3.49, "cappuccino.png")
            )
        )
    )
    val dataManager = DataManager(Application()).apply {
        menu = sampleMenu
    }
    CoffeeMastersTheme {
        App(dataManager)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(dataManager: DataManager) {
    var selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    AppTitle()
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            NavBar(
                selectedRoute = selectedRoute.value,
                onChange = { newRoute ->
                    selectedRoute.value = newRoute
                }
            )
        }
    ) { innerPadding ->
        when (selectedRoute.value) {
            Routes.MenuPage.route -> MenuPage(dataManager, modifier = Modifier.padding(innerPadding))
            Routes.OffersPage.route -> OffersPage(modifier = Modifier.padding(innerPadding))
            Routes.OrderPage.route -> OrderPage(dataManager, modifier = Modifier.padding(innerPadding))
            Routes.InfoPage.route -> InfoPage(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun AppTitle() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    {
        Image(
            painter = painterResource( id = R.drawable.logo),
            contentDescription = "App Logo"
        )
    }

}
