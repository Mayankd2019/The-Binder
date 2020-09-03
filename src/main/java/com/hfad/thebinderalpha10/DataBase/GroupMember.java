package com.hfad.thebinderalpha10.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "group_member_info")
public class GroupMember {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    @ColumnInfo(name = "nick_name")
    private String nickName;
    @ColumnInfo(name = "email_id")
    private String emailId;
    @ColumnInfo(name = "contact_number")
    private long contactNumber;

    @Ignore
    public GroupMember(String name,String nickName,String emailId,long contactNumber){
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.nickName = nickName;
        this.name = name;
    }

    public GroupMember(int id,String name,String nickName,String emailId,long contactNumber){
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.nickName = nickName;
        this.name = name;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmailId() {
        return emailId;
    }

    public long getContactNumber() {
        return contactNumber;
    }
}
