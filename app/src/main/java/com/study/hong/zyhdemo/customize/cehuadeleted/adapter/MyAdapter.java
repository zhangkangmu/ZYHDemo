package com.study.hong.zyhdemo.customize.cehuadeleted.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.customize.cehuadeleted.ui.SwipeLayout;
import com.study.hong.zyhdemo.customize.cehuadeleted.ui.SwipeLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 洪 on 2019/5/14.
 */

public class MyAdapter extends BaseAdapter implements SwipeLayout.OnSwipeStateChangeListener{
    private Context context;
    private ArrayList<String> list;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.customize_cehuadeleted_adapter_item, null);
        }
        ViewHolder hodler = ViewHolder.getHodler(convertView);
        hodler.tvName.setText(list.get(position));
        //如果想获得位置的话那可以在swipelayout中设置一个setpostion的方法，或者setTag，让onOpen和onClose可以getTag
        hodler.swipeLayout.setTag(position);
        hodler.swipeLayout.setOnSwipeStateChangeListener(this);
        hodler.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                SwipeLayoutManager.getInstance().closeCurrentLayout();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public void onOpen(Object tag) {
        Toast.makeText(context,"第"+(Integer)tag+"个打开", 0).show();
    }

    @Override
    public void onClose(Object tag) {
        Toast.makeText(context,"第"+(Integer)tag+"个关闭", 0).show();
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.swipeLayout)
        SwipeLayout swipeLayout;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public static ViewHolder getHodler(View convertView) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}
