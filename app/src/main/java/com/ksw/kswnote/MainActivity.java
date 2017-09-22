package com.ksw.kswnote;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //插入一个新的toolbar
        initToolBar();
        //初始化侧边栏
        initDrawerLayout();
    }

    Toolbar toolbar = null;

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initDrawerLayout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //显示拖动状态的控件
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //我们可以在这里做一点东西
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //我们可以在这里做一点东西
            }
        };
        drawer.addDrawerListener(toggle);
        //显示右上角的三杠图标
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        if (headerView != null) {
//            tv_username = (TextView) headerView.findViewById(R.id.tv_username);
//            tv_username.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    /*View view = getLayoutInflater().inflate(R.layout.activity_login, null);
//                    view.findViewById(R.id.btn_wechat).setOnClickListener(onClickListener);
//                    view.findViewById(R.id.btn_qq).setOnClickListener(onClickListener);
//                    // 设置dialog没有title
//                    Dialog dialog = new Dialog(MainActivity.this);
//                    dialog.setContentView(view, new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
//                    Window window = dialog.getWindow();
//                    // 可以在此设置显示动画
//                    WindowManager.LayoutParams wl = window.getAttributes();
//                    wl.x = 0;
//                    wl.y = getWindowManager().getDefaultDisplay().getHeight();
//                    // 以下这两句是为了保证按钮可以水平满屏
//                    wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                    wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                    // 设置显示位置
//                    dialog.onWindowAttributesChanged(wl);
//                    dialog.setTitle("快速登录");
//                    dialog.show();*/
//                }
//            });
//            userIcon = (ImageView) headerView.findViewById(R.id.user_icon);
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(Gravity.START);
        return true;
    }
}
