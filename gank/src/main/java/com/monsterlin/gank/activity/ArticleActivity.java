package com.monsterlin.gank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.monsterlin.gank.BaseActivity;
import com.monsterlin.gank.R;
import com.monsterlin.gank.widget.ProgressWebView;


/**
 * Created by monsterLin on 7/16/2016.
 */
public class ArticleActivity extends BaseActivity {
    private String Url;
    private Toolbar toolbar;
    private SpringView springView;
    private ProgressWebView progressweb;
    private View headerView;
    private TextView tv_web, tv_intro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Url = getIntent().getStringExtra("url");
        initView();
        initWeb();
    }

    private void initWeb() {
        WebSettings settings = progressweb.getSettings();
        settings.setJavaScriptEnabled(true);
        progressweb.loadUrl(Url);
        tv_web.setText("网页由github.com 提供");  //TODO 拼接字符串实现对应网站提供
        tv_intro.setText("已启用 逗比 内核支持");
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, "详情", true);
        springView= (SpringView) findViewById(R.id.springView);
        progressweb= (ProgressWebView) findViewById(R.id.progressweb);

        headerView = springView.getHeaderView();
        tv_web = (TextView) headerView.findViewById(R.id.tv_web);
        tv_intro = (TextView) headerView.findViewById(R.id.tv_intro);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (progressweb.canGoBack()) {
                progressweb.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
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
                shareIntent.putExtra(Intent.EXTRA_TEXT, "文章地址："+"\n"+Url+"\n"+"感谢gank.io提供数据接口");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
