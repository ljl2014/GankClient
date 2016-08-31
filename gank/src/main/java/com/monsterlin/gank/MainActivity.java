package com.monsterlin.gank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.monsterlin.gank.ui.ArticleFragment;
import com.monsterlin.gank.ui.PicsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener{
    @BindView(R.id.toolbar) Toolbar toolBar;

    @BindView(R.id.fab) FloatingActionButton fab;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    @BindView(R.id.nav_view) NavigationView navigationView;

    private ActionBarDrawerToggle toggle;
    private Menu menu;

    private String []tags = {
            "articleFragment","picsFragment"
    };

    private int curIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initToolBar(toolBar,"技术小黑屋",false);
        initMain();
        initEvents();
    }


    private void initMain() {
        Menu menu = navigationView.getMenu();
        menu.getItem(0).setChecked(true);
        setSelect(0);
    }

    private void initEvents() {


        /**
         * 悬浮按钮点击事件
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("测试");
            }
        });

        /**
         * 控制菜单的变化
         */
        toggle = new ActionBarDrawerToggle(
                this, drawer,    toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        /**
         * 菜单中的item的点击事件
         */
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        menu = navigationView.getMenu();

        switch (id){
            case R.id.action_article:
                menu.getItem(0).setChecked(true);
                setSelect(0);
                break;
            case R.id.action_images:
                menu.getItem(1).setChecked(true);
                fab.setVisibility(View.GONE);
                setSelect(1);
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

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
                    fragment = new ArticleFragment();
                    break;
                case 1:
                    fragment = new PicsFragment();
                    break;

                default:
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.frame_main, fragment, tags[i]).commit();
        }
        for (int j = 0; j < 2; j++) {
            Fragment f = getSupportFragmentManager().findFragmentByTag(tags[j]);
            if (f != null) {
                getSupportFragmentManager().beginTransaction().hide(f).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commit();

    }
}
