package com.example.mycamera.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// Routes for the bottom bar
object MainRoutes {
    const val HOME = "home"
    const val SEARCH = "search"
    const val ADD_POST = "add_post"
    const val LIKED = "liked"
    const val PROFILE = "profile"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val scaffoldState = rememberModalBottomSheetState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // For now, we are creating placeholder screens
    // In a real app, you would create separate files for these.

    val homeScreen = @Composable { Text("Home Feed Screen") }
    val searchScreen = @Composable { Text("Search Users Screen") }
    val addPostScreen = @Composable { Text("Add Post Screen (Camera)") }
    val likedScreen = @Composable { Text("Liked Photos Screen") }
    val profileScreen = @Composable { ProfileContent(onLogout) } // A bit more complex


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Content of the Burger Menu
            AppDrawer(
                onLogout = onLogout
                // Add other navigation items here
            )
        }
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentRoute == MainRoutes.HOME,
                        onClick = { navController.navigate(MainRoutes.HOME) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                        label = { Text("Search") },
                        selected = currentRoute == MainRoutes.SEARCH,
                        onClick = { navController.navigate(MainRoutes.SEARCH) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Post") },
                        label = { Text("Post") },
                        selected = currentRoute == MainRoutes.ADD_POST,
                        onClick = { navController.navigate(MainRoutes.ADD_POST) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Liked") },
                        label = { Text("Liked") },
                        selected = currentRoute == MainRoutes.LIKED,
                        onClick = { navController.navigate(MainRoutes.LIKED) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = currentRoute == MainRoutes.PROFILE,
                        onClick = { navController.navigate(MainRoutes.PROFILE) }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MainRoutes.HOME,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(MainRoutes.HOME) { homeScreen() }
                composable(MainRoutes.SEARCH) { searchScreen() }
                composable(MainRoutes.ADD_POST) { addPostScreen() }
                composable(MainRoutes.LIKED) { likedScreen() }
                composable(MainRoutes.PROFILE) { profileScreen() }
            }
        }
    }
}


@Composable
fun ProfileContent(onLogout: () -> Unit) {
    // This would be a more complex screen showing user's posts, followers, etc.
    // For now, it just has a logout button.
    Button(onClick = onLogout) {
        Text("Logout")
    }
}

@Composable
fun AppDrawer(onLogout: () -> Unit) {
    // Placeholder for drawer content
    Column {
        Text("Settings")
        Text("Notifications")
        Text("Dark Mode")
        TextButton(onClick = onLogout) {
            Text("Logout")
        }
    }
}