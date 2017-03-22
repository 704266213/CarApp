package com.car.app.listener;

import android.view.View;

/**
 * Created by shuhj on 2017/3/6.
 */

public class OnTabClickListener implements View.OnClickListener {

    private OnTabChangeListener onTabChangeListener;
    private int position;
    public OnTabClickListener(int position , OnTabChangeListener onTabChangeListener){
        this.position = position;
        this.onTabChangeListener = onTabChangeListener;
    }

    @Override
    public void onClick(View v) {
        if(onTabChangeListener != null){
            onTabChangeListener.onTabChange(position,v);
        }
    }
}
