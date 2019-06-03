package com.shenkangyun.recoveryproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.recoveryproject.BeanFolder.ChildrenBean;
import com.shenkangyun.recoveryproject.R;

/**
 * Created by Administrator on 2018/3/31.
 */

public class ChildrenAdapter extends BaseQuickAdapter<ChildrenBean, BaseViewHolder> {

    public ChildrenAdapter() {
        super(R.layout.item_children, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChildrenBean item) {
        helper.setText(R.id.tv_unitName,item.getUnitName());
        helper.setText(R.id.tv_unitContent,item.getUnitCotent());
        helper.addOnClickListener(R.id.unitParent);
    }
}
