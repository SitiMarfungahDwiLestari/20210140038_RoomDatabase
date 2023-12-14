package com.example.roomsiswa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsiswa.repositori.RepositoriSiswa
import com.example.roomsiswa.ui.theme.halaman.ItemEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel(){

    // State yang digunakan untuk mengelola tampilan UI
    var siswaUiState by mutableStateOf(UIStateSiswa())
        private set
    // ID siswa yang sedang diedit
    private val itemId: Int = 0 /*0*/ //checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    // Inisialisasi ViewModel
    init {
        // Melakukan operasi asinkron untuk mendapatkan data siswa
        viewModelScope.launch {
            // Mengambil data siswa dari repository
            siswaUiState = repositoriSiswa.getSiswaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }

    // Fungsi untuk memperbarui data siswa
    suspend fun updateSiswa(){
        // Melakukan validasi input sebelum memperbarui data siswa
        if(validasiInput(siswaUiState.detailSiswa)){
            repositoriSiswa.updateSiswa(siswaUiState.detailSiswa.toSiswa())
        }
        else{
            println("Data Tidak Valid")
        }
    }

    // Fungsi untuk memperbarui state UI
    fun updateUiState(detailSiswa: DetailSiswa){
        siswaUiState =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    // Fungsi untuk melakukan validasi input
    private fun validasiInput(uiState: DetailSiswa = siswaUiState.detailSiswa) : Boolean{
        return with(uiState){
            // Valid jika nama, alamat, dan nomor telepon tidak kosong
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
}