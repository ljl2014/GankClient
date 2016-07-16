package com.monsterlin.gankclient.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.monsterlin.gankclient.R;
import com.monsterlin.gankclient.activity.ArticleActivity;
import com.monsterlin.gankclient.adapter.ArticleAdapter;
import com.monsterlin.gankclient.bean.Data;
import com.monsterlin.gankclient.bean.Results;
import com.monsterlin.gankclient.biz.OnItemClickListener;
import com.monsterlin.gankclient.widget.LoadingDialog;

import java.util.ArrayList;

/**
 * Created by monsterLin on 7/16/2016.
 */
public class BaseFragment extends Fragment {
    public View view;
    public SpringView springView;
    public RecyclerView articleRecycler;
    public Context mContext;
    public LoadingDialog dialog;
    public ArticleAdapter articleAdapter;
    public ArrayList<Results> articleList = new ArrayList<>();


    public static final int INITSUCCESS = 0x001;

    public int currentPage = 0;
    public int pagenumber = 15;


    public Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INITSUCCESS:

                    ArrayList<Results> resultsArrayList = ((Data) msg.obj).getResults();
                    articleList.addAll(resultsArrayList);
                    articleAdapter = new ArticleAdapter(articleList, mContext);
                    articleRecycler.setAdapter(articleAdapter);
                    articleRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                    dialog.dismissDialog();
                    articleAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void OnItemClick(int position, View view) {

                            Intent articleIntent = new Intent(mContext, ArticleActivity.class);
                            articleIntent.putExtra("url", articleAdapter.getResult(position).getUrl());
                            startActivity(articleIntent);
                        }
                    });
                    break;
                default:
                    break;


            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_article_list, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }


    public void initView(View view) {
        springView = (SpringView) view.findViewById(R.id.springView);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new DefaultHeader(mContext));
        springView.setFooter(new DefaultFooter(mContext));
        articleRecycler = (RecyclerView) view.findViewById(R.id.articleRecycler);
        dialog = new LoadingDialog(mContext);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }


    public void initData() {

    }


    public void initEvent() {

    }
}
