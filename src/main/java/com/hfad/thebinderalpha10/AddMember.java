package com.hfad.thebinderalpha10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hfad.thebinderalpha10.DataBase.GroupMember;
import com.hfad.thebinderalpha10.DataBase.GroupMembersDataBase;

import java.util.List;

public class AddMember extends AppCompatActivity {

    private EditText mName;
    private EditText mAssignedName;
    private  EditText mEMail;
    private EditText mContact;
    private Button mSaveButton;

    private GroupMembersDataBase mDb;
    private List<GroupMember> mGroupMembersList;
    private GroupMember mGroupMember;
    private int memberId ;
    private final int DEFAULT_ID = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        initViews();

        Intent intent = getIntent();

        if(intent !=null && intent.hasExtra(Intent.EXTRA_INDEX)) {
            memberId = intent.getIntExtra(Intent.EXTRA_INDEX, DEFAULT_ID);
        }

        if(memberId != DEFAULT_ID){
            setHintsToEditTexts();
        }

        mSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }


    private void initViews() {
        mName = findViewById(R.id.et_name_of_member_);
        mAssignedName = findViewById(R.id.et_name_assigned_to_member);
        mEMail = findViewById(R.id.et_member_email);
        mContact = findViewById(R.id.et_member_phone);
        mSaveButton = findViewById(R.id.save_changes_button);
        mDb = GroupMembersDataBase.getInstance(this);

        memberId = DEFAULT_ID;
    }


    private void setHintsToEditTexts() {
        //mDb = GroupMembersDataBase.getInstance(getApplicationContext());
        mGroupMember = mDb.membersInfoDao().loadTaskById(memberId);
        mName.setText(mGroupMember.getName());
        mAssignedName.setText(mGroupMember.getNickName());
        mEMail.setText(mGroupMember.getEmailId());
        mContact.setText(String.valueOf(mGroupMember.getContactNumber()));
    }

    private void saveChanges() {

        String name = mName.getText().toString();
        String nickName = mAssignedName.getText().toString();
        String email = mEMail.getText().toString();
        long contactNumber = Long.parseLong(mContact.getText().toString());
        if(memberId == DEFAULT_ID){
            mGroupMember = new GroupMember(name,nickName,email,contactNumber);
            mDb.membersInfoDao().addMember(mGroupMember);
        } else {
            mGroupMember.setContactNumber(contactNumber);
            mGroupMember.setEmailId(email);
            mGroupMember.setNickName(nickName);
            mGroupMember.setName(name);
            mDb.membersInfoDao().updateMember(mGroupMember);
        }
        finish();
    }


}
