package es.uniovi.miw.miwtter.persistence.room

import androidx.room.Database
import androidx.room.Room

@Database(entities = [RoomPost::class], version = 1)
abstract class RoomDatabase: androidx.room.RoomDatabase() {

    abstract fun roomPostDao(): RommPostDao

    companion object {
        const val DATABASE_NAME = "miwtter-local-db"
    }
}