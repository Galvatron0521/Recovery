package com.shenkangyun.recoveryproject.HomePage.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shenkangyun.recoveryproject.BeanFolder.AdaptationBean;
import com.shenkangyun.recoveryproject.HomePage.Adapter.AdaptationAdapter;
import com.shenkangyun.recoveryproject.R;
import com.shenkangyun.recoveryproject.UtilsFolder.FuncUtil;
import com.shenkangyun.recoveryproject.UtilsFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdaptationActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.adaptationRecycler)
    RecyclerView adaptationRecycler;

    private LinearLayoutManager layoutManager;
    private AdaptationAdapter adaptationAdapter;
    private List<AdaptationBean> adaptationBeans;

    private int viewPosition;
    private List<String> unitName;
    private List<String> unitPhone;
    private List<String> unitContacts;

    private String[] name = new String[]{"财源街道", "岱庙", "邱家店", "上高", "省庄", "徐家楼", "泰前"};
    private String[] contacts = new String[]{"孟方圆", "李国祥", "刘娜", "武翠萍", "刘贞", "薛新春", "陈燕"};
    private String[] number = new String[]{"13695382671", "18854822444", "13583891097", "13562806898", "15854864400", "15163897551", "15866016629"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaptation);
        ButterKnife.bind(this);
        FuncUtil.iniSystemBar(this, R.color.head_bg);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("机构列表");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        adaptationBeans = new ArrayList<>();
        adaptationAdapter = new AdaptationAdapter();
        layoutManager = new LinearLayoutManager(this);
        adaptationRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        adaptationRecycler.setLayoutManager(layoutManager);
        adaptationRecycler.setAdapter(adaptationAdapter);
    }

    private void initData() {
        unitName = Arrays.asList(name);
        unitPhone = Arrays.asList(number);
        unitContacts = Arrays.asList(contacts);
        AdaptationBean adaptationBean = new AdaptationBean(1);
        adaptationBean.setUnitName("泰山区残疾人综合服务中心");
        adaptationBean.setUnitPhone("8519676");
        adaptationBean.setUnitContacts("齐学伟");
        adaptationBeans.add(adaptationBean);
        for (int i = 0; i < 7; i++) {
            AdaptationBean adaptation = new AdaptationBean(2);
            adaptation.setUnitName(unitName.get(i));
            adaptation.setUnitPhone(unitPhone.get(i));
            adaptation.setUnitContacts(unitContacts.get(i));
            adaptationBeans.add(adaptation);
        }
        adaptationAdapter.setNewData(adaptationBeans);

        adaptationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_position:
                        Intent intentService = new Intent(AdaptationActivity.this, ServiceActivity.class);
                        intentService.putExtra("X", 117.178206);
                        intentService.putExtra("Y", 36.211912);
                        intentService.putExtra("unitName", "泰山区残疾人综合服务中心");
                        intentService.putExtra("unitContacts", "齐学伟");
                        intentService.putExtra("unitPhone", "8519676");
                        startActivity(intentService);
                        break;
                    case R.id.tv_phone:
                        viewPosition = position;
                        if (ContextCompat.checkSelfPermission(AdaptationActivity.this,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // 没有获得授权，申请授权
                            if (ActivityCompat.shouldShowRequestPermissionRationale(AdaptationActivity.this,
                                    Manifest.permission.CALL_PHONE)) {
                                Toast.makeText(AdaptationActivity.this, "请授权！", Toast.LENGTH_LONG).show();

                                // 帮跳转到该应用的设置界面，让用户手动授权
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                // 不需要解释为何需要该权限，直接请求授权
                                ActivityCompat.requestPermissions(AdaptationActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            }
                        } else {
                            // 已经获得授权，可以打电话
                            CallPhone();
                        }
                        break;
                }
            }
        });
    }

    private void CallPhone() {
        View position = adaptationRecycler.getLayoutManager().findViewByPosition(viewPosition);
        TextView tvPhone = position.findViewById(R.id.tv_phone);
        String callPhone = tvPhone.getText().toString();
        if (TextUtils.isEmpty(callPhone)) {
            Toast.makeText(this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 拨号：激活系统的拨号组件
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + callPhone);
            intent.setData(data);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    CallPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
