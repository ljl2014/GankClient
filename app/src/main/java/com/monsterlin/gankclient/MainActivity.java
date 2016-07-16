package com.monsterlin.gankclient;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;

import com.monsterlin.gankclient.ui.AboutFragment;
import com.monsterlin.gankclient.ui.PicFragment;
import com.monsterlin.gankclient.ui.ArticleFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

/**
 * <p>本应用纯属娱乐，谢谢</p>
 *
 * @author monsterLin
 */
public class MainActivity extends BaseActivity {

    private BottomBar mBottomBar;

    private String []tags = {
      "picFragment","articleFragment","aboutFragment"
    };

    private int curIndex = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBar = BottomBar.attach(this, savedInstanceState);  //关联当前Activity

        initEvent();
    }

    private void initEvent() {

        //如果底部为大于3个，会有意想不到的效果

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {

            /**
             * 当tab被选中时候触发
             * @param menuItemId
             */
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.bottomBarPic:
                        setSelect(0);
                        break;
                    case R.id.bottomBarArticle:
                        setSelect(1);
                        break;
                    case R.id.bottomBarAbout:
                        setSelect(2);
                        break;
                }
            }

            /**
             * 当前选中的 tab 再次被点击时候触发
             * @param menuItemId
             */
            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.bottomBarPic:
                        break;
                    case R.id.bottomBarArticle:
                        break;
                    case R.id.bottomBarAbout:
                        break;
                }
            }
        });

//        int redColor = Color.parseColor("#FF0000");
//        BottomBarBadge nearbyBadge = mBottomBar.makeBadgeForTabAt(1, redColor, 5);
//        nearbyBadge.setAutoShowAfterUnSelection(true);

        //设置导航栏选中时候的颜色 导航>3的时候有效
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }


    /**
     * 切换Fragment
     *
     * @param i 切换页
     */
    private void setSelect(int i) {

        if (curIndex == i)
            return;


        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tags[i]);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = new PicFragment();
                    break;
                case 1:
                    fragment = new ArticleFragment();
                    break;
                case 2:
                    fragment = new AboutFragment();
                    break;
              default:
                  break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, fragment, tags[i]).commit();
        }
        for (int j = 0; j < 3; j++) {
            Fragment f = getSupportFragmentManager().findFragmentByTag(tags[j]);
            if (f != null) {
                getSupportFragmentManager().beginTransaction().hide(f).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commit();


    }
}
