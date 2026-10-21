package com.shivraj.coffeemasters.pages

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shivraj.coffeemasters.Category
import com.shivraj.coffeemasters.DataManager
import com.shivraj.coffeemasters.Product
import com.shivraj.coffeemasters.ui.theme.Alternative1
import com.shivraj.coffeemasters.ui.theme.CardBackground
import com.shivraj.coffeemasters.ui.theme.CoffeeMastersTheme

@Preview(showBackground = true)
@Composable
fun MenuPage_Preview() {
    val sampleMenu = listOf(
        Category(
            name = "Hot Coffee",
            products = mutableListOf(
                Product(1, "Latte", 2.99, "cappuccino.png"),
                Product(2, "Cappuccino", 3.49, "cappuccino.png"),
                Product(3, "Americano", 1.99, "blackamericano.png")
            )
        ),
        Category(
            name = "Cold Coffee",
            products = mutableListOf(
                Product(4, "Iced Coffee", 2.49, "icedcoffee.png"),
                Product(5, "Frappuccino", 3.99, "frappuccino.png")
            )
        )
    )
    val dataManager = DataManager(Application()).apply {
        menu = sampleMenu
    }
    CoffeeMastersTheme {
        MenuPage(dataManager = dataManager)
    }
}

@Composable
fun MenuPage(dataManager: DataManager, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(dataManager.menu) { category ->
            Text(
                text = category.name,
                color = Alternative1,
                modifier = Modifier.padding(10.dp)
            )
            category.products.forEach { product ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .background(CardBackground)
                        .padding(12.dp)
                ) {
                    ProductItem(
                        product = product,
                        onAdd = {
                            dataManager.cartAdd(it)
                        }
                    )
                }
            }
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun ProductItem(product: Product, onAdd: (Product) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Image for ${product.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("$${product.price.format(2)} ")
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Alternative1,
                    contentColor = Color.White
                ),
                onClick = {
                    onAdd(product)
                },
            ) {
                Text("Add")
            }
        }
    }
}
