package com.shenkangyun.recoveryproject.HomePage.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.recoveryproject.BaseFolder.Base;
import com.shenkangyun.recoveryproject.BeanFolder.HearingBean;
import com.shenkangyun.recoveryproject.BeanFolder.HospitalBean;
import com.shenkangyun.recoveryproject.BeanFolder.LimbsBean;
import com.shenkangyun.recoveryproject.HomePage.Adapter.HearingAdapter;
import com.shenkangyun.recoveryproject.HomePage.Adapter.LimbsAdapter;
import com.shenkangyun.recoveryproject.R;
import com.shenkangyun.recoveryproject.UtilsFolder.FuncUtil;
import com.shenkangyun.recoveryproject.UtilsFolder.GsonCallBack;
import com.shenkangyun.recoveryproject.UtilsFolder.RecyclerViewDivider;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LimbsActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.limbsRecycler)
    RecyclerView limbsRecycler;

    private List<LimbsBean> limbsBeans;
    private LimbsAdapter limbsAdapter;
    private LinearLayoutManager layoutManager;

    private List<HospitalBean.DataBean.ListBean> listBeans;
    private String name;
    private String content;
    private String phone;
    private String scope;
    private String subsidy;
    private String xAxis;
    private String yAxis;
    private String contactsName;

    private String[] names = new String[]{"泰安市中医二院", "泰山医学院附属医院", "泰山区人民医院", "各街道卫生院"};
    private String[] contents = new String[]{"泰安市中医二院位于山东省泰安市灵山大街285号，是一所集医疗、教学、科研、预防为一体的中医综合医院，是泰安市医保定点医院。",
            "泰山医学院附属医院是一家三级甲等综合性医院。医院设有43个临床科室、8个医技科室。", "泰安市泰山区人民医院位于山东省泰安市东升平街42号，是一所二级综合医院。医院设有内科、外科、妇科、儿科、骨科、儿童康复科、妇幼保健科、麻醉科、影像科、理疗科等临床与医技科室。",
            "泰山区下辖的每个街道的卫生院"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limbs);
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
//        initHttp();
    }

    private void initView() {
        listBeans = new ArrayList<>();
        limbsBeans = new ArrayList<>();
        limbsAdapter = new LimbsAdapter();
        layoutManager = new LinearLayoutManager(this);
        limbsRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        limbsRecycler.setLayoutManager(layoutManager);
        limbsRecycler.setAdapter(limbsAdapter);
    }

    private void initData() {
        List<String> unitName = Arrays.asList(names);
        List<String> unitContent = Arrays.asList(contents);
        for (int i = 0; i < 4; i++) {
            LimbsBean limbsBean = new LimbsBean();
            limbsBean.setUnitName(unitName.get(i));
            limbsBean.setUnitCotent(unitContent.get(i));
            limbsBeans.add(limbsBean);
        }
        limbsAdapter.setNewData(limbsBeans);

        limbsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.unitParent:
                        if (position == 3) {
                            Intent intentStreet = new Intent(LimbsActivity.this, StreetActivity.class);
                            startActivity(intentStreet);
                        } else {
                            Intent intentLimbs = new Intent(LimbsActivity.this, LimbsInfoActivity.class);
                            intentLimbs.putExtra("id", position);
                            startActivity(intentLimbs);
                        }
                        break;
                }
            }
        });
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

    private void initHttp() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "selectOrganizeListByType")
                .addParams("data", new selectOrganizeListByType(Base.getMD5Str(), Base.getTimeSpan(), "1",
                        "0", "10", "4").toJson())
                .build().execute(new GsonCallBack<HospitalBean>() {
            @Override
            public void onSuccess(HospitalBean response) {
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    HospitalBean.DataBean.ListBean listBean = new HospitalBean.DataBean.ListBean();
                    name = response.getData().getList().get(i).getName();
                    content = response.getData().getList().get(i).getContent();
                    phone = response.getData().getList().get(i).getPhone();
                    scope = response.getData().getList().get(i).getScope();
                    subsidy = response.getData().getList().get(i).getSubsidy();
                    xAxis = response.getData().getList().get(i).getXAxis();
                    yAxis = response.getData().getList().get(i).getYAxis();
                    contactsName = response.getData().getList().get(i).getResponsibilityName();
                    listBean.setName(name);
                    listBean.setContent(content);
                    listBean.setPhone(phone);
                    listBean.setScope(scope);
                    listBean.setSubsidy(subsidy);
                    listBean.setXAxis(xAxis);
                    listBean.setYAxis(yAxis);
                    listBean.setResponsibilityName(contactsName);
                    listBeans.add(listBean);
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    static class selectOrganizeListByType {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String pageNo;
        private String pageCount;
        private String serviceprojectID;

        public selectOrganizeListByType(String appKey, String timeSpan, String mobileType, String pageNo, String pageCount, String serviceprojectID) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.pageNo = pageNo;
            this.pageCount = pageCount;
            this.serviceprojectID = serviceprojectID;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
