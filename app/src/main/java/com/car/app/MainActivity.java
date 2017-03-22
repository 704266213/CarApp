package com.car.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.car.app.base.AppsBaseFragment;
import com.car.app.bean.AppBean;
import com.car.app.fragment.BaseFragment;
import com.car.app.listener.OnOpenAppListener;
import com.car.app.listener.OnStartAppListener;
import com.car.app.listener.OnTabChangeListener;
import com.car.app.listener.OnTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnTabChangeListener, OnStartAppListener ,OnOpenAppListener {

    private FragmentManager fragmentManager;
    private DrawerLayout drawer;
    private TextView currentView;
    private BaseFragment currentFragment;
    private List<BaseFragment> baseFragmentList;
    private List<TextView> tabs;
    private int position ;

    private TextView setting;
    private TextView voice;
    private TextView home;
    private TextView moreApps;
    private FrameLayout container;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        container = (FrameLayout) findViewById(R.id.container);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        baseFragmentList = new ArrayList<>();
        tabs = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        setting = (TextView) findViewById(R.id.setting);
        tabs.add(setting);
        voice = (TextView) findViewById(R.id.voice);
        tabs.add(voice);
        home = (TextView) findViewById(R.id.home);
        tabs.add(home);
        moreApps = (TextView) findViewById(R.id.moreApps);
        tabs.add(moreApps);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 2);
        int tabSize = tabs.size();
        for (int i = 0; i < tabSize; i++) {
            TextView tab = tabs.get(i);
            tab.setOnClickListener(new OnTabClickListener(i, this));
            boolean isShow = i == position ? true : false;
            addFragment(i, isShow);
        }
    }

    @Override
    public void onTabChange(int position, View selView) {
        onTabViewChange(position);
        onTabFragmentChange(position);
    }

    private void onTabViewChange(int position) {
        currentView.setTextColor(Color.WHITE);
        currentView = tabs.get(position);
        currentView.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void onTabFragmentChange(int position) {
        int count = fragmentManager.getBackStackEntryCount();
        if(count > 0){
            for(int i = 0 ; i < count ; i++ ){
                fragmentManager.popBackStack();
            }
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(currentFragment);
        currentFragment = baseFragmentList.get(position);
        fragmentTransaction.show(currentFragment);
        fragmentTransaction.commit();
    }

    private void addFragment(int position, boolean isShow) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BaseFragment baseFragment = BaseFragment.instanceByPosition(position);
        fragmentTransaction.add(R.id.container, baseFragment, baseFragment.getClass().getSimpleName());
        if (isShow) {
            fragmentTransaction.show(baseFragment);
            currentFragment = baseFragment;
            currentView = tabs.get(position);
            currentView.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            fragmentTransaction.hide(baseFragment);
        }
        fragmentTransaction.commit();
        baseFragmentList.add(baseFragment);
    }

    @Override
    public void addFragmentToContainer(AppBean appBean) {
        AppsBaseFragment appsBaseFragment = appBean.getAppsBaseFragment();
        String fragmentName = appsBaseFragment.getClass().getSimpleName();
        if(fragmentManager.findFragmentByTag(fragmentName) == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, appsBaseFragment, fragmentName);
            fragmentTransaction.addToBackStack(fragmentName);
            fragmentTransaction.commit();
        }
    }

    public void onFragmentBack(View view){
        fragmentManager.popBackStack();
    }


    public void onSaveInstanceState(Bundle outState) {

    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int position = intent.getIntExtra("position",2);

        onTabViewChange(position);
        onTabFragmentChange(position);
        int appId = intent.getIntExtra("appId",-1);
        if (appId != -1) {
            BaseFragment baseFragment = baseFragmentList.get(position);
            baseFragment.setPageIndex(appId);
        }
    }

    @Override
    public int getOpenAppId() {
        Intent intent = getIntent();
        int appId = intent.getIntExtra("appId",-1);
        return appId;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
