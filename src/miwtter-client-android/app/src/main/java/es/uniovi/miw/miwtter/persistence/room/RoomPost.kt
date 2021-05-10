package es.uniovi.miw.miwtter.persistence.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_posts")
data class RoomPost(
        @PrimaryKey val postId: String,
        @ColumnInfo(name = "owner_name") val ownerName: String,
        @ColumnInfo(name = "owner_username") val ownerUsername: String,
        @ColumnInfo(name = "post_content") val postContent: String
    )
