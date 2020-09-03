package com.hfad.thebinderalpha10.DataBase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hfad.thebinderalpha10.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberImageFragment extends Fragment {

    private ImageView memberImage ;
    public static final String IMAGE_ID = "image index for getting image from imageAssets";
    private int id;
    public MemberImageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member_image, container, false);
        memberImage = view.findViewById(R.id.member_fragment_iv);
        Bundle args = getArguments();
        if(args != null){
            id = args.getInt(IMAGE_ID);
            memberImage.setImageResource(ImageAssets.getImages().get(id));
        }
        return view;
    }
}
