package com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

@Composable
fun ShoppingListItem(
    list: List<Item>,
    onNavigate:()-> Unit
) {

  Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(top = LocalSpacing.current.medium),
      verticalAlignment = Alignment.CenterVertically
  ){
      Box(modifier = Modifier
          .size(50.dp)
          .clip(RoundedCornerShape(LocalSpacing.current.medium))
          .background(Color(0xffdbe9ab)),
      contentAlignment = Alignment.Center){
          Text(list.size.toString(),
            color = Color(0xff082d52),
          fontWeight = FontWeight.Bold)
      }

          LazyRow(
              modifier = Modifier
                  .fillMaxWidth(.6f)
                  .padding(start = 5.dp)
          ) {
              items(list.size) { index ->
                  Box(
                      modifier = Modifier
                          .size(40.dp)
                          .padding(end = LocalSpacing.current.extraSmall)

                          .clip(RoundedCornerShape(LocalSpacing.current.medium))
                          .shadow(elevation = 4.dp),
                      contentAlignment = Alignment.Center
                  ) {
                      Image(
                          painter = painterResource(id = list[index].image),
                          contentDescription = list[index].name,
                          contentScale = ContentScale.FillBounds
                      )
                  }
              }

      }
      IconButton(onClick = {onNavigate.invoke()}){
          Icon(imageVector = Icons.Default.ArrowForward,
              contentDescription = "Go to orders",
          tint = Color(0xff082d52))
      }
  }

}