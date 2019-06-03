package com.shenkangyun.recoveryproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.recoveryproject.BeanFolder.SpiritBean;
import com.shenkangyun.recoveryproject.R;

/**
 * Created by Administrator on 2018/4/1.
 */

public class SpiritAdapter extends BaseQuickAdapter<SpiritBean,BaseViewHolder> {
    public SpiritAdapter() {
        super(R.layout.item_spirit, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpiritBean item) {
        helper.setText(R.id.tv_unitName, item.getUnitName());
        helper.setText(R.id.tv_unitContent, item.getUnitCotent());
        helper.addOnClickListener(R.id.unitParent);
    }
}
