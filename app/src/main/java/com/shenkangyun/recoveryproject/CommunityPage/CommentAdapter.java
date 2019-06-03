package com.shenkangyun.recoveryproject.CommunityPage;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shenkangyun.recoveryproject.BaseFolder.Base;
import com.shenkangyun.recoveryproject.BeanFolder.CommunityBean;
import com.shenkangyun.recoveryproject.R;

/**
 * Created by Administrator on 2018/3/30.
 */

public class CommentAdapter extends BaseQuickAdapter<CommunityBean.DataBean.ListBean.CommentListBean, BaseViewHolder> {
    public CommentAdapter() {
        super(R.layout.item_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean.DataBean.ListBean.CommentListBean item) {
        if (item.getCreateUser().equals("1")) {
            helper.setText(R.id.tv_userName, Base.getUserName());
        } else {
            helper.setText(R.id.tv_userName, item.getCreateUser());
        }
        helper.setText(R.id.tv_time, item.getCreateTime());
        helper.setText(R.id.tv_content, item.getContent());
    }
}
