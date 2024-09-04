package com.example.fidabirdsmania

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

@Composable
fun BottomBarItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(imageVector = icon, contentDescription = label)
        }
        Text(text = label)
    }
}

@Composable
fun DrawerMenuItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, fontSize = 18.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val images = listOf(
        R.drawable.fida0,
        R.drawable.fida1,
        R.drawable.fida2
    )
    val categories = listOf(
        CategoryItem(R.drawable.birds_all_products, "Birds All Products"),
        CategoryItem(R.drawable.mixed_seeds, "Mixed Seeds For Birds"),
        CategoryItem(R.drawable.wooden_toys, "Wooden Toys & Trees"),
        CategoryItem(R.drawable.pallets_for_parrots, "Pallets For Parrots"),
        CategoryItem(R.drawable.nest_box, "Nest Box & Nesting Material"),
        CategoryItem(R.drawable.multivitamins, "Multivitamin in Calcium"),
        CategoryItem(R.drawable.cages_and_birdhouses, "Cages And Birdhouses"),
        CategoryItem(R.drawable.seeds_for_birds, "Seeds For Birds"),
        CategoryItem(R.drawable.hand_feeding, "Hand Feeding Formula"),
        CategoryItem(R.drawable.other_accessories, "Other Accessories"),
        CategoryItem(R.drawable.best_seller, "Best Seller"),
        CategoryItem(R.drawable.know_more_about_birds, "Know More About Birds")
    )
    var menuExpanded by remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .padding(start = 16.dp, top = 24.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Person, contentDescription = "User Icon", modifier = Modifier.size(40.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Login | Register", fontSize = 18.sp)
                }
                Divider(color = Color.Gray, thickness = 1.dp)
                DrawerMenuItem(icon = Icons.Default.Home, label = "Home") { /* Handle Home click */ }
                DrawerMenuItem(icon = Icons.Default.Info, label = "About Us") { /* Handle About Us click */ }
                DrawerMenuItem(icon = Icons.Default.Face, label = "Pets Careing") { /* Handle Dogs & Puppies click */ }
                DrawerMenuItem(icon = Icons.Default.Send, label = "Our Products") { /* Handle Our Products click */ }
                DrawerMenuItem(icon = Icons.Default.Favorite, label = "Our Charity") { /* Handle Our Charity click */ }
                DrawerMenuItem(icon = Icons.Default.Email, label = "Contact Us") { /* Handle Contact Us click */ }
                DrawerMenuItem(icon = Icons.Default.Info, label = "Know More About Birds") { /* Handle Know More About Birds click */ }
                Spacer(modifier = Modifier.weight(1f))
                DrawerMenuItem(icon = Icons.Default.Settings, label = "App Settings") { /* Handle App Settings click */ }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Fida Birds Mania") },
                        navigationIcon = {
                            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* Handle search icon click */ }) {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            }
                            IconButton(onClick = { navController.navigate("wishlist_screen") }) {
                                Icon(Icons.Default.FavoriteBorder, contentDescription = "Wishlist")
                            }
                            IconButton(onClick = { navController.navigate("cart_screen") }) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                            }
                        }
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        content = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                            ) {
                                BottomBarItem(
                                    icon = Icons.Default.Home,
                                    label = "HOME",
                                    onClick = { /* Handle Home button click */ }
                                )
                                BottomBarItem(
                                    icon = Icons.Default.ShoppingCart,
                                    label = "CART",
                                    onClick = { navController.navigate("cart_screen") }
                                )
                                BottomBarItem(
                                    icon = Icons.Default.FavoriteBorder,
                                    label = "WISHLIST",
                                    onClick = { navController.navigate("wishlist_screen") }
                                )
                                BottomBarItem(
                                    icon = Icons.Default.Person,
                                    label = "LOGIN",
                                    onClick = { navController.navigate("login_screen") }
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AutoScrollingCarousel(images = images, interval = 2000)
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(categories) { category ->
                            CategoryItemView(item = category) {
                                // Handle category click
                            }
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoScrollingCarousel(images: List<Int>, interval: Long) {
    val pagerState = rememberPagerState { images.size }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(interval)
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % images.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        Image(
            painter = painterResource(id = images[page]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 1f - (pagerState.currentPageOffsetFraction * 0.2f)
                }
        )
    }
}