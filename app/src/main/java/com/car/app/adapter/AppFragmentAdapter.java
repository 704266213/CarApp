package com.car.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.car.app.fragment.AppsGridFragment;

import java.util.List;

/**
 * Created by shuhj on 2017/3/7.
 */

public class AppFragmentAdapter extends FragmentPagerAdapter {

    private List<AppsGridFragment> appsGridFragmentList;

    public AppFragmentAdapter(FragmentManager fm,List<AppsGridFragment> appsGridFragmentList) {
        super(fm);
        this.appsGridFragmentList = appsGridFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return appsGridFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return appsGridFragmentList.size();
    }
}
