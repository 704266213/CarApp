package com.car.app.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.car.app.AppsEnum;
import com.car.app.bean.AppBean;
import com.car.app.listener.OnStartAppListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    public BaseFragment() {
    }

    public static BaseFragment instanceByPosition(int positon) {
        BaseFragment baseFragment = null;
        switch (positon) {
            case 0:
                baseFragment = new SettingFragment();
                break;
            case 1:
                baseFragment = new VoiceFragment();
                break;
            case 2:
                baseFragment = new HomeFragment();
                break;
            case 3:
                baseFragment = new AppsFragment();
                break;
        }
        return baseFragment;
    }

    protected OnStartAppListener onStartAppListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartAppListener) {
            onStartAppListener = (OnStartAppListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onStartAppListener = null;
    }


    public void setPageIndex(int appid) {

    }
}
