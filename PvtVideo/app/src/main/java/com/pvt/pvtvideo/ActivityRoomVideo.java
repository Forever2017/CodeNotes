package com.pvt.pvtvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pili.pldroid.player.widget.PLVideoView;

public class ActivityRoomVideo extends AppCompatActivity {

    private PLVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoView = findViewById(R.id.PLVideoView);

    }

    private void setting(String url){
        /*设置播放地址
        这是最重要的环节，在调用播放器的控制接口之前，必须先设置好播放地址。
        传入播放地址，可以是 /path/to/local.mp4 本地文件绝对路径，或 HLS URL，或 RTMP URL*/
        mVideoView.setVideoPath("rtmp://live.hkstv.hk.lxdns.com/live/hks");



       /* 关联播放控制器
        Demo 里面提供了一个播放控制器示例，MediaController，您可以随意修改满足定制化需求*/
//        MediaController mMediaController = new MediaController(this);
//        mVideoView.setMediaController(mMediaController);

        /*设置加载动画    该 loadingView 可以是任意的 Android View 视图对象。*/
//        View loadingView = findViewById(R.id.LoadingView);
//        mVideoView.setBufferingIndicator(loadingView);


       // 视频截图

    }



}
