package com.example.legenddark.bucketsdrops;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.legenddark.bucketsdrops.Beans.Drops;
import com.example.legenddark.bucketsdrops.adapters.AdapterDrops;
import com.example.legenddark.bucketsdrops.adapters.Divider;
import com.example.legenddark.bucketsdrops.widget.BucketRecylerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ActivityMain extends AppCompatActivity {

    Toolbar mToolBar;
    Button mBtnAdd;
    BucketRecylerView mRecycler;
    Realm mRealm;
    RealmResults<Drops> mResults;
    AdapterDrops mAdapter;
    View emptyView;
    private View.OnClickListener mBtnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };

    private RealmChangeListener mChangeListener = new RealmChangeListener() {
        @Override
        public void onChange() {
            mAdapter.update(mResults);
        }
    };

    private void showDialog() {
        DialogAdd dialog = new DialogAdd();
        dialog.show(getSupportFragmentManager(), "dialogAdd");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = Realm.getDefaultInstance();
        mResults = mRealm.where(Drops.class).findAllAsync();

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mBtnAdd = (Button) findViewById(R
                .id.iv_button);
        emptyView =findViewById(R.id.empty_drops);
        mBtnAdd.setOnClickListener(mBtnAddListener);
        mRecycler = (BucketRecylerView) findViewById(R.id.rv_drops);

        mRecycler.hideIfEmpty(mToolBar);
        mRecycler.showIfEmpty(emptyView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);

        mAdapter = new AdapterDrops(this,mResults);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new Divider(this,LinearLayoutManager.VERTICAL));

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

    @Override
    protected void onStart() {
        super.onStart();
        mResults.addChangeListener(mChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResults.removeChangeListener(mChangeListener);
    }
}
