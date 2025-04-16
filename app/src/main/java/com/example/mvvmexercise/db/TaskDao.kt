package np.com.bimalkafle.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmexercise.DataType

@Dao
interface TaskDao {

    @Query("SELECT * FROM DataType")
    fun getAllTask() : LiveData<List<DataType>>

    @Insert
    fun addTask(task : DataType)

    @Query("Delete FROM DataType where id = :id")
    fun deleteTask(id : Int)

    @Query("Delete FROM DataType")
    fun deleteAllTask()
}