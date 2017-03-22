package com.car.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.car.app.R;
import com.car.app.adapter.AppsAdapter;
import com.car.app.bean.AppBean;
import com.car.app.listener.OnStartAppListener;

import java.util.List;


public class AppsGridFragment extends Fragment implements AdapterView.OnItemClickListener{

    private OnStartAppListener onStartAppListener;
    private List<AppBean> appBeens;
    private GridView gridView;

    private AppsAdapter appsAdapter;

    public void setAppBeens(List<AppBean> appBeens) {
        this.appBeens = appBeens;
    }

    public AppsGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apps_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridView);
        appsAdapter = new AppsAdapter(this.appBeens);
        gridView.setAdapter(appsAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (onStartAppListener != null){
            onStartAppListener.addFragmentToContainer(appBeens.get(position));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStartAppListener) {
            this.onStartAppListener = (OnStartAppListener)context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}
