package com.study.hong.zyhdemo.customize.quickindex.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.customize.quickindex.other.Friend;

import java.util.ArrayList;


/**
 * Created by 洪 on 2019/5/9.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Friend> list;
private Context mContext;

    public MyAdapter(ArrayList<Friend> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.customize_quickindex_list_item2,null);
        }
        ViewHolder holder = ViewHolder.getHolder(convertView);
        //设置数据
        Friend friend = list.get(position);
        holder.name.setText(friend.getName());

        String currentWord = friend.getPinyin().charAt(0) + "";
        if (position > 0) {
            //获取上一个item的首字母
            String lastWord = list.get(position - 1).getPinyin().charAt(0) + "";
            if (currentWord.equals(lastWord)){
                //说明首字母相同，需要隐藏当前item的first_word
                holder.first_word.setVisibility(View.GONE);
            }else {
                    //不一样，需要显示当前的首字母
                    //由于布局是复用的，所以在需要显示的时候，再次将first_word设置为可见
                    holder.first_word.setVisibility(View.VISIBLE);
                    holder.first_word.setText(currentWord);
            }
        }else {
            holder.first_word.setVisibility(View.VISIBLE);
            holder.first_word.setText(currentWord);
        }

        return convertView;
    }
    public static class ViewHolder{
        TextView name,first_word;

        public ViewHolder(View convertView) {
            name =  convertView.findViewById(R.id.name);
            first_word =convertView.findViewById(R.id.first_word);
        }

        public static ViewHolder getHolder(View convertView) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder==null){
                holder=new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}
