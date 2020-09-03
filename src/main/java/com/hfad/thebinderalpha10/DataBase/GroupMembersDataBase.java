package com.hfad.thebinderalpha10.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GroupMember.class},version = 1, exportSchema = false)
public abstract class GroupMembersDataBase extends RoomDatabase {

    public static GroupMembersDataBase sInstance;
    public static final String dataBaseName = "binder_database";
    public static final Object LOCK = new Object();

    public static GroupMembersDataBase getInstance(Context context){
        if(sInstance == null){
            synchronized(LOCK){                                                                     //ye haga  hai ha. malum nahi mereko ye kuch bhi
                sInstance = Room.databaseBuilder(context.getApplicationContext(),GroupMembersDataBase.class,dataBaseName).
                        allowMainThreadQueries().build();
            }
        }
        return sInstance;
    }
    public abstract MembersInfoDao membersInfoDao();
}
