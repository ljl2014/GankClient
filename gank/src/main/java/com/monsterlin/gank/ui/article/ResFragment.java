package com.monsterlin.gank.ui.article;

import android.util.Log;

import com.liaoinstan.springview.widget.SpringView;
import com.monsterlin.gank.bean.Data;
import com.monsterlin.gank.constant.GankRefush;
import com.monsterlin.gank.http.HttpMethods;
import com.monsterlin.gank.ui.base.BaseFragment;
import com.monsterlin.gank.utils.SnackbarUtil;

import rx.Subscriber;

/**
 * Created by monsterLin on 2016/7/17.
 */
public class ResFragment extends BaseFragment {

    private Subscriber subscriber;

    @Override
    public void initData() {
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

        HttpMethods.getInstance().getDatas(subscriber, "拓展资源", pagenumber, currentPage);
        currentPage++;
    }

    @Override
    public void initEvent() {
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                SnackbarUtil.ShortSnackbar(view, GankRefush.lowRefushEnd, SnackbarUtil.Info).show();
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
                            articleRecycler.scrollToPosition(articleAdapter.getItemCount() - 13);
                            SnackbarUtil.ShortSnackbar(view, GankRefush.loadMoreEnd, SnackbarUtil.Confirm).show();
                        }
                    }
                };
                HttpMethods.getInstance().getDatas(subscriber, "拓展资源", pagenumber, currentPage);
                currentPage++;
                springView.onFinishFreshAndLoad();
            }

        });
    }
}
