package es.uniovi.miw.miwtter.persistence.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RommPostDao {

    @Query("SELECT * FROM local_posts")
    fun getAll(): List<RoomPost>

    @Query("SELECT * FROM local_posts WHERE postId = :postId")
    fun findByPostId(postId: String): RoomPost

    @Insert
    fun insertAll(vararg roomPosts: RoomPost)

    @Delete
    fun delete(roomPost: RoomPost)
}