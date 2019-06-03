package com.shenkangyun.recoveryproject.HomePage.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.recoveryproject.BeanFolder.SpeechBean;
import com.shenkangyun.recoveryproject.R;

/**
 * Created by Administrator on 2018/4/1.
 */

public class SpeechAdapter extends BaseQuickAdapter<SpeechBean, BaseViewHolder> {
    public SpeechAdapter() {
        super(R.layout.item_speech, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpeechBean item) {
        helper.setText(R.id.tv_unitName, item.getUnitName());
        helper.setText(R.id.tv_unitContent, item.getUnitCotent());
        helper.addOnClickListener(R.id.unitParent);
    }
}
