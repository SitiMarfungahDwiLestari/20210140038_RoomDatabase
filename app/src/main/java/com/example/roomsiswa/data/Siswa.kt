package com.example.roomsiswa.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tblSiswa")
data class  Siswa(
    //tag primary key digunakan untuk menandakan bahwa primary key untuk dibawahnya(jika tag primary key diletakkan di atas id, maka idnya yang jadi primary key. jika diatas nama, maka primary keynya diletakkan diatas nama)
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val alamat : String,
    val telpon : String
)