package com.example.roomsiswa.navigasi


import android.icu.text.CaseMap.Title
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomsiswa.R
import com.example.roomsiswa.ui.theme.halaman.DestinasiEntry
import com.example.roomsiswa.ui.theme.halaman.DestinasiHome
import com.example.roomsiswa.ui.theme.halaman.DetailScreen
import com.example.roomsiswa.ui.theme.halaman.DetailsDestination
import com.example.roomsiswa.ui.theme.halaman.EntrySiswaScreen
import com.example.roomsiswa.ui.theme.halaman.HomeScreen
import com.example.roomsiswa.ui.theme.halaman.ItemEditDestination
import com.example.roomsiswa.ui.theme.halaman.ItemEditScreen


// Inisialisasi Navigasi
@Composable
fun SiswaApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

// Komponen TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {                 // Tombol Kembali
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

// Host Navigasi dan Mendefinisikan Destinasi
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier)
    {
        // Destinasi Home
        composable(DestinasiHome.route){
            HomeScreen(navigateToItemEntry = { navController.navigate((DestinasiEntry.route)) },
                onDetailClick = {
                    navController.navigate("${DetailsDestination.route}/$it")
                })
        }
        // Destinasi Entry
        composable(DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = {navController.popBackStack()})
        }
        // Destinasi Detail dengan Parameter
        composable(
            DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.siswaIdArg){
                type = NavType.IntType
            })
        ){
            DetailScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = {
                    navController.navigate("${ItemEditDestination.route}/$it")
                }
            )
        }
        // Destinasi Edit Item dengan Parameter
        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg){
                type = NavType.IntType
            })
        ){
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}