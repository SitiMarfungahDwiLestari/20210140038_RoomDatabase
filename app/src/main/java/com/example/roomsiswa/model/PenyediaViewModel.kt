package com.example.roomsiswa.model


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomsiswa.AplikasiSiswa

// Ini adalah objek yang menyediakan factory (pabrik) untuk membuat ViewModel.
object PenyediaViewModel {     // Factory adalah fungsi yang mengembalikan ViewModel sesuai dengan jenisnya.
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)         // Inisialisasi HomeViewModel dengan repositori Siswa.
        }

        // Inisialisasi EntryViewModel dengan repositori Siswa.
        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        // Inisialisasi DetailsViewModel dengan saved state handle dan repositori Siswa.
        initializer{
            DetailsViewModel(
                createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa
            )
        }

        // Inisialisasi EditViewModel dengan saved state handle dan repositori Siswa.
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiSiswa().container.repositoriSiswa
            )
        }
    }
}

//Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari [AplikasiSiswa]
fun CreationExtras.aplikasiSiswa():AplikasiSiswa = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)