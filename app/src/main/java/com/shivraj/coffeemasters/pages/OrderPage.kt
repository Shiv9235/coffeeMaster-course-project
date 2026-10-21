package com.shivraj.coffeemasters.pages

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shivraj.coffeemasters.DataManager
import com.shivraj.coffeemasters.ItemInCart
import com.shivraj.coffeemasters.Product
import com.shivraj.coffeemasters.ui.theme.CoffeeMastersTheme
import com.shivraj.coffeemasters.ui.theme.Primary

@Preview(showBackground = true)
@Composable
fun OrderPage_Preview() {
    val dataManager = DataManager(Application()).apply {
        cart = listOf(
            ItemInCart(Product(1, "Latte", 2.99, ""), 2),
            ItemInCart(Product(2, "Cappuccino", 3.49, ""), 1)
        )
    }
    CoffeeMastersTheme {
        OrderPage(dataManager = dataManager)
    }
}

@Composable
fun OrderPage(dataManager: DataManager, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        if (dataManager.cart.isEmpty()) {
            item {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Your Cart is Empty !!!",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            items(dataManager.cart) { item ->
                CartItem(item, onDelete = { product ->
                    dataManager.cartRemove(product)
                })
            }
        }
    }
}

@Composable
fun CartItem(it: ItemInCart, onDelete: (Product) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("${it.quantity}x")

        Text(
            it.product.name,
            modifier = Modifier.width(150.dp)
        )

        Text(
            "$${(it.quantity * it.product.price).format(2)}",
            modifier = Modifier.width(50.dp)
        )
        Image(
            imageVector = Icons.Rounded.Delete,
            contentDescription = "Delete",
            colorFilter = ColorFilter.tint(Primary),
            modifier = Modifier.clickable {
                onDelete(it.product)
            }
        )
    }
}
