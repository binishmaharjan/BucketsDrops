package com.example.legenddark.bucketsdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ActivityMain extends AppCompatActivity {

    Toolbar mToolBar;
    Button mBtnAdd;
    RecyclerView mRecycler;
    private View.OnClickListener mBtnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };

    private void showDialog() {
        DialogAdd dialog = new DialogAdd();
        dialog.show(getSupportFragmentManager(),"dialogAdd");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mBtnAdd = (Button) findViewById(R.id.iv_button);
        mBtnAdd.setOnClickListener(mBtnAddListener);
        mRecycler = (RecyclerView) findViewById(R.id.rv_drops);
        setSupportActionBar(mToolBar);


        initBackgroundImage();
    }

    private void initBackgroundImage() {
        ImageView background = (ImageView) findViewById(R.id.iv_background);
        Glide.with(this)
                .load(R.drawable.background)
                .centerCrop()
                .into(background);
    }
}
