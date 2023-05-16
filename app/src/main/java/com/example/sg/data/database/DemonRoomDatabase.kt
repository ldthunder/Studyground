package com.example.sg.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/*** В настоящем приложении exportSchema надо поменять, и, поменяв, ты потом повышаешь версию дб и определяешь политику миграции. Destroy and re-create strategy
 может быть достаточно, но в реальных приложениях лучше добавить свою стратегию миграции
*/
@Database(entities = [Demon::class], version = 1)
abstract class DemonRoomDatabase : RoomDatabase() {
    abstract val dao: DemonDao


    companion object {
        @Volatile
        private var INSTANCE: DemonRoomDatabase? = null

        fun getDatabase(context: Context): DemonRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DemonRoomDatabase::class.java,
                    "demon_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}