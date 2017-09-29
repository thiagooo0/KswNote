package com.ksw.kswnote.mainpage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.ksw.kswnote.R;
import com.ksw.kswnote.addnote.AddNoteFragment;
import com.ksw.kswnote.base.BaseFragment;
import com.ksw.kswnote.base.BaseFragment.FragmentType;
import com.ksw.kswnote.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    private BaseFragment currentFragment;
    private MainPageFragment mainPageFragment;
    private AddNoteFragment addNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //插入布局
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //插入一个新的toolbar
        initToolBar();
        //初始化侧边栏
        initDrawerLayout();

//        initRecycleView();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content, new MainPageFragment()).commit();
        if (savedInstanceState == null) {
            mainPageFragment = new MainPageFragment();
            //底下是首页笔记列表
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, mainPageFragment).commit();
            currentFragment = mainPageFragment;
        }

        initFloatingButton();
    }

    private void showFragmentContent() {
        int cx = (binding.layoutMainPage.floatingActionButton.getLeft()
                + binding.layoutMainPage.floatingActionButton.getRight()) / 2;
        int cy = (binding.layoutMainPage.floatingActionButton.getTop()
                + binding.layoutMainPage.floatingActionButton.getBottom()) / 2 - binding.layoutMainPage.toolbar.getHeight();
        //做动画
        Animator animator = ViewAnimationUtils.createCircularReveal(binding.layoutMainPage.content
                , cx, cy, 0, binding.layoutMainPage.content.getHeight());


        //在动画开始的地方速率改变比较慢,然后开始加速
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(300);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                binding.layoutMainPage.content.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                binding.layoutMainPage.mainContent.setVisibility(View.GONE);
            }
        });
    }

    private void hideFragmentContent() {
        int cx = (binding.layoutMainPage.floatingActionButton.getLeft()
                + binding.layoutMainPage.floatingActionButton.getRight()) / 2;
        int cy = (binding.layoutMainPage.floatingActionButton.getTop()
                + binding.layoutMainPage.floatingActionButton.getBottom()) / 2 - binding.layoutMainPage.toolbar.getHeight();

        binding.layoutMainPage.mainContent.setVisibility(View.VISIBLE);

        //做动画
        Animator animator = ViewAnimationUtils.createCircularReveal(binding.layoutMainPage.content
                , cx, cy, binding.layoutMainPage.content.getHeight(), 0);

        //在动画开始的地方速率改变比较慢,然后开始加速
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(300);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                binding.layoutMainPage.content.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 更新fragment
     **/
    public void updateFragment(BaseFragment.FragmentType type) {
        if (currentFragment == null) {
            currentFragment = mainPageFragment;
        } else {
            //自己切换成自己就不用麻烦了
            if (currentFragment.getType() != type) {
                FragmentManager manager = getSupportFragmentManager();
                //回到推荐列表
                if (type == FragmentType.MainPageFragment) {
//                    binding.layoutMainPage.content.bringToFront();
                    currentFragment = mainPageFragment;
                    hideFragmentContent();
                } else {
                    //在推荐列表上新增东西
                    if (currentFragment.getType() == FragmentType.MainPageFragment) {
                        binding.layoutMainPage.content.bringToFront();
                        binding.layoutMainPage.content.setVisibility(View.VISIBLE);

                        //添加新笔记
                        if (type == FragmentType.AddNoteFragment) {
                            if (addNoteFragment == null) {
                                addNoteFragment = new AddNoteFragment();
                            }
                            if (addNoteFragment.isAdded()) {
                                manager.beginTransaction().show(addNoteFragment).commit();
                            } else {
                                manager.beginTransaction().add(R.id.content, addNoteFragment).commit();
                            }
                            currentFragment = addNoteFragment;
                        }
                        showFragmentContent();
                    }
                    //在推荐列表上切换
                    else {

                    }

                }

//                //如果是从首页切换到其他页面的话
//                if (currentFragment.getType() == BaseFragment.FragmentType.MainPageFragment) {
//                    //如果是切换到添加笔记页面
//                    if (type == BaseFragment.FragmentType.AddNoteFragment) {
//
////                        manager.beginTransaction().add(R.id.content, addNoteFragment)
////                                .hide(mainPageFragment).commit();
//                        currentFragment = addNoteFragment;
//                    }
//                } else if (currentFragment.getType() == BaseFragment.FragmentType.AddNoteFragment) {
//                    //如果是切换回主页
//                    if (type == BaseFragment.FragmentType.MainPageFragment) {
//                        if (mainPageFragment.isAdded()) {
//                            manager.beginTransaction().hide(addNoteFragment).commit();
//                        } else {
////                            manager.beginTransaction().add(R.id.content, mainPageFragment).commit();
//                        }
//                        currentFragment = mainPageFragment;
//                    }
//                }
            }
        }
    }

    private void initFloatingButton() {
        binding.layoutMainPage.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cx = (v.getLeft() + v.getRight()) / 2 + 100;
                int cy = (v.getTop() + v.getBottom()) / 2 + 100;

//                AddNoteFragment fragment = new AddNoteFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                if (currentFragment.getType() == FragmentType.AddNoteFragment) {
                    //切换到主页
//                    mainPageFragment.setShowCircularReveal(true, cx, cy);
//                    updateFragment(FragmentType.MainPageFragment);
                    addNoteFragment.completeNote();
                } else if (currentFragment.getType() == FragmentType.MainPageFragment) {
//                    addNoteFragment.setShowCircularReveal(true, cx, cy);
                    updateFragment(FragmentType.AddNoteFragment);
                }
            }
        });
    }

//    private void initRecycleView() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.layoutMainPage.recyclerView.setLayoutManager(linearLayoutManager);
//        //设置Item增加、移除动画
////        binding.layoutMainPage.recyclerView.addHeaderView(headerView);
//        binding.layoutMainPage.recyclerView.setItemAnimator(new DefaultItemAnimator());
//        binding.layoutMainPage.recyclerView.setLoadingMoreEnabled(true);
//        binding.layoutMainPage.recyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
//        binding.layoutMainPage.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
//        binding.layoutMainPage.recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
//
//            }
//        });
//        //一无所有的时候显示什么
//        binding.layoutMainPage.recyclerView.setEmptyView(binding.layoutMainPage.emptyView.getRoot());
//
//        ArrayList<Note> list = new ArrayList<Note>();
//        list.add(new LocalNote("还等吗，你爱戴钻戒，要他爱戴吗"));
//        list.add(new LocalNote("无人忘记仍能闪闪发光"));
//        list.add(new LocalNote("何必等他一吻去韬光"));
//        list.add(new LocalNote("从某年某天某地"));
//        list.add(new LocalNote("谁得到过愿放手"));
//        list.add(new LocalNote("如果失约在这生，无需相见在某年"));
//        list.add(new LocalNote("谈你谈我的新趣味，无法忘记当天的你"));
//        list.add(new LocalNote("完完全全共醉他生也愿意"));
//
//        BaseAdapter adapter = new BaseAdapter(list, R.layout.item_note, BR.note);
//        binding.layoutMainPage.recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }

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
