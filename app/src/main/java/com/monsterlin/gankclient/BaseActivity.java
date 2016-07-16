package com.monsterlin.gankclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by monsterLin on 7/15/2016.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 打印Toast 日志
     *
     * @param msg toast信息
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打印Log 日志
     * @param type  类型
     * @param log 日志内容
     */
    public void log(int type, String log) {
        switch (type) {
            case 1:
                Log.i("Info：",log);
                break;
            case 2:
                Log.v("Verbose",log);
                break;
            case 3:
                Log.e("Error",log);
                break;
            case 4:
                Log.w("Warn",log);
                break;
            case 5:
                Log.d("Debug",log);
                break;
            default:
                break;
        }


    }


    /**
     * 初始化ToolBar
     * @param toolbar
     * @param title
     */
    public void initToolBar(Toolbar toolbar , String title, boolean isBack){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if (isBack){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }
}
