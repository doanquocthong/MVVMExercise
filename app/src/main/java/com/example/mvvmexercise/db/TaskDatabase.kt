package np.com.bimalkafle.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmexercise.DataType


@Database(entities = [DataType::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    companion object {
        const val NAME = "Task_DB"
    }
    abstract fun getTaskDao() : TaskDao
}