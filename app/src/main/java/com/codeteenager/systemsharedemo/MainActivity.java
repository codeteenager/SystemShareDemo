package com.codeteenager.systemsharedemo;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_share_text)
    public void shareText() {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, "分享文本内容");
        startActivity(Intent.createChooser(textIntent, "分享文本标题"));
    }

    @OnClick(R.id.btn_share_weichat)
    public void shareWeichat() {
        Intent wechatIntent = new Intent(Intent.ACTION_SEND);
        wechatIntent.setPackage("com.tencent.mm");
        wechatIntent.setType("text/plain");
        wechatIntent.putExtra(Intent.EXTRA_TEXT, "分享文本内容");
        startActivity(wechatIntent);
    }

    @OnClick(R.id.btn_share_tencent)
    public void shareTencent() {
        Intent qqIntent = new Intent(Intent.ACTION_SEND);
        qqIntent.setPackage("com.tencent.mobileqq");
        qqIntent.setType("text/plain");
        qqIntent.putExtra(Intent.EXTRA_TEXT, "分享文本内容");
        startActivity(qqIntent);
    }


    @OnClick(R.id.btn_share_image)
    public void shareImage() {
        String path = getResourcesUri(R.drawable.image);
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/*");
        imageIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
        startActivity(Intent.createChooser(imageIntent, "分享"));
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }
}
