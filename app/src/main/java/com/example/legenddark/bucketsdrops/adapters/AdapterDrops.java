package com.example.legenddark.bucketsdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.legenddark.bucketsdrops.Beans.Drops;
import com.example.legenddark.bucketsdrops.R;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by legenddark on 2016/03/17.
 */
public class AdapterDrops extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public  static final int ITEM=0;
    public static final int FOOTER=1;

    private LayoutInflater mInflater;
    private RealmResults<Drops> mResults;

    public AdapterDrops(Context context,RealmResults<Drops> results) {
        mInflater = LayoutInflater.from(context);
        update(results);
    }

    public void update(RealmResults<Drops> results){
        mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(mResults == null || position < mResults.size()){
            return ITEM;
        }
        else{
            return FOOTER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FOOTER){
            View view =  mInflater.inflate(R.layout.footer, parent, false);

            return new FooterHolder(view);
        }
        else{
            View view =  mInflater.inflate(R.layout.row_layout, parent, false);

            return new DropHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DropHolder){
            DropHolder dropHolder = (DropHolder) holder;
            Drops drops = mResults.get(position);
            dropHolder.mTextWhat.setText(drops.getWhat());
        }
    }

    @Override
    public int getItemCount() {

        return mResults.size() + 1;
    }

    class DropHolder extends RecyclerView.ViewHolder {

        TextView mTextWhat;

        public DropHolder(View itemView) {
            super(itemView);

            mTextWhat = (TextView) itemView.findViewById(R.id.tv_text_what);
        }
    }


    class FooterHolder extends RecyclerView.ViewHolder {

        Button mBtnFooter;

        public FooterHolder(View itemView) {
            super(itemView);

            mBtnFooter = (Button) itemView.findViewById(R.id.btn_footer);
        }
    }
}
