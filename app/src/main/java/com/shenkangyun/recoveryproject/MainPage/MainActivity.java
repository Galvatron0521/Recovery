package com.shenkangyun.recoveryproject.MainPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenkangyun.recoveryproject.CalendarPage.CalendarPageFragment;
import com.shenkangyun.recoveryproject.CommunityPage.CommunityPageFragment;
import com.shenkangyun.recoveryproject.CommunityPage.PublishInfoActivity;
import com.shenkangyun.recoveryproject.HomePage.HomePageFragment;
import com.shenkangyun.recoveryproject.LocationPage.LocationPageFragment;
import com.shenkangyun.recoveryproject.MinePage.MinePageActivity;
import com.shenkangyun.recoveryproject.R;
import com.shenkangyun.recoveryproject.ToolPage.ToolPageFragment;
import com.shenkangyun.recoveryproject.UtilsFolder.FuncUtil;
import com.shenkangyun.recoveryproject.UtilsFolder.NetworkChangeReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_user)
    ImageView imgUser;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver changeReceiver;

    private FragmentManager fragmentManager;
    private Fragment HomeFragment, CalendarFragment, CommunityFragment, NearByFragment, ToolFragment, replaceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        ButterKnife.bind(this);
        intentFilter = new IntentFilter();
        changeReceiver = new NetworkChangeReceiver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(changeReceiver, intentFilter);
        initView();
        initFragment();
    }

    private void initView() {
        ToolFragment = new ToolPageFragment();
        HomeFragment = new HomePageFragment();
        NearByFragment = new LocationPageFragment();
        CalendarFragment = new CalendarPageFragment();
        CommunityFragment = new CommunityPageFragment();
        tvTitle.setText("首页");
        fragmentManager = getSupportFragmentManager();
    }

    private void initFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_public, HomeFragment);
        fragmentTransaction.commit();
        replaceFragment = HomeFragment;
    }

    @OnClick({R.id.btn_home, R.id.btn_community, R.id.btn_tool, R.id.btn_calendar, R.id.btn_nearby})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.btn_tool:
                tvTitle.setText("辅具");
                imgMenu.setVisibility(View.GONE);
                imgUser.setVisibility(View.GONE);
                replaceFragment(ToolFragment, fragmentTransaction);
                break;
            case R.id.btn_nearby:
                tvTitle.setText("附近");
                imgMenu.setVisibility(View.GONE);
                imgUser.setVisibility(View.GONE);
                replaceFragment(NearByFragment, fragmentTransaction);
                break;
            case R.id.btn_home:
                tvTitle.setText("首页");
                imgMenu.setVisibility(View.GONE);
                imgUser.setVisibility(View.VISIBLE);
                replaceFragment(HomeFragment, fragmentTransaction);
                break;
            case R.id.btn_community:
                tvTitle.setText("社区");
                imgMenu.setVisibility(View.VISIBLE);
                imgUser.setVisibility(View.GONE);
                replaceFragment(CommunityFragment, fragmentTransaction);
                break;
            case R.id.btn_calendar:
                tvTitle.setText("日历");
                imgMenu.setVisibility(View.GONE);
                imgUser.setVisibility(View.GONE);
                replaceFragment(CalendarFragment, fragmentTransaction);
                break;
        }
    }

    public void replaceFragment(Fragment showFragment, FragmentTransaction fragmentTransaction) {
        if (showFragment.isAdded()) {
            fragmentTransaction.hide(replaceFragment).show(showFragment).commit();
        } else {
            fragmentTransaction.hide(replaceFragment).add(R.id.layout_public, showFragment).commit();
        }
        replaceFragment = showFragment;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(changeReceiver);
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9 && resultCode == 8) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            tvTitle.setText("社区");
            imgMenu.setVisibility(View.VISIBLE);
            replaceFragment(CommunityFragment, fragmentTransaction);
        }
    }

    @OnClick({R.id.img_menu, R.id.img_user})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                Intent intentPublish = new Intent(this, PublishInfoActivity.class);
                startActivityForResult(intentPublish, 9);
                break;
            case R.id.img_user:
                Intent intentMine = new Intent(this, MinePageActivity.class);
                startActivityForResult(intentMine, 7);
                break;
        }
    }
}
