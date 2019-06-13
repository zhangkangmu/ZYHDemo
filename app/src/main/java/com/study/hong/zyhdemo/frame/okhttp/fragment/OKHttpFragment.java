package com.study.hong.zyhdemo.frame.okhttp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.study.hong.zyhdemo.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.yokeyword.fragmentation.SupportFragment;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 洪 on 2019/5/21.
 * 记得在build.gradle中配置上implementation 'com.zhy:okhttputils:2.6.2'
 */

public class OKHttpFragment extends SupportFragment implements View.OnClickListener {
    private static final int GET = 1;
    private static final int POST = 2;
    private static final String TAG = "zyh-OKHttpFragment";
    private Button btn_get_post;
    private TextView tv_result;
    private Button btn_get_okhttputils;
    private Button btn_downloadfile;
    private ProgressBar mProgressBar;
    private Button btn_uploadfile;
    private Button btn_image;
    private Button btn_image_list;
    private ImageView iv_icon;
    private Object getRequest;

    private OkHttpClient client = new OkHttpClient();
    private Object dataFromPost;

    public static OKHttpFragment newInstance() {
        Bundle args = new Bundle();
        OKHttpFragment fragment = new OKHttpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                    tv_result.setText((String) msg.obj);
                    break;
                case POST:
                    tv_result.setText((String) msg.obj);
                    break;
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.okhttp_main_fragment, container, false);
        btn_get_post = view.findViewById(R.id.btn_get_post);
        tv_result = view.findViewById(R.id.tv_result);
        btn_get_okhttputils = view.findViewById(R.id.btn_get_okhttputils);
        btn_downloadfile = view.findViewById(R.id.btn_downloadfile);
        mProgressBar = view.findViewById(R.id.progressBar);
        btn_uploadfile = view.findViewById(R.id.btn_uploadfile);
        iv_icon = view.findViewById(R.id.iv_icon);
        btn_image = view.findViewById(R.id.btn_image);
        btn_image_list = view.findViewById(R.id.btn_image_list);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置点击事件
        btn_get_post.setOnClickListener(this);
        btn_get_okhttputils.setOnClickListener(this);
        btn_downloadfile.setOnClickListener(this);
        btn_uploadfile.setOnClickListener(this);
        btn_image.setOnClickListener(this);
        btn_image_list.setOnClickListener(this);
        //设置textview可以滚动
        tv_result.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //原生get和post
        if (id == R.id.btn_get_post) {
            tv_result.setVisibility(View.VISIBLE);
            getDataFromPost();
        }else if (id==R.id.btn_get_okhttputils){
            tv_result.setVisibility(View.VISIBLE);
            getDataGetByOkhttpUtils();
        }else if (id==R.id.btn_downloadfile){
            tv_result.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
            downloadFile();
        }else if (id==R.id.btn_uploadfile){
            mProgressBar.setVisibility(View.VISIBLE);
            multiFileUpload();
        }
        else if (id==R.id.btn_image){
            getImage();
        }else if (id==R.id.btn_image_list){
           start(OKHttpListActivity.newInstance());
        }

    }


    /**
     * 原生的请求方式，放在子线程中
     */
    String getGETRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 使用get请求网络数据
     */
    private void getDataFromGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    String result = get("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Log.e("TAG", result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * get请求
     *
     * @param url 网络连接
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 原生的请求方式，放在子线程中
     * 使用post请求网络数据
     */
    public void getDataFromPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = getGETRequest("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Message message = Message.obtain();
                    message.what = POST;
                    message.obj = result;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 使用okhttp-utils的get请求网络文本数据
     */
    public void getDataGetByOkhttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    /**
     * 使用okhttp-utils的post请求网络文本数据
     */
    public void getDataPostByOkhttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
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
            Log.e("zyh","onBefore");
        }

        @Override
        public void onAfter(int id) {
            Log.e("zyh","onAfter");
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            tv_result.setText("onResponse:" + response);

            switch (id) {
                case 100:
                    Toast.makeText(_mActivity, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(_mActivity, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
            mProgressBar.setVisibility(View.INVISIBLE);
            Log.e("zyh", "onResponse");

        }

        @Override
        public void inProgress(float progress, long total, int id) {
//            Log.e(TAG, "inProgress:" + progress+"--total:"+total);
            mProgressBar.setProgress((int) (100 * progress));
//            Log.e("zyh", "inProgress");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tv_result.setText("onError:" + e.getMessage());
            mProgressBar.setVisibility(View.INVISIBLE);
            Log.e("zyh", "onError");
        }
    }

    /**
     * 使用okhttp-utils下载大文件
     */
    public void downloadFile()
    {
        String url = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724055620533327_480.mp4";
        //参数1：路径          //参数2：文件名
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"zyh","okhttp-utils-test.mp4") {
            @Override
            public void onBefore(Request request, int id)
            {
            }

            @Override
            public void inProgress(float progress, long total, int id)
            {
                mProgressBar.setProgress((int) (100 * progress));
//                Log.e(TAG, "inProgress:" + progress+"--total:"+total);
            }

            @Override
            public void onError(Call call, Exception e, int id)
            {
                Log.e(TAG, "onError :" + e.getMessage());
            }

            @Override
            public void onResponse(File file, int id)
            {
                Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    /**
     * 使用okhttp-utils上传多个或者单个文件
     */
    public void multiFileUpload()
    {
        String mBaseUrl = "http://192.168.1.105:8080/FileUpload/FileUploadServlet";
//        File file = new File(Environment.getExternalStorageDirectory()+File.separator+"zyh", "zyh.png");
        File file2 = new File(Environment.getExternalStorageDirectory()+File.separator+"zyh", "okhttp-utils-test.mp4");
//        if (!file.exists()||!file2.exists())
        if (!file2.exists())
        {
            Toast.makeText(_mActivity, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张宇洪");
        params.put("password", "123");

        String url = mBaseUrl ;
        OkHttpUtils.post()//
//                .addFile("mFile", "server_afu.png", file)//
                .addFile("mFile", "server_test.txt", file2)//
                .url(url)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }

    /**
     * 请求单张图片
     */
    public void getImage()
    {
        tv_result.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tv_result.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        iv_icon.setImageBitmap(bitmap);
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
