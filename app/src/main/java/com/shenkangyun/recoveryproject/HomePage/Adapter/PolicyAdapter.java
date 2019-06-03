package com.shenkangyun.recoveryproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.recoveryproject.BeanFolder.PolicyBean;
import com.shenkangyun.recoveryproject.R;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PolicyAdapter extends BaseQuickAdapter<PolicyBean.DataBean.ListBean, BaseViewHolder> {

    public PolicyAdapter() {
        super(R.layout.item_policy, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, PolicyBean.DataBean.ListBean item) {
        helper.setText(R.id.policy_title, item.getTitle());
    }
}
