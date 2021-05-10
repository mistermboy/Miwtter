package es.uniovi.miw.miwtter.persistence

import android.content.Context
import androidx.room.Room
import com.example.miwtter.App
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.persistence.room.RoomDatabase
import es.uniovi.miw.miwtter.persistence.room.RoomPost

/**
 * Implementation of the [LocalDatabase] interface. This implementation is specific for Room.
 */
object LocalDatabaseRoomImpl: LocalDatabase {

    private val db = Room.databaseBuilder(
        App.instance,
        RoomDatabase::class.java,
        RoomDatabase.DATABASE_NAME
    ).build()


    override fun getAll(): List<Miwtter.FeedPost> =
        db.roomPostDao().getAll().map {
            localPost -> Miwtter.FeedPost.newBuilder()
            .setPostId(localPost.postId)
            .setOwnerName(localPost.ownerName)
            .setOwnerUsername(localPost.ownerUsername)
            .setContent(localPost.postContent)
            .build()
        }

    override fun findByPostId(postId: String): Miwtter.FeedPost? =
        if(db.roomPostDao().findByPostId(postId) != null) {
            val localPost = db.roomPostDao().findByPostId(postId)
            Miwtter.FeedPost.newBuilder()
                .setPostId(localPost.postId)
                .setOwnerName(localPost.ownerName)
                .setOwnerUsername(localPost.ownerUsername)
                .setContent(localPost.postContent)
                .build()
        } else {
            null
        }

    override fun addPost(post: Miwtter.FeedPost): Boolean {
        val localPost = RoomPost(
            postId = post.postId,
            ownerName = post.ownerName,
            ownerUsername = post.ownerUsername,
            postContent = post.content
        )
        db.roomPostDao().insertAll(localPost)
        return true
    }

    override fun removePost(postId: String): Miwtter.FeedPost? {
        val localPost = db.roomPostDao().findByPostId(postId)
        if(localPost == null) return localPost

        db.roomPostDao().delete(localPost)
        return Miwtter.FeedPost.newBuilder()
            .setPostId(localPost.postId)
            .setOwnerName(localPost.ownerName)
            .setOwnerUsername(localPost.ownerUsername)
            .setContent(localPost.postContent)
            .build()
    }
}