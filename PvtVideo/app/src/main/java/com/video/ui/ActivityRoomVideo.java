package com.video.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.db.core.Core;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.pili.pldroid.player.widget.PLVideoView;
import com.pvt.bean.RoomInfoBean;
import com.pvt.bean.RoomsBean;
import com.pvt.pvtvideo.R;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;
import com.video.util.MediaController;

import cz.msebera.android.httpclient.Header;

public class ActivityRoomVideo extends AppCompatActivity {

    private PLVideoView mVideoView;

    private String livePlatformId,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        livePlatformId = getIntent().getStringExtra("livePlatformId");
        no = getIntent().getStringExtra("no");

        mVideoView = findViewById(R.id.PLVideoView);

        data();
      //  setting("");
    }

    public void data(){
        // app=nice
        // livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b
        // token=0f8420f60e4a43ca9b4d60e59919f5a9
        // userId=161be15eeeea48f384ae3bba624f60d0
        RequestParams params = new RequestParams();
        params.put("app","nice");
        params.put("livePluginId",livePlatformId);
        params.put("no", no);
        params.put("userId",Core.userDb.getId());

        HttpUtils.post(HttpUtils.ROOM_INFO, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if(statusCode == 200){

                    RoomInfoBean rib =  GsonUtils.parseJson(responseString,RoomInfoBean.class);
                    setting(rib.getResult().getPlayUrl());

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

        });



    }

    private void setting(String url) {
        /*设置播放地址
        这是最重要的环节，在调用播放器的控制接口之前，必须先设置好播放地址。
        传入播放地址，可以是 /path/to/local.mp4 本地文件绝对路径，或 HLS URL，或 RTMP URL*/
        mVideoView.setVideoPath(url);



       /* 关联播放控制器
        Demo 里面提供了一个播放控制器示例，MediaController，您可以随意修改满足定制化需求*/
        MediaController mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);

        /*设置加载动画    该 loadingView 可以是任意的 Android View 视图对象。*/
//        View loadingView = findViewById(R.id.LoadingView);
//        mVideoView.setBufferingIndicator(loadingView);


        // 视频截图

    }


    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }
}
