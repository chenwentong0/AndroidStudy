package com.topsports.androidstudy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.topsports.androidstudy.R;

import java.util.List;

/**
 * Date 2018/10/17
 * Time 10:53
 *
 * @author wentong.chen
 */
public class SimpleTextAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SimpleTextAdapter() {
        super(R.layout.item_simple_text, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);
    }
}
