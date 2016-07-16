package com.monsterlin.gankclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.monsterlin.gankclient.BaseActivity;
import com.monsterlin.gankclient.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by monsterLin on 7/15/2016.
 */
public class ImgActivity extends BaseActivity {

    private Toolbar toolbar;
    private ImageView iv_pic;
    private String picUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        picUrl = getIntent().getStringExtra("url");
        initView();
        ImageLoader.getInstance().displayImage(picUrl, iv_pic);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, "详情", true);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "图片地址："+"\n"+picUrl+"\n"+"感谢gank.io提供数据接口");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
