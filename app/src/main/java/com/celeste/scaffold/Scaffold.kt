package com.celeste.scaffold

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldView() {
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        gesturesEnabled = true ,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                Modifier.padding (0.dp, 0.dp, 50.dp, 0.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.padding(12.dp))
                    NavigationDrawerItem(
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = "Home"
                            )
                        },
                        onClick = {

                        },
                        selected = true
                    )

                    NavigationDrawerItem(
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = "Listado"
                            )
                        },
                        onClick = {},
                        selected = false
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBarView {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    } },
                bottomBar = { BottomNavigationView() },
                floatingActionButton = { FabView() },
                floatingActionButtonPosition = FabPosition.Center,
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                content = { padding ->
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .background(Color.Red)
                            .padding(padding)
                    ){

                    }
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(onClickIcon: (String) -> Unit) {
    TopAppBar(
        title = { Text(text = "Primera toolbar")},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { onClickIcon("Menú")}) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Busquéda") }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
            IconButton(onClick = { onClickIcon("Configuraciónes") }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "")
            }
        }
    )
}

@Composable
fun BottomNavigationView() {
    var index by rememberSaveable { mutableStateOf(0) }

    NavigationBar(
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "") },
            label = { Text("Home") },
            selected = index == 0,
            onClick = { index = 0 }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "") },
            label = { Text("Favorite") },
            selected = index == 1,
            onClick = { index = 1 }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "") },
            label = { Text("Person") },
            selected = index == 2,
            onClick = { index = 2 }
        )
    }
}

@Composable
fun FabView() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Floating Button")
    }
}
