package com.ksw.kswnote.mainpage;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ksw.kswnote.BR;
import com.ksw.kswnote.R;
import com.ksw.kswnote.been.LocalNote;
import com.ksw.kswnote.been.Note;
import com.ksw.kswnote.databinding.ActivityMainBinding;
import com.ksw.kswnote.mrecyclerview.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //插入布局
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        //插入一个新的toolbar
        initToolBar();
        //初始化侧边栏
        initDrawerLayout();

        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.layoutMainPage.recyclerView.setLayoutManager(linearLayoutManager);
        //设置Item增加、移除动画
//        binding.layoutMainPage.recyclerView.addHeaderView(headerView);
        binding.layoutMainPage.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.layoutMainPage.recyclerView.setLoadingMoreEnabled(true);
        binding.layoutMainPage.recyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        binding.layoutMainPage.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        binding.layoutMainPage.recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });

        ArrayList<Note> list = new ArrayList<Note>();
        list.add(new LocalNote("还等吗，你爱戴钻戒，要他爱戴吗"));
        list.add(new LocalNote("无人忘记仍能闪闪发光"));
        list.add(new LocalNote("何必等他一吻去韬光"));
        list.add(new LocalNote("从某年某天某地"));
        list.add(new LocalNote("谁得到过愿放手"));
        list.add(new LocalNote("如果失约在这生，无需相见在某年"));
        list.add(new LocalNote("谈你谈我的新趣味，无法忘记当天的你"));
        list.add(new LocalNote("完完全全共醉他生也愿意"));

        SimpleAdapter adapter = new SimpleAdapter(list, R.layout.item_note, BR.note);
        binding.layoutMainPage.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initToolBar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(binding.layoutMainPage.toolbar);
    }

    private void initDrawerLayout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //显示拖动状态的控件
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.layoutMainPage.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
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
