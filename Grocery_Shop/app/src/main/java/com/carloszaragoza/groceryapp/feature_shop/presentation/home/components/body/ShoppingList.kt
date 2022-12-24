package com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carloszaragoza.groceryapp.R
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

@Composable
fun ShoppingList(
    listOfOrders: List<Item>,
    onNavigate:()-> Unit
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = LocalSpacing.current.small,
                    end = LocalSpacing.current.small,
                    top = LocalSpacing.current.medium)
        .height(125.dp),
        shape = RoundedCornerShape(LocalSpacing.current.small),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier
                .fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .fillMaxHeight()
                    .padding(start = LocalSpacing.current.small),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Shopping list",
                    color = Color(0xff000000),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold)
                if (listOfOrders.isNotEmpty())
                    ShoppingListItem(list = listOfOrders){
                        onNavigate.invoke()
                    }
                else
                    Text("No Orders yet...",
                        color = Color(0xff000000).copy(0.5f),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal)

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.grocery_basket),
                    contentDescription = "Grocery Basket",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

}