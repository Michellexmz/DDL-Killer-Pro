package com.example.michellezhang.ddlkillerpro;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michellezhang.ddlkillerpro.database.LabelInfo;

import java.util.List;

/**
 * Created by MichelleZhang on 2017/4/18.
 */

public class LabelListArrayAdapter extends ArrayAdapter<LabelInfo> {

    private final List<LabelInfo> list;
    private final Activity context;

    static class ViewHolder {
        protected TextView name;
        protected ImageView logo;
    }

    public LabelListArrayAdapter(Activity context, List<LabelInfo> list) {
        super(context, R.layout.activity_label_row, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.activity_label_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.logo = (ImageView) view.findViewById(R.id.logo);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getLabelName());
        holder.logo.setImageResource(list.get(position).getLabelLogo());
        return view;
    }
}