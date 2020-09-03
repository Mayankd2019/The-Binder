package com.hfad.thebinderalpha10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.thebinderalpha10.DataBase.GroupMember;
import com.hfad.thebinderalpha10.DataBase.ImageAssets;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder> {


    private Context mContext;
    private List<GroupMember> mGroupMemberList;
    private ClickEventHandler mClickHandler;
    //private ImageClickListener mImageClickListener;

    public List<GroupMember> getGroupMembers(){
        return mGroupMemberList;
    }


    public MembersAdapter(Context context, ClickEventHandler clickEventHandler){
        mContext = context;
        mClickHandler = clickEventHandler;
        //mImageClickListener = imageClickListener;
    }


    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.members_desc_card_item,parent,false);
        return new MembersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        if(mGroupMemberList == null){
            return;
        }
        holder.mMemberAssignedNameTv.setText(mGroupMemberList.get(position).getName());
        holder.mMemberNameTV.setText(mGroupMemberList.get(position).getNickName());
        /*int i = position % 10;
        if(i >= ImageAssets.getImages().size()){
            holder.mMemberImageIv.setImageResource(ImageAssets.getImages().get(i));
        }*/
    }

    @Override
    public int getItemCount() {
        if(mGroupMemberList == null){
            return 0;
        }
        return mGroupMemberList.size();
    }

    public void setMembers(List<GroupMember> groupMembers){
        mGroupMemberList = groupMembers;
        notifyDataSetChanged();
    }


    public class MembersViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mMemberNameTV;
        private TextView mMemberAssignedNameTv;
        private ImageView mMemberImageIv;
        public MembersViewHolder(@NonNull View itemView) {
            super(itemView);
            mMemberNameTV = itemView.findViewById(R.id.tv_name_of_member);
            mMemberAssignedNameTv = itemView.findViewById(R.id.tv_name_assigned_to_member);
            //mMemberImageIv = itemView.findViewById(R.id.member_iv);
            /*mMemberImageIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition() % 10;
                    mImageClickListener.onImageClicked(i);
                }
            });*/
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemId = mGroupMemberList.get(getAdapterPosition()).getId();
            mClickHandler.onClick(itemId);
        }
    }

    public interface ClickEventHandler {
        void onClick(int itemId);
    }

    /*public interface ImageClickListener {
        void onImageClicked(int n);
    }*/
}
