package com.hfad.thebinderalpha10.DataBase;

import com.hfad.thebinderalpha10.R;

import java.util.ArrayList;

public class ImageAssets {

    private static final ArrayList<Integer> images = new ArrayList<Integer>(){{
        add(R.drawable.android_body);
        add(R.drawable.android_body_1);
        add(R.drawable.android_body_2);
        add(R.drawable.android_head);
        add(R.drawable.android_head_1);
        add(R.drawable.android_head_2);
        add(R.drawable.android_leg);
        add(R.drawable.android_leg_1);
        add(R.drawable.android_leg_2);
        add(R.drawable.z);
    }};


    public static ArrayList<Integer> getImages(){
        return images;
    }

}
