package com.hfad.thebinderalpha10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.thebinderalpha10.DataBase.GroupMember;
import com.hfad.thebinderalpha10.DataBase.GroupMembersDataBase;
import com.hfad.thebinderalpha10.DataBase.MemberImageFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MembersAdapter.ClickEventHandler {

    private TextView mMembersTitleTextView;
    private ImageButton addMemberButton;
    private RecyclerView mMembersRecyclerView;
    private MembersAdapter mMembersAdapter;
    private LiveData<List<GroupMember>> mGroupMembers;
    private List<GroupMember> mGroupMemberList;
    private GroupMembersDataBase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMembersRecyclerView =  findViewById(R.id.recycler_view_members);
        mMembersTitleTextView = findViewById(R.id.members_title_tv);
        addMemberButton = findViewById(R.id.add_member_button);

        addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMembers();
            }
        });

        mMembersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMembersAdapter = new MembersAdapter(this,this);
        mMembersRecyclerView.setAdapter(mMembersAdapter);

        mDb = GroupMembersDataBase.getInstance(this);
        observeDataAndUpdateUi();
        addSwipingActionToRecyclerView();
    }

    private void addSwipingActionToRecyclerView() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction){
                int i = viewHolder.getAdapterPosition();
                GroupMember groupMember = mMembersAdapter.getGroupMembers().get(i);
                mDb.membersInfoDao().deleteMember(groupMember);
                //mMembersAdapter.notifyItemRemoved(i);
            }
        }).attachToRecyclerView(mMembersRecyclerView);
    }

    private void observeDataAndUpdateUi() {
        //mGroupMembers = mDb.membersInfoDao().getAllMembers();
        //mMembersAdapter.setMembers(mGroupMembers);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getGroupMembers().observe(this, new Observer<List<GroupMember>>() {
            @Override
            public void onChanged(List<GroupMember> groupMembers) {
                mMembersAdapter.setMembers(groupMembers);
                //mMembersAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addMembers() {
        Intent intentToAddMembers = new Intent(this,AddMember.class);
        startActivity(intentToAddMembers);
    }

    @Override
    public void onClick(int itemId) {
        Intent intent  = new Intent(this,AddMember.class);
        intent.putExtra(Intent.EXTRA_INDEX,itemId);
        startActivity(intent);
        //Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show();
    }


    /* @Override
    public void onImageClicked(int n) {
        MemberImageFragment fragment = new MemberImageFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putInt(MemberImageFragment.IMAGE_ID,n);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.recycler_view_members,fragment)
                .commit();
    }*/
}
