package com.example.roomsiswa.repositori

import android.content.Context
import com.example.roomsiswa.data.DatabaseSiswa

// Interface yang berfungsi sebagai wadah (container) untuk repositori siswa.
interface ContainerApp{
    val repositoriSiswa : RepositoriSiswa
}

// Kelas yang mengimplementasikan interface ContainerApp, berisi repositori siswa.
class ContainerDataApp(private val context: Context): ContainerApp{
    //Repositori siswa diinisialisasi secara malas (lazy) menggunakan objek OfflineRepositoriSiswa.
    override val repositoriSiswa: RepositoriSiswa by lazy{
        OfflineRepositoriSiswa(DatabaseSiswa.getDatabase(context).siswaDao())
    }

}