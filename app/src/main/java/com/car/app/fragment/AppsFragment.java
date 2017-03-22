package com.car.app.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.car.app.AppsEnum;
import com.car.app.R;
import com.car.app.adapter.AppFragmentAdapter;
import com.car.app.bean.AppBean;
import com.car.app.listener.OnOpenAppListener;

import java.util.ArrayList;
import java.util.List;


public class AppsFragment extends BaseFragment {

    private ArrayList<AppBean> appBeens;

    private ViewPager viewPager;
    private AppFragmentAdapter appFragmentAdapter;
    private View dot1;
    private View dot2;

    private List<AppsGridFragment> appsGridFragmentList;

    private OnOpenAppListener onOpenAppListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apps, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
    }

    private void initData() {
        appBeens = new ArrayList<>();
        appsGridFragmentList = new ArrayList<>();
        AppsEnum[] appsEnumArray = AppsEnum.values();
        int appSize = appsEnumArray.length;
        for (int i = 0; i < appSize; i++) {
            AppBean appBean = new AppBean();
            AppsEnum appsEnum = appsEnumArray[i];
            appBean.setAppId(appsEnum.getAppId());
            appBean.setAppIcon(appsEnum.getAppIcon());
            appBean.setAppName(appsEnum.getAppName());
            appBean.setAppsBaseFragment(appsEnum.getAppsBaseFragment());
            appBeens.add(appBean);
        }

        int size = appBeens.size() / 8 + 1;
        for (int i = 0; i < size; i++) {
            AppsGridFragment appsGridFragment = new AppsGridFragment();
            int start = i * 8;
            int end = start + 8;
            end = end < appSize ? end : appSize;
            appsGridFragment.setAppBeens(appBeens.subList(start, end));
            appsGridFragmentList.add(appsGridFragment);
        }

    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        dot1 = view.findViewById(R.id.dot1);
        dot2 = view.findViewById(R.id.dot2);

        appFragmentAdapter = new AppFragmentAdapter(getChildFragmentManager(), appsGridFragmentList);
        viewPager.setAdapter(appFragmentAdapter);

        int appid = onOpenAppListener.getOpenAppId();
        setPageIndex(appid);
    }

    public void setPageIndex(int appid){
        if(appid != -1){
            int appSize = appBeens.size();
            for( int i = 0 ; i < appSize ; i++ ){
                AppBean appBean = appBeens.get(i);
                if(appid == appBean.getAppId()){
                    viewPager.setCurrentItem(i/8);
                    onStartAppListener.addFragmentToContainer(appBean);
                    break;
                }
            }
        } else {
            viewPager.setCurrentItem(0);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenAppListener) {
            onOpenAppListener = (OnOpenAppListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

}
