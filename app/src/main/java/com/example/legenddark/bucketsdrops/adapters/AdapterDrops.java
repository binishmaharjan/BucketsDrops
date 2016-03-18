package com.example.legenddark.bucketsdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.legenddark.bucketsdrops.Beans.Drops;
import com.example.legenddark.bucketsdrops.R;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by legenddark on 2016/03/17.
 */
public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {

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
    public DropHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view =  mInflater.inflate(R.layout.row_layout, parent, false);

        DropHolder holder = new DropHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DropHolder holder, int position) {
        Drops drops = mResults.get(position);
        holder.mTextWhat.setText(drops.getWhat());
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class DropHolder extends RecyclerView.ViewHolder {

        TextView mTextWhat;

        public DropHolder(View itemView) {
            super(itemView);

            mTextWhat = (TextView) itemView.findViewById(R.id.tv_text_what);
        }
    }
}
