package com.monsterlin.gank.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsterlin.gank.R;
import com.monsterlin.gank.adapter.ViewPagerAdapter;
import com.monsterlin.gank.ui.article.AndroidFragment;
import com.monsterlin.gank.ui.article.HtmlFragment;
import com.monsterlin.gank.ui.article.IosFragment;
import com.monsterlin.gank.ui.article.ResFragment;

/**
 * Created by monsterLin on 2016/7/17.
 */
public class ArticleFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int cachePagers = 3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_article, container, false);
        initView(view);
        initMainContent();
        return view;

    }

    private void initView(View view) {
        mTabLayout= (TabLayout) view.findViewById(R.id.tl_article_tabs);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_article_content);
    }

    /**
     * 初始化viewpager
     */
    private void initMainContent() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());

        Fragment androidFragment = new AndroidFragment();
        Fragment iosFragment = new IosFragment();
        Fragment htmlFragment = new HtmlFragment();
        Fragment resFragment = new ResFragment();


        adapter.addFragment(androidFragment, "Android");
        adapter.addFragment(iosFragment, "Ios");
        adapter.addFragment(htmlFragment, "Html");
        adapter.addFragment(resFragment, "拓展资源");


        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(cachePagers);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
