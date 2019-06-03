package com.shenkangyun.recoveryproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.recoveryproject.BeanFolder.StreetBean;
import com.shenkangyun.recoveryproject.R;

/**
 * Created by Administrator on 2018/4/8.
 */

public class StreetAdapter extends BaseQuickAdapter<StreetBean.DataBean.ListBean, BaseViewHolder> {
    public StreetAdapter() {
        super(R.layout.item_street, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, StreetBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_unitName, item.getName());
    }
}
