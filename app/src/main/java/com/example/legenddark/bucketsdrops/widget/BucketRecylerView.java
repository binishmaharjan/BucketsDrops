package com.example.legenddark.bucketsdrops.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.legenddark.bucketsdrops.extras.Util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by legenddark on 2016/03/18.
 */
public class BucketRecylerView extends RecyclerView {
    private List<View>mEmptyViews = Collections.emptyList();
    private  List<View> mNonEmptyViews =Collections.emptyList();


   //Private Abstract Class Of AdapterDataObserver
    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleView();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleView();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            toggleView();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleView();
        }
    };

    private void toggleView() {
         if(getAdapter() != null && !mEmptyViews.isEmpty() && !mNonEmptyViews.isEmpty()){
             if(getAdapter().getItemCount() == 0){
                 //show all EmptyView
                 Util.showViews(mEmptyViews);

                 //hide the RecyclerView
                 setVisibility(GONE);

                 //hide all NonEmptyView
                 Util.hideViews(mNonEmptyViews);
             }
             else{
                 //hide all EmptyView
                 Util.hideViews(mEmptyViews);
                 //show the RecyclerView
                 setVisibility(VISIBLE);

                 //show all NonEmptyView
                 Util.showViews(mNonEmptyViews);
             }
         }
    }

    //Constructor of BucketRecyclerView
    public BucketRecylerView(Context context) {
        super(context);
    }

    public BucketRecylerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BucketRecylerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(adapter != null){
            adapter.registerAdapterDataObserver(mObserver);
        }
        mObserver.onChanged();
    }

    public void hideIfEmpty(View... views) {
        mNonEmptyViews = Arrays.asList(views);
    }

    public void showIfEmpty(View... emptyViews) {
        mEmptyViews = Arrays.asList(emptyViews);
    }
}
