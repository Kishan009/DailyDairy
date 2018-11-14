package com.dailymood.tracker.daily.diary.database.crud;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.dailymood.tracker.daily.diary.database.table.MultipleImageTable;
import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MultiIplemageDao {

  @Insert(onConflict = IGNORE)
  void InsertImage(List<MultipleImageTable> multipleImageTables);

  @Query("Delete from multiple_image Where note_id = :id")
  void DeleteImage(int id);

  @Update(onConflict = REPLACE)
  void UpdateImage(List<MultipleImageTable> multipleImageTables);


}