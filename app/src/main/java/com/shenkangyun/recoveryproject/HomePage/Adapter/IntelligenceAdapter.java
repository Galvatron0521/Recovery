package com.shenkangyun.recoveryproject.HomePage.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.recoveryproject.BeanFolder.IntelligenceBean;
import com.shenkangyun.recoveryproject.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/1.
 */

public class IntelligenceAdapter extends BaseQuickAdapter<IntelligenceBean, BaseViewHolder> {
    public IntelligenceAdapter() {
        super(R.layout.item_intelligence, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntelligenceBean item) {
        helper.setText(R.id.tv_unitName, item.getUnitName());
        helper.setText(R.id.tv_unitContent, item.getUnitCotent());
        helper.addOnClickListener(R.id.unitParent);
    }
}
