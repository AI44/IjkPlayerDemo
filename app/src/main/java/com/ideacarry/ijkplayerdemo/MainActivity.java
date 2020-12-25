package com.ideacarry.ijkplayerdemo;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import tv.danmaku.ijk.media.example.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;

public class MainActivity extends AppCompatActivity {

    private AndroidMediaController mMediaController;
    private IjkVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVideoView = new IjkVideoView(this);
        mVideoView.setBackgroundColor(0xFF000000);
        mVideoView.setKeepScreenOn(true);
        mMediaController = new AndroidMediaController(this, false);
        mVideoView.setMediaController(mMediaController);
        setContentView(mVideoView);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET}, 777);
    }

    @Override
    protected void onStart() {
        //全屏
        final View decorView = getWindow().getDecorView();
        int flag = decorView.getSystemUiVisibility();
        flag |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//固定开始的布局，不随navigation bar显示/隐藏影响
        flag |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;//设置后布局会被status bar和navigation bar覆盖
        flag |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;//设置后布局会被status bar覆盖
        flag |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;//隐藏navigation bar
        flag |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;//bar 唤出后几秒就消失，不触发 Listener
        decorView.setSystemUiVisibility(flag);

        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        String url;

        //url = "http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear1/prog_index.m3u8";
        //url = "https://h.vdo.ai/videos/categories/cyber9.m3u8";
        //url = "http://cctvalih5ca.v.myalicdn.com/live/cctv1_2/index.m3u8";
        //url = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
        //url = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
        //url = "http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8";
        url = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";

        mVideoView.setVideoURI(Uri.parse(url));
        mVideoView.start();
    }
}