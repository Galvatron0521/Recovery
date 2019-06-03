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
import com.shenkangyun.recoveryproject.BeanFolder.HospitalBean;
import com.shenkangyun.recoveryproject.BeanFolder.VisionBean;
import com.shenkangyun.recoveryproject.HomePage.Adapter.VisionAdapter;
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

public class VisionActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.visionRecycler)
    RecyclerView visionRecycler;

    private VisionAdapter visionAdapter;
    private LinearLayoutManager layoutManager;
    private List<VisionBean> visionBeans;

    private List<HospitalBean.DataBean.ListBean> listBeans;
    private String name;
    private String content;
    private String phone;
    private String scope;
    private String subsidy;
    private String xAxis;
    private String yAxis;
    private String contactsName;

    private String[] names = new String[]{"泰安市爱尔眼科医院", "泰安市姜玉坤眼镜有限公司", "泰安市中医二院"};
    private String[] contents = new String[]{"泰安爱尔光明医院有限公司是由中国大规模连锁眼科医院集团——爱尔眼科医院集团与泰安市泰山光明医院合作新建的现代化眼科医院。",
            "姜玉坤眼镜连锁企业， 一九九二年姜玉坤先生创建。通过ISO9002国际质量认证，装配技术已经达到了100%的质量合格。",
            "泰安市中医二院位于山东省泰安市灵山大街285号，是一所集医疗、教学、科研、预防为一体的中医综合医院，是泰安市医保定点医院。"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);
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
        visionBeans = new ArrayList<>();
        visionAdapter = new VisionAdapter();
        layoutManager = new LinearLayoutManager(this);
        visionRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        visionRecycler.setLayoutManager(layoutManager);
        visionRecycler.setAdapter(visionAdapter);
    }

    private void initData() {
        List<String> unitName = Arrays.asList(names);
        List<String> unitContent = Arrays.asList(contents);
        for (int i = 0; i < 3; i++) {
            VisionBean visionBean = new VisionBean();
            visionBean.setUnitName(unitName.get(i));
            visionBean.setUnitCotent(unitContent.get(i));
            visionBeans.add(visionBean);
        }
        visionAdapter.setNewData(visionBeans);

        visionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.unitParent:
                        Intent intent = new Intent(VisionActivity.this, VisionInfoActivity.class);
                        intent.putExtra("id", position);
                        startActivity(intent);
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
