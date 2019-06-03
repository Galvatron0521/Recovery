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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shenkangyun.recoveryproject.BaseFolder.Base;
import com.shenkangyun.recoveryproject.BeanFolder.ChildrenBean;
import com.shenkangyun.recoveryproject.BeanFolder.HospitalBean;
import com.shenkangyun.recoveryproject.HomePage.Adapter.ChildrenAdapter;
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

public class ChildrenActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.childrenRecycler)
    RecyclerView childrenRecycler;

    private ChildrenAdapter childrenAdapter;
    private LinearLayoutManager layoutManager;
    private List<ChildrenBean> childrenBeans;

    private List<HospitalBean.DataBean.ListBean> listBeans;

    private String name;
    private String content;
    private String phone;
    private String scope;
    private String subsidy;
    private String xAxis;
    private String yAxis;
    private String contactsName;

    private String[] childNames = new String[]{"泰山区人民医院儿康中心", "泰山区小天使康复中心", "泰山区智康能力儿童活动中心", "泰山区东岳特教中心"};
    private String[] childContents = new String[]{"泰山区人民医院儿康中心儿童康复中心成立2006年2009年儿康中心成为省残疾儿童智障康复技术指导中心定点机构。",
            "小天使康复中心与中国康复中心、中国康复技术委员会、残疾人康复协会等国内一流康复科研基地建立友好关系。",
            "泰安市泰山区智康能力儿童活动中心创办于二00八年一月，机构性质民办非营利慈善机构。几年来智康人秉承“立智高远，康达人生”的办学宗旨。",
            "泰安市泰山区东岳特教中心成立于2010年6月，由泰安市泰山区残联主管，2010年12月6日在泰山区民政局正式注册的民办非企单位。"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children);
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
        childrenBeans = new ArrayList<>();
        childrenAdapter = new ChildrenAdapter();
        layoutManager = new LinearLayoutManager(this);
        childrenRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        childrenRecycler.setLayoutManager(layoutManager);
        childrenRecycler.setAdapter(childrenAdapter);
    }

    private void initData() {
        List<String> unitName = Arrays.asList(childNames);
        List<String> unitContent = Arrays.asList(childContents);
        for (int i = 0; i < 4; i++) {
            ChildrenBean childrenBean = new ChildrenBean();
            childrenBean.setUnitName(unitName.get(i));
            childrenBean.setUnitCotent(unitContent.get(i));
            childrenBeans.add(childrenBean);
        }
        childrenAdapter.setNewData(childrenBeans);

        childrenAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.unitParent:
                        Intent intent = new Intent(ChildrenActivity.this, ChildrenInfoActivity.class);
                        intent.putExtra("id", position);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initHttp() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "selectOrganizeListByType")
                .addParams("data", new selectOrganizeListByType(Base.getMD5Str(), Base.getTimeSpan(), "1",
                        "0", "10", "9").toJson())
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
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
