package com.monsterlin.gankclient.ui.article;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.monsterlin.gankclient.R;
import com.monsterlin.gankclient.activity.ArticleActivity;
import com.monsterlin.gankclient.adapter.ArticleAdapter;
import com.monsterlin.gankclient.bean.Data;
import com.monsterlin.gankclient.bean.Results;
import com.monsterlin.gankclient.biz.OnItemClickListener;
import com.monsterlin.gankclient.http.HttpMethods;
import com.monsterlin.gankclient.utils.SnackbarUtil;
import com.monsterlin.gankclient.widget.LoadingDialog;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by monsterLin on 7/16/2016.
 */
public class IosFragment extends Fragment {
    private SpringView springView;
    private RecyclerView articleRecycler;
    private ArticleAdapter articleAdapter;
    private ArrayList<Results> articleList = new ArrayList<>();
    private Subscriber subscriber;
    private Context mContext;
    private LoadingDialog dialog;
    private View view;

    private static final int INITSUCCESS = 0x001;

    private int currentPage = 0;
    private int pagenumber = 15;


    private Handler mHandle = new Handler() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_article_list, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                SnackbarUtil.ShortSnackbar(view, "各位哥哥，已经刷不出来了~", SnackbarUtil.Info).show();
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                subscriber = new Subscriber<Data>() {

                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", "onError：" + e.getMessage());
                    }

                    @Override
                    public void onNext(Data article) {
                        if (article != null) {
                            articleList.addAll(article.getResults());
                            articleAdapter.notifyDataSetChanged();
                        }
                    }
                };
                HttpMethods.getInstance().getDatas(subscriber, "iOS", pagenumber, currentPage);
                currentPage++;
                springView.onFinishFreshAndLoad();
            }

        });
    }


    private void initData() {
        dialog.showDialog();
        subscriber = new Subscriber<Data>() {

            @Override
            public void onCompleted() {
                Log.e("onCompleted", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError", "onError：" + e.getMessage());
            }

            @Override
            public void onNext(Data article) {
                mHandle.obtainMessage(INITSUCCESS, article).sendToTarget();
            }
        };

        HttpMethods.getInstance().getDatas(subscriber, "iOS", pagenumber, currentPage);
        currentPage++;
    }


    private void initView(View view) {
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

}
