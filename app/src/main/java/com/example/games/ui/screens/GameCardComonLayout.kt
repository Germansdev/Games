@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.games.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.GameViewModel


//this is reusableCode:
@Composable
fun GameCardComonContent(
    game: Game,
) {
    val coroutineScope = rememberCoroutineScope()

    ElevatedCard(
        modifier = Modifier//modifier
            .padding(8.dp)
            //.fillMaxSize()
            .height(100.dp),
        //.aspectRatio(1f),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),
        /**
        .fillMaxSize()
        .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),*/
        //onClick = {onClick(game.id)},

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically


            ) {
            AsyncImage(

                modifier = Modifier
                    .heightIn()
                    .padding(8.dp)
                // .aspectRatio(.8f)
                ,
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(game.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
            Column(modifier = Modifier) {

                Text(
                    text = game.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}


