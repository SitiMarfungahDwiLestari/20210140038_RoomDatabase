package com.example.roomsiswa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version = 1, exportSchema = false) //Anotasi yang menandakan bahwa kelas ini adalah kelas database Room. Mendeklarasikan entitas (Siswa::class), versi database, dan menonaktifkan ekspor skema.
abstract class DatabaseSiswa : RoomDatabase(){ //Mendeklarasikan kelas DatabaseSiswa yang merupakan turunan dari RoomDatabase.
    abstract fun siswaDao() : SiswaDao //Mendeklarasikan fungsi abstrak untuk mendapatkan objek DAO (SiswaDao).

    companion object{ //Mendeklarasikan objek companion, yang berfungsi seperti variabel statis di dalam kelas.
        @Volatile //agar dapat diakses secara aman oleh beberapa thread.
        private var Instance: DatabaseSiswa? = null //Mendeklarasikan variabel instance yang menggunakan kata kunci

        fun getDatabase(context: Context): DatabaseSiswa{ //Mendeklarasikan fungsi untuk mendapatkan instance database.
            return (Instance?: synchronized(this){ //Mengembalikan instance database jika sudah ada, jika tidak, menggunakan blok synchronized untuk membuat instance baru.
                Room.databaseBuilder(context, // Membangun objek database menggunakan databaseBuilder.
                    DatabaseSiswa::class.java, //Parameter untuk membangun database, termasuk Context, kelas database, dan nama database.
                    "siswa_database") //nama untuk menamakan databasenya
                    .build().also{Instance=it} //: Membangun database dan mengatur nilai instance dengan database yang baru.
            })
        }
    }
}



//Urutannya :
// 1. Database (tabel, DML/Dao, database)
// 2. Repositori (repositori, offline, container)
// 3. View Model
// 4. Aplikasi
// 5. Penyedia View Model
// 6. Destinasi Navigasi
// 7. Halaman-halaman
// 8. Peta Navigasi (Pengelola Halaman)