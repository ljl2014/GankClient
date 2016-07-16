package com.monsterlin.gankclient.ui.article;

import android.util.Log;

import com.liaoinstan.springview.widget.SpringView;
import com.monsterlin.gankclient.bean.Data;
import com.monsterlin.gankclient.http.HttpMethods;
import com.monsterlin.gankclient.ui.BaseFragment;
import com.monsterlin.gankclient.utils.SnackbarUtil;

import rx.Subscriber;

/**
 * Created by monsterLin on 7/16/2016.
 */
public class IosFragment extends BaseFragment {

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

        HttpMethods.getInstance().getDatas(subscriber, "iOS", pagenumber, currentPage);
        currentPage++;
    }

    @Override
    public void initEvent() {
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
                            articleRecycler.scrollToPosition(articleAdapter.getItemCount() - 13);
                            SnackbarUtil.ShortSnackbar(view, "各位哥哥，小女子给你刷出了15条数据", SnackbarUtil.Confirm).show();
                        }
                    }
                };
                HttpMethods.getInstance().getDatas(subscriber, "iOS", pagenumber, currentPage);
                currentPage++;
                springView.onFinishFreshAndLoad();
            }

        });
    }
}
