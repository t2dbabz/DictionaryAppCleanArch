package com.example.dictionary.feature_dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionary.R
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.presentation.components.WordInfoItem
import com.example.dictionary.ui.theme.DictionaryTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WordInfoScreen(viewModel: WordInfoViewModel = hiltViewModel()) {
    val state = viewModel.wordInfoState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->

            when(event) {
                is WordInfoViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }


    Scaffold(topBar = {
                      TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))})
    }, scaffoldState = scaffoldState) {paddingValues ->
        Box(modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(paddingValues)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                TextField(
                    value = viewModel.searchQuery.value,
                    onValueChange = {
                        viewModel.searchQuery(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Search..") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()){

                    items(state.wordInfoItems.size) {index->
                        val wordInfo = state.wordInfoItems[index]

                        if (index > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        WordInfoItem(wordInfo)

                        if (index < state.wordInfoItems.size - 1) {
                            Divider()
                        }
                    }
                }
            }
        }

    }
}


@Preview
@Composable
fun WordInfoScreenPreview() {
    DictionaryTheme {
        WordInfoScreen()
    }

}