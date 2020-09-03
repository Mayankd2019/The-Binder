package com.hfad.thebinderalpha10.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface  MembersInfoDao {

    @Query("SELECT * FROM group_member_info")
    LiveData<List<GroupMember>> getAllMembers();

    @Delete
    void deleteMember(GroupMember groupMember);

    @Insert
    void addMember(GroupMember groupMember);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMember(GroupMember groupMember);

    @Query("SELECT * FROM group_member_info WHERE id = :id")
    GroupMember loadTaskById(int id);
}
