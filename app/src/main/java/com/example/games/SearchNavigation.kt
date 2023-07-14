package com.example.games

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.games.appDestinations.NavigationDestination
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.theme.GameIcons

private const val TAG: String = "Search Screen"

const val searchRoute = "search_route"

object SearchScreenDestination : NavigationDestination {
    override val route: String = "search_route"
    override val titleRes: Int = R.string.search

}

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,

) {
    //NavController.addNavigator()
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = searchRoute) {
       SearchRoute(
           onBackClick = onBackClick,
           searchViewModel = viewModel()

        )
    }

}


/**
private fun NavController.Companion.addNavigator() {

}
*/

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,

    searchViewModel: SearchViewModel,
) {
   // val searchResultUiState by searchViewModel.searchResultUiState.collectAsStateWithLifecycle()
    //val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()
    SearchScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        //onClearRecentSearches = searchViewModel::clearRecentSearches,
       // onSearchQueryChanged = searchViewModel::onSearchQueryChanged,
        //onSearchTriggered = searchViewModel::onSearchTriggered,
        //searchQuery = searchQuery,
        //searchResultUiState = searchResultUiState,
    )
}




sealed interface SearchResultUiState {
    object Loading : SearchResultUiState
    /**
     * The state query is empty or too short. To distinguish the state between the
     * (initial state or when the search query is cleared) vs the state where no search
     * result is returned, explicitly define the empty query state.
     */
    object EmptyQuery : SearchResultUiState
    object LoadFailed : SearchResultUiState
    data class Success(
        val games: List<Game> = emptyList(),

        ) : SearchResultUiState {
        fun isEmpty(): Boolean = games.isEmpty()
    }
    object SearchNotReady : SearchResultUiState
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onClearRecentSearches: () -> Unit = {},
    onSearchQueryChanged : (String) -> Unit ={},
    onSearchTriggered: (String) -> Unit = {},
    searchQuery: String = "",

    searchResultUiState: SearchResultUiState = SearchResultUiState.Loading,
) {

    //variables Philip:
    val searchViewModel: SearchViewModel = viewModel(factory = AppViewModelProvider.Factory)

    val searchText by searchViewModel.searchText.collectAsState()
    val gameMix by searchViewModel.gameMix.collectAsState()
    val isSearching by searchViewModel.isSearching.collectAsState()
//end variables Philip

    Column(modifier = modifier) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))

        SearchToolbar(
            onBackClick = onBackClick,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
        /**
        when (searchResultUiState) {
        SearchResultUiState.Loading,
        SearchResultUiState.LoadFailed,
        -> Unit

        SearchResultUiState.SearchNotReady -> SearchNotReadyBody()
        SearchResultUiState.EmptyQuery,
        -> {

        if (recentSearchesUiState is RecentSearchQueriesUiState.Success) {
        RecentSearchesBody(
        onClearRecentSearches = onClearRecentSearches,
        onRecentSearchClicked = {
        onSearchQueryChanged(it)
        onSearchTriggered(it)
        },
        recentSearchQueries = recentSearchesUiState.recentQueries.map { it.query },
        )
        }
        }

        is SearchResultUiState.Success -> {
        if (searchResultUiState.isEmpty()) {
        EmptySearchResultBody(
        searchQuery = searchQuery,
        )
        //RECENT SEARCH:
        if (recentSearchesUiState is RecentSearchQueriesUiState.Success) {
        RecentSearchesBody(
        onClearRecentSearches = onClearRecentSearches,
        onRecentSearchClicked = {
        onSearchQueryChanged(it)
        onSearchTriggered(it)
        },
        recentSearchQueries = recentSearchesUiState.recentQueries.map { it.query },
        )
        }
        } else {
        SearchResultBody(
        games = searchResultUiState.games,
        onSearchTriggered = onSearchTriggered,
        searchQuery = searchQuery,
        )
        }
        }

        } */
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))

        //Philip Lacnker video:
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clickable { onBackClick }
        ) {

            TextField(
                value = searchText,
                onValueChange = searchViewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { /**Text(text = "Search")*/ },

                )
            Spacer(modifier = Modifier.height(16.dp))
            /**LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            ){
            items(gameMix){gameMix ->
            /** Text(
            text = "${gameMix.genre} ${gameMix.title}",
            modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            )*/
            Log.e(TAG, gameMix.title)
            }
            }
            }*/

        }
    }
}
@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String = "",
    onSearchTriggered: (String) -> Unit,

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = GameIcons.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.back_button,
                ),
            )
        }
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    searchQuery: String
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }

    androidx.compose.material3.TextField(
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = GameIcons.Search,
                contentDescription = stringResource(
                    id = R.string.search,
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                    },
                ) {
                    Icon(
                        imageVector = GameIcons.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            if (!it.contains("\n")) {
                onSearchQueryChanged(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}





@Composable
fun EmptySearchResultBody(
    searchQuery: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 48.dp),
    ) {
        val message = stringResource(R.string.no_results)
        val start = message.indexOf(searchQuery)
        androidx.compose.material3.Text(
            text = AnnotatedString(
                text = message,
                spanStyles = listOf(
                    AnnotatedString.Range(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start = start,
                        end = start + searchQuery.length,
                    ),
                ),
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp),
        )
        val games = stringResource(id = R.string.app_name)
        val tryAnotherSearchString = buildAnnotatedString {
            append("try another search")
            append(" ")
            withStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                ),
            ) {
                pushStringAnnotation(tag = games, annotation =games)
                append(games)
            }
            append(" ")
            //append( "")
        }
        ClickableText(
            text = tryAnotherSearchString,
            style = MaterialTheme.typography.bodyLarge.merge(
                TextStyle(
                    textAlign = TextAlign.Center,
                ),
            ),
            modifier = Modifier
                .padding(start = 36.dp, end = 36.dp, bottom = 24.dp)
                .clickable {},
        ) { offset ->
            tryAnotherSearchString.getStringAnnotations(start = offset, end = offset)
                .firstOrNull()
                ?.let {
                    //PENDING IMPLEMENTATION
                }
        }
    }
}

@Composable
fun SearchNotReadyBody() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 48.dp),
    ) {
        androidx.compose.material3.Text(
            text = stringResource(R.string.search_not_ready),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp),
        )
    }
}

@Composable
fun SearchResultBody(
    games: List<Game>,
    onSearchTriggered: (String) -> Unit,
    searchQuery: String = "",

    ) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .testTag("search:newsResources"),
        state = state,
    ) {
        if (games.isNotEmpty()) {
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                },
            ) {
                androidx.compose.material3.Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.app_name))
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .clickable { onSearchTriggered(searchQuery) },
                )
            }
            games.forEach { game ->
                val gameS = game.id
                item(
                    key = "gameS-$game", // Append a prefix to distinguish a key for news resources
                    span = {
                        GridItemSpan(maxLineSpan)
                    },
                ) {

                }
            }
        }


        }
    }


@Composable
fun RecentSearchesBody(
    onClearRecentSearches: () -> Unit,
    onRecentSearchClicked: (String) -> Unit,
    recentSearchQueries: List<String>,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            androidx.compose.material3.Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("recent searches")
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
            if (recentSearchQueries.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onClearRecentSearches()
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    Icon(
                        imageVector = GameIcons.Close,
                        contentDescription = stringResource(
                            id = R.string.search,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(recentSearchQueries) { recentSearch ->
                androidx.compose.material3.Text(
                    text = recentSearch,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clickable {
                            onRecentSearchClicked(recentSearch)
                        }
                        .fillMaxWidth(),
                )
            }
        }
    }
}


