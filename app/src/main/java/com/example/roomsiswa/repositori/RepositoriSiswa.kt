package com.example.roomsiswa.repositori

import com.example.roomsiswa.data.Siswa
import kotlinx.coroutines.flow.Flow

// Interface untuk mendefinisikan operasi-operasi dasar pada data siswa.
interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>     // Fungsi ini memberikan aliran data (stream) yang terus menerus dari daftar semua siswa.

    fun getSiswaStream(id: Int): Flow<Siswa?>     // Fungsi ini memberikan aliran data (stream) yang terus menerus dari satu siswa berdasarkan ID-nya.

    suspend fun insertSiswa(siswa: Siswa)     // Fungsi ini menambahkan siswa baru ke dalam database.


    suspend fun deleteSiswa(siswa: Siswa)    // Fungsi ini menghapus siswa dari database.

    suspend fun updateSiswa(siswa: Siswa)     // Fungsi ini memperbarui data siswa yang sudah ada di database.
}