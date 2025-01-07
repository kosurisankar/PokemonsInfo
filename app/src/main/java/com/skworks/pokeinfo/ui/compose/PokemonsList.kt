package com.skworks.pokeinfo.ui.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.io.Resources
import com.skworks.pokeinfo.R
import com.skworks.pokeinfo.model.NamedApiResource
import com.skworks.pokeinfo.util.ChangeColor
import com.skworks.pokeinfo.viewmodel.GetPokemonListViewModel
import kotlinx.coroutines.launch

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun AllPokemonsList(viewModel: GetPokemonListViewModel, navController: NavController) {
    // Observe the list of pokemons from the viewModel
    val pokemonsList: List<NamedApiResource> by viewModel.pokemonsList.observeAsState(emptyList())
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    // State to hold the search query
    var searchQuery by remember { mutableStateOf("") }
    // Filtered list based on search query
    val filteredList = pokemonsList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    // Remember the scroll state and coroutine scope for the scrolling action
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val cardColors = listOf(
        colorResource(id = R.color.card_color_1),
        colorResource(id = R.color.card_color_2),
        colorResource(id = R.color.card_color_3),
        colorResource(id = R.color.card_color_4),
        colorResource(id = R.color.card_color_5),
        colorResource(id = R.color.card_color_6),
        colorResource(id = R.color.card_color_7),
        colorResource(id = R.color.card_color_8),
        colorResource(id = R.color.card_color_9),
        colorResource(id = R.color.card_color_10)
    )
    // Main Column for the screen layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp) // Optional padding for the whole column
    ) {
        // Title of the screen
        HeaderText(
            text = stringResource(id = R.string.pokemons_list), modifier = Modifier.padding(
                bottom = 8.dp,
                top = 8.dp
            ), fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Pokémon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            if (isLoading) {
                // Show progress bar while loading
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            } else {
                // LazyColumn for displaying the list of Pokemons
                LazyColumn(
                    state = scrollState,
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between items
                ) {
                    itemsIndexed(filteredList) { index, item ->
                        val cardColor = cardColors[index % cardColors.size]

                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)// Added shadow as an alternative to elevation
                                .clickable {
                                    Log.e("Color Code :::", cardColor.toString())
                                    navController.navigate("Pokemon_details/${item.id}/${cardColor.toArgb()}")
                                }, colors = CardDefaults.cardColors(containerColor = cardColor)
                        ) {
                            Row {
                                Box(
                                    modifier = Modifier.wrapContentSize(), // Set a fixed height for the card (optional)
                                    contentAlignment = Alignment.CenterStart // Center content in the Box
                                ) {
                                    LargeText(
                                        text = "#" + item.id.toString(),
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(16.dp),
                                        color = ChangeColor().getDarkerColor(cardColor,0.3f)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(), // Set a fixed height for the card (optional)
                                    contentAlignment = Alignment.Center // Center content in the Box
                                ) {
                                    LargeText(
                                        text = item.name.replaceFirstChar { it.uppercase() },
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(16.dp),
                                       color =  ChangeColor().getDarkerColor(cardColor,0.3f)
                                    )
                                }
                            }
                        }
                    }
                }
                if (filteredList.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()// Optional padding for the whole column
                        ,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MediumText(text = stringResource(id = R.string.no_pokemon_found))
                    }
                }
                // Scroll to top button, shown only if not at the top of the list
                if (scrollState.firstVisibleItemIndex > 0) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                scrollState.animateScrollToItem(0) // Animate scroll to top
                            }
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                CircleShape
                            ) // Optional background
                            .size(48.dp) // Adjust size as needed
                    ) {
                        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Scroll to top")
                    }
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewListScreen() {
    AllPokemonsList(viewModel = GetPokemonListViewModel(), navController = rememberNavController())
}