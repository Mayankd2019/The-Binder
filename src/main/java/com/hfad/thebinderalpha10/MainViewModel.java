package com.hfad.thebinderalpha10;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hfad.thebinderalpha10.DataBase.GroupMember;
import com.hfad.thebinderalpha10.DataBase.GroupMembersDataBase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<GroupMember>> mGroupMembers;

    public MainViewModel(@NonNull Application application) {
        super(application);
        GroupMembersDataBase db = GroupMembersDataBase.getInstance(application);
        mGroupMembers = db.membersInfoDao().getAllMembers();
    }

    public LiveData<List<GroupMember>> getGroupMembers(){
        return mGroupMembers;
    }
}
