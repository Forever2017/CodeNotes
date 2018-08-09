package com.video.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.db.bean.PvtDB;
import com.db.util.DButil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.pili.pldroid.player.widget.PLVideoView;
import com.pvt.bean.RoomInfoBean;
import com.pvt.bean.RoomsBean;
import com.pvt.pvtvideo.ActivityRegistered;
import com.pvt.pvtvideo.ActivityRoomList;
import com.pvt.pvtvideo.R;
import com.pvt.util.GsonUtils;
import com.pvt.util.HttpUtils;
import com.video.util.MediaController;

import org.litepal.LitePal;

import cz.msebera.android.httpclient.Header;

public class ActivityRoomVideo extends AppCompatActivity implements View.OnClickListener {

    private PLVideoView mVideoView;

    private String livePlatformId, no;
    private LinearLayout VideoTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        livePlatformId = getIntent().getStringExtra("livePlatformId");
        no = getIntent().getStringExtra("no");

        VideoTool = findViewById(R.id.VideoTool);

        mVideoView = findViewById(R.id.PLVideoView);
        mVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        data();
        //  setting("");
    }

    public void data() {
        // app=nice
        // livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b
        // token=0f8420f60e4a43ca9b4d60e59919f5a9
        // userId=161be15eeeea48f384ae3bba624f60d0
        RequestParams params = new RequestParams();
        params.put("app", "nice");
        params.put("livePluginId", livePlatformId);
        params.put("no", no);

        params.put("userId", DButil.PvtDBvaule("userId"));

        HttpUtils.post(HttpUtils.ROOM_INFO, params, new HttpUtils.AsyncHttp() {
            @Override
            public void onSuccess(String responseString) {
                super.onSuccess(responseString);
                RoomInfoBean rib = GsonUtils.parseJson(responseString, RoomInfoBean.class);

                if (rib.getType().equals("true")) {
                    setting(rib.getResult().getPlayUrl());
                } else {
                    Toast.makeText(getApplicationContext(), rib.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityRoomVideo.this, ActivityRegistered.class));
                }

            }

            @Override
            public void NetworkError() {
                super.NetworkError();
            }
        });


    }

    private void setting(String url) {
        /*设置播放地址
        这是最重要的环节，在调用播放器的控制接口之前，必须先设置好播放地址。
        传入播放地址，可以是 /path/to/local.mp4 本地文件绝对路径，或 HLS URL，或 RTMP URL*/
        mVideoView.setVideoPath(url);

        /*画面预览模式，包括：原始尺寸、适应屏幕、全屏铺满、16:9、4:3 等 */
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);

       /* 关联播放控制器
        Demo 里面提供了一个播放控制器示例，MediaController，您可以随意修改满足定制化需求*/
        MediaController mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);

        /*设置加载动画    该 loadingView 可以是任意的 Android View 视图对象。*/
//        View loadingView = findViewById(R.id.LoadingView);
//        mVideoView.setBufferingIndicator(loadingView);



       /* 获取时间戳
        getRtmpVideoTimestamp*/

        // 视频截图
        /** 可以通过 captureImage 方法进行视频截图，截图数据将会在 PLOnImageCapturedListener 中回调
         * Capture video image
         * @param delayTimeMs 截取调用此方法后相应毫秒后的视频画面，仅对点播流生效
         */
        //public void captureImage(long delayTimeMs);

        //隐藏点击后显示的下面的控制器
        mMediaController.setVisibility(View.GONE);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        data();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.PLVideoView:

                Toast.makeText(getApplicationContext(),"点击",Toast.LENGTH_LONG).show();
                if(VideoTool.getVisibility() == View.GONE)  VideoTool.setVisibility(View.GONE);
                else VideoTool.setVisibility(View.VISIBLE);

                break;
        }

    }
}
