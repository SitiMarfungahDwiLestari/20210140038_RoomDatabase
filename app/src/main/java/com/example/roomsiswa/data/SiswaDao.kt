package com.example.roomsiswa.data
//Data Access Object adalah tempat untuk kita menempatkan metode untuk berinteraksi dengan database.
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface SiswaDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE) //(data dengan ID yang sama), strategi yang dipilih di sini adalah mengabaikan data baru yang sama.
    suspend fun insert(siswa: Siswa) //Menambahkan data siswa ke dalam database.

    @Update
    suspend fun update(siswa:Siswa) //Mengupdate data siswa yang sudah ada di dalam database.

    @Delete
    suspend fun delete(siswa:Siswa) //Menghapus data siswa dari database.

    @Query("SELECT * from tblSiswa WHERE id = :id") //Mengambil satu siswa dari database berdasarkan ID-nya. Hasilnya dikirimkan sebagai aliran (Flow), yang dapat digunakan untuk mengamati perubahan data secara asinkron.
    fun getSiswa(id: Int): Flow<Siswa>

    @Query("SELECT * from tblSiswa ORDER BY nama ASC") //Mengambil semua siswa dari database dan mengurutkannya berdasarkan nama secara ascending (menaik). Hasilnya dikirimkan sebagai aliran (Flow), yang dapat digunakan untuk mengamati perubahan data secara asinkron.
    fun getAllSiswa(): Flow<List<Siswa>>
}



//Aliran (Flow) digunakan untuk memberi tahu aplikasi ketika ada perubahan dalam data. Ini sangat membantu ketika kita ingin membuat antarmuka pengguna yang responsif terhadap perubahan dalam database.