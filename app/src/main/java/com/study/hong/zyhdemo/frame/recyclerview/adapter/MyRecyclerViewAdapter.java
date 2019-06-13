package com.study.hong.zyhdemo.frame.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.study.hong.zyhdemo.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hong on 2019/6/5.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> datas;
    Context mContext;

    public MyRecyclerViewAdapter(ArrayList<String> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    /**
     * 相当于于getView方法中创建View和ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.recyclerview_item,null);
        return new ViewHolder(itemView);
    }

    /**相当于于getView绑定数据部分的代码
     * 数据和View绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String  data  = datas.get(position);
        String random = getRandom(data);
        holder.tv_title.setText(random);
    }

    private String getRandom(String name) {
        Random random = new Random();
        StringBuilder builder=new StringBuilder();
        int length = random.nextInt(10) + 1;
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addItem(int i, String new_content) {
        datas.add(i,new_content);
        //单条刷新适配器
        notifyItemInserted(i);
    }

    public void removeItem(int position) {
        datas.remove(position);
        //单条刷新适配器
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(v,datas.get(getLayoutPosition()));
                    }
                }
            });
            iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "我是图片=="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface OnItemClickListener{
        /**
         * 当RecyclerView某个被点击的时候回调
         * @param view 点击item的视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view, String data);
    }
    private OnItemClickListener onItemClickListener;
    /**
     * 设置RecyclerView某个的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
