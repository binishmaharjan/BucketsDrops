package com.example.legenddark.bucketsdrops.extras;

import android.view.View;

import java.util.List;

/**
 * Created by legenddark on 2016/03/18.
 */
public class Util {

    public static void showViews(List<View> views){
        for (View view: views){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hideViews(List<View> views){
        for (View view: views){
            view.setVisibility(View.GONE);
        }
    }
}
