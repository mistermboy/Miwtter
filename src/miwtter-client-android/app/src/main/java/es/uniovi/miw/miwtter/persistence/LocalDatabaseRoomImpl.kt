package es.uniovi.miw.miwtter.persistence

import androidx.room.Room
import com.example.miwtter.App
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.persistence.room.RoomDatabase
import es.uniovi.miw.miwtter.persistence.room.RoomPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of the [LocalDatabase] interface. This implementation is specific for Room.
 */
object LocalDatabaseRoomImpl: LocalDatabase {

    private val db = Room.databaseBuilder(
        App.instance,
        RoomDatabase::class.java,
        RoomDatabase.DATABASE_NAME
    ).build()


    override suspend fun getAll(): List<Miwtter.FeedPost> =
        withContext(Dispatchers.IO) {
            db.roomPostDao().getAll().map { localPost ->
                Miwtter.FeedPost.newBuilder()
                    .setPostId(localPost.postId)
                    .setOwnerName(localPost.ownerName)
                    .setOwnerUsername(localPost.ownerUsername)
                    .setContent(localPost.postContent)
                    .build()
            }
        }

    override suspend fun findByPostId(postId: String): Miwtter.FeedPost? =
        withContext(Dispatchers.IO) {
            if (db.roomPostDao().findByPostId(postId) != null) {
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
        }

    override suspend fun addPost(post: Miwtter.FeedPost): Boolean {
        withContext(Dispatchers.IO) {
            val localPost = RoomPost(
                postId = post.postId,
                ownerName = post.ownerName,
                ownerUsername = post.ownerUsername,
                postContent = post.content
            )
            db.roomPostDao().insertAll(localPost)
        }
        return true
    }

    override suspend fun removePost(postId: String): Miwtter.FeedPost? {
        return withContext(Dispatchers.IO) {
            val localPost = db.roomPostDao().findByPostId(postId)
            if (localPost == null){ localPost}
            else {
                db.roomPostDao().delete(localPost)
                Miwtter.FeedPost.newBuilder()
                    .setPostId(localPost.postId)
                    .setOwnerName(localPost.ownerName)
                    .setOwnerUsername(localPost.ownerUsername)
                    .setContent(localPost.postContent)
                    .build()
            }
        }
    }
}