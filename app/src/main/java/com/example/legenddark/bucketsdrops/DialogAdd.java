package com.example.legenddark.bucketsdrops;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.legenddark.bucketsdrops.Beans.Drops;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by legenddark on 2016/02/25.
 */
public class DialogAdd extends DialogFragment {
    private ImageView mBtnClose;
    private EditText mInputWhat;
    private DatePicker mInputWhen;
    private Button mBtnAdd;

    private View.OnClickListener mBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            switch (id){
                case R.id.btn_add_it:
                    addAction();
                    break;

                case R.id.btn_close:
                    break;

            }

            dismiss();
        }
    };

    //TODO process date
    private void addAction() {
        String what = mInputWhat.getText().toString();
        long now = System.currentTimeMillis();
        RealmConfiguration configuration = new RealmConfiguration.Builder(getActivity()).build();
        Realm.setDefaultConfiguration(configuration);
        Realm realm = Realm.getDefaultInstance();
        Drops drops = new Drops(what,now,0,false);
        realm.beginTransaction();
        realm.copyToRealm(drops);
        realm.commitTransaction();
        realm.close();
    }

    public DialogAdd() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnClose  = (ImageView) view.findViewById(R.id.btn_close);
        mInputWhat = (EditText) view.findViewById(R.id.et_drops);
        mInputWhen = (DatePicker) view.findViewById(R.id.bpv_datepicker);
        mBtnAdd = (Button) view.findViewById(R.id.btn_add_it);

        mBtnClose.setOnClickListener(mBtnClickListener);

        mBtnAdd.setOnClickListener(mBtnClickListener);
        
    }
}
