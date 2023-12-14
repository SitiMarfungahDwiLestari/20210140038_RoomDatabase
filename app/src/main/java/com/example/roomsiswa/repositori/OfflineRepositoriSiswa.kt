package com.example.roomsiswa.repositori

import com.example.roomsiswa.data.Siswa
import com.example.roomsiswa.data.SiswaDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriSiswa(private val siswaDao: SiswaDao):RepositoriSiswa{ //Ini adalah kelas yang bertanggung jawab untuk berinteraksi dengan data siswa di database melalui DAO (Data Access Object) yang disediakan oleh Room.
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa() //Mengembalikan aliran (Flow) dari semua data siswa dari database.

    override fun getSiswaStream(id: Int): Flow<Siswa?> = siswaDao.getSiswa(id) //Mengembalikan aliran (Flow) dari data siswa dengan ID tertentu dari database.

    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa) //Menyisipkan data siswa baru ke dalam database secara asinkron.

    override suspend fun deleteSiswa(siswa: Siswa) = siswaDao.delete(siswa) //Menghapus data siswa dari database secara asinkron.

    override suspend fun updateSiswa(siswa: Siswa) = siswaDao.update(siswa) //Memperbarui data siswa di database secara asinkron.
}

// kelas ini berfungsi sebagai perantara antara kode aplikasi dan database. Melalui fungsinya, kita dapat melakukan operasi seperti membaca, menambahkan, menghapus, atau memperbarui data siswa di database.