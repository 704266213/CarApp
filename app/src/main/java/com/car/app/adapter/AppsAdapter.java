package com.car.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.car.app.R;
import com.car.app.bean.AppBean;

import java.util.List;

/**
 * Created by shuhj on 2017/3/7.
 */

public class AppsAdapter extends BaseAdapter {

    private List<AppBean> appBeens;

    public AppsAdapter(List<AppBean> appBeens) {
        this.appBeens = appBeens;
    }

    @Override
    public int getCount() {
        return appBeens.size();
    }

    @Override
    public AppBean getItem(int position) {
        return appBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item, null);
            holder.appIcon = (ImageView) convertView.findViewById(R.id.appIcon);
            holder.appName = (TextView) convertView.findViewById(R.id.appName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AppBean appBean = appBeens.get(position);
        holder.appIcon.setBackgroundResource(appBean.getAppIcon());
        holder.appName.setText(appBean.getAppName());
        return convertView;
    }

    public final class ViewHolder {
        public ImageView appIcon;
        public TextView appName;
    }
}
