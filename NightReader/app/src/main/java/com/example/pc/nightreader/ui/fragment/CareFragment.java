package com.example.pc.nightreader.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.nightreader.R;

/**
 *
 */
public class CareFragment extends Fragment {


    public CareFragment() {

    }
    public static  CareFragment newInstance(){
        CareFragment _Fragment=new CareFragment();
        return  _Fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_care, container, false);
    }

}
