package com.study.hong.zyhdemo.frame.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.study.hong.zyhdemo.R;

import java.util.List;

/**
 * Created by hong on 2019/6/12.
 */

public class PersonActivity extends AppCompatActivity {
    private List<Person> person;
    private ImageView mPersonImageView;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        mPersonImageView = findViewById(R.id.person_image_view);
        mToolbar = findViewById(R.id.toolbar);
        mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
        mAppbar = findViewById(R.id.appbar);

        Intent intent = getIntent();
        person = (List<Person>) intent.getSerializableExtra("persons");

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        mCollapsingToolbar.setTitle(person.get(1).getName());
        //扩张的颜色
        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorPrimary));
        //收缩的颜色
        mCollapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        Glide.with(this).load(person.get(1).getImgId()).into(mPersonImageView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
