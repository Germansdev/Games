package com.example.games.ui.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.games.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float = 0.0F, //try with Float?
    stars: Int = 5,
    starColor: Color = colorResource(id = R.color.orange_star),
    onRatingChange: (Float) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var isHalfStar = (rating % 1) != 0.0F

    Row(
        modifier = Modifier

    ) {

        if (rating.toDouble() == 0.0) {
            for (index in 1..5) {
                Icons.Rounded.StarOutline
            }
        }

        for (index in 1..stars) {

            androidx.compose.material.Icon(
                modifier = modifier.clickable { onRatingChange(index.toFloat()) },
                contentDescription = null,
                tint = starColor,
                imageVector =
                if (index <= rating) {
                    Icons.Rounded.Star
                } else {
                    if (isHalfStar) {
                        isHalfStar = false
                        Icons.Rounded.StarHalf
                    } else {
                        Icons.Rounded.StarOutline
                    }
                }
            )
        }
    }
}



