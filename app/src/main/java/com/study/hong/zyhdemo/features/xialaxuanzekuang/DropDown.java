package com.study.hong.zyhdemo.features.xialaxuanzekuang;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.study.hong.zyhdemo.R;

import java.util.ArrayList;

/**
 * Created by shuaihong on 2019/2/27.
 */

public class DropDown extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private ImageButton ib_dropdown;
    private EditText et_input;
    private ListView listView;
    private ArrayList<String> datas;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_down_mian);
        //找到控件
        initView();
    }

    private void initView() {
        et_input = findViewById(R.id.et_input);
        ib_dropdown = findViewById(R.id.ib_dropdown);
        ib_dropdown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showPopupWindow();
    }

    /**
     * 点击下拉按钮弹出号码列表
     */
    private void showPopupWindow() {
        //准备一个listview给PopupWindow
        initListView();
        // 显示下拉选择框注意宽高的跟前面一个框一样
        popupWindow = new PopupWindow(listView,et_input.getWidth(),300);

        //设置popupWindow可获取焦点，以防万一
        popupWindow.setFocusable(true);

        //设置popupWindow外部可以点击
        popupWindow.setOutsideTouchable(true);
        //设置一个空的背景，让popupWindow不显示
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //注意：一定要让popupWindow显示出来
        popupWindow.showAsDropDown(et_input,0,0);
    }

    private void initListView() {
        listView = new ListView(this);
        //去掉横线
        listView.setDividerHeight(0);
        //设置背景，四周有线
        listView.setBackgroundResource(R.drawable.xialaxuanze_listview_background_xiala);

        //设置条目点击事件
        listView.setOnItemClickListener(this);

        datas = new ArrayList<>();
        for (int i=0;i<10;i++){
            datas.add(""+500000+i);
        }

           Log.d("initListView",datas.size()+"");

        listView.setAdapter(new MyListViewAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("onItemClick",position+"");
        //点击后，设置文本
        et_input.setText(datas.get(position));
        //点击后显示文本后，让popupWindow消失
        popupWindow.dismiss();
    }


    class MyListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(parent.getContext(), R.layout.item_number, null);
            } else {
                view = convertView;
            }
            Log.d("getView", datas.size() + "");
            Log.d("position", position + "");
            //注意findViewById前面要加view
            TextView tv_number = view.findViewById(R.id.tv_number);
            tv_number.setText(datas.get(position));
            view.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    //adapter里的方法，刷新listview
                    notifyDataSetChanged();
                    if (datas.size()==0){
                        popupWindow.dismiss();
                    }
                }
            });
            return view;
        }
    }
}
