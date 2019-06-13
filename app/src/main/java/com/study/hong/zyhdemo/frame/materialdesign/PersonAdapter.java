package com.study.hong.zyhdemo.frame.materialdesign;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.study.hong.zyhdemo.R;

import java.util.List;

/**
 * Created by hong on 2019/6/12.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHodler> {
    private Context mContext;
    private List<Person> mPerson;

    public PersonAdapter(Context mContext, List<Person> mPerson) {
        this.mContext = mContext;
        this.mPerson = mPerson;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =View.inflate(mContext, R.layout.person_item,null);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, final int position) {
        Person person =mPerson.get(position);
        Glide.with(mContext).load(person.getImgId()).into(holder.image);
        holder.tv_name.setText(person.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPerson.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder{

        ImageView image;
        TextView tv_name;
        CardView cardView;

        public ViewHodler(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            image = itemView.findViewById(R.id.person_image);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
        }
}
