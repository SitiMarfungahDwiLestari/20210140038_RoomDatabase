package com.example.roomsiswa.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsiswa.repositori.RepositoriSiswa
import com.example.roomsiswa.ui.theme.halaman.DetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Ini adalah deklarasi kelas DetailsViewModel yang merupakan bagian dari Android's ViewModel untuk UI.
class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
): ViewModel(){

    // Mendapatkan ID siswa dari navigasi menggunakan SavedStateHandle.
    private val siswaId: Int = checkNotNull(savedStateHandle[DetailsDestination.siswaIdArg])

    // Mendeklarasikan StateFlow untuk mengelola UI state.
    val uiState: StateFlow<ItemDetailsUiState> =
        // Mengambil data siswa dari repositori dan mengonversinya ke dalam UI state.
        repositoriSiswa.getSiswaStream(siswaId)
            .filterNotNull()
            .map{
                ItemDetailsUiState(detailSiswa = it.toDetailSiswa())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    // Fungsi untuk menghapus data siswa dari repositori.
    suspend fun deleteItem(){
        repositoriSiswa.deleteSiswa(uiState.value.detailSiswa.toSiswa())
    }

    // Companion object digunakan untuk menyimpan konstanta, dalam hal ini, timeout millis.
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

// Data class untuk menyimpan UI state untuk halaman detail item.
data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailSiswa:DetailSiswa = DetailSiswa(),
)