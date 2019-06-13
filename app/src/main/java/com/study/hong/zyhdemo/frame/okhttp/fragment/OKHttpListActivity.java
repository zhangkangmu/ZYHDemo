package com.study.hong.zyhdemo.frame.okhttp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.study.hong.zyhdemo.R;
import com.study.hong.zyhdemo.frame.okhttp.adapter.OKHttpListAdapter;
import com.study.hong.zyhdemo.frame.okhttp.domain.DataBean;
import com.study.hong.zyhdemo.frame.okhttp.uitls.CacheUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by 洪 on 2019/5/24.
 */

public class OKHttpListActivity extends SupportFragment {
    private static final String TAG = OKHttpListActivity.class.getSimpleName();
    private ListView listView;
    private ProgressBar progressBar;
    private TextView tv_nodata;
    private OKHttpListAdapter adapter;
    private String url;


    public static OKHttpListActivity newInstance() {
        Bundle args = new Bundle();
        OKHttpListActivity fragment = new OKHttpListActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.okhttp_activity_okhttplist, container, false);
        tv_nodata = view.findViewById(R.id.tv_nodata);
        listView = view.findViewById(R.id.listview);
        progressBar =view.findViewById(R.id.progressBar);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromNet();
    }

    private void getDataFromNet() {
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        //得到缓存的数据
        String saveJson = CacheUtils.getString(_mActivity,url);
        if(!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }


        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
//            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
//            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tv_nodata.setVisibility(View.VISIBLE);
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            tv_nodata.setVisibility(View.GONE);

            switch (id) {
                case 100:
                    Toast.makeText(_mActivity, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(_mActivity, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
            //解析数据和显示数据
            if(response != null){
                //缓存数据
                CacheUtils.putString(_mActivity,url,response);
                processData(response);

            }
        }



        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }

    /**
     * 解析和显示数据
     * @param json
     */
    private void processData(String json) {

        //解析数据
        DataBean dataBean = parsedJson(json);
        List<DataBean.ItemData> datas =  dataBean.getTrailers();

        if(datas != null && datas.size() >0){
            //有数据
            tv_nodata.setVisibility(View.GONE);
            //显示适配器
            adapter = new OKHttpListAdapter(_mActivity,datas);
            listView.setAdapter(adapter);
        }else{
            //没有数据
            tv_nodata.setVisibility(View.VISIBLE);
        }

        progressBar.setVisibility(View.GONE);
    }


    /**
     * 解析json数据
     *
     * @param response
     * @return
     */
    private DataBean parsedJson(String response) {
        DataBean dataBean = new DataBean();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("trailers");
            if (jsonArray != null && jsonArray.length() > 0) {
                List<DataBean.ItemData> trailers = new ArrayList<>();
                dataBean.setTrailers(trailers);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);

                    if (jsonObjectItem != null) {

                        DataBean.ItemData mediaItem = new DataBean.ItemData();

                        String movieName = jsonObjectItem.optString("movieName");//name
                        mediaItem.setMovieName(movieName);

                        String videoTitle = jsonObjectItem.optString("videoTitle");//desc
                        mediaItem.setVideoTitle(videoTitle);

                        String imageUrl = jsonObjectItem.optString("coverImg");//imageUrl
                        mediaItem.setCoverImg(imageUrl);

                        String hightUrl = jsonObjectItem.optString("hightUrl");//data
                        mediaItem.setHightUrl(hightUrl);

                        //把数据添加到集合
                        trailers.add(mediaItem);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBean;
    }
}
