package com.monsterlin.gankclient.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsterlin.gankclient.R;
import com.monsterlin.gankclient.adapter.ViewPagerAdapter;
import com.monsterlin.gankclient.ui.article.AndroidFragment;
import com.monsterlin.gankclient.ui.article.IosFragment;

/**
 * Created by monsterLin on 7/15/2016.
 */
public class ArticleFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int cachePagers = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,container,false);
        initView(view);
        initMainContent();
        return view;
    }

    private void initView(View view) {
        mTabLayout= (TabLayout) view.findViewById(R.id.tl_tabs);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_content);
    }

    /**
     * 初始化viewpager
     */
    private void initMainContent() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());

        Fragment androidFragment = new AndroidFragment();
        Fragment iosFragment = new IosFragment();

        adapter.addFragment(androidFragment, "Android");
        adapter.addFragment(iosFragment, "Ios");

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(cachePagers);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
