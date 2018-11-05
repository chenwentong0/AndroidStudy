package com.topsports.androidstudy.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.topsports.androidstudy.R;
import com.topsports.androidstudy.adapter.SimpleTextAdapter;
import com.topsports.androidstudy.base.BaseActivity;

/**
 * Date 2018/10/17
 * Time 14:22
 *
 * @author wentong.chen
 */
public class BottomSheetBvActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_bv);
        useBottomBehavior(false);
        showBottomDialog(true);
    }

    private void showBottomDialog(boolean use) {
//        View btn = findViewById(R.id.btn_sheet_bottom_dialog);
//        btn.setVisibility(use ? View.VISIBLE : View.GONE);
//        BottomSheetDialog dialog = new BottomSheetDialog(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,null);
//
//        handleList(view);
//
//        dialog.setContentView(view);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
    }

    private void handleList(View contentView){
//        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
//        recyclerView.setite
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        SimpleTextAdapter adapter = new SimpleTextAdapter();
//        recyclerView.setAdapter(adapter);
//        adapter.setData();
//        adapter.notifyDataSetChanged();
    }

    private void useBottomBehavior(boolean use) {
        View shareView = findViewById(R.id.share_view);
        shareView.setVisibility(use ? View.VISIBLE : View.GONE);
        View btn = findViewById(R.id.btn_show_bottom_sheet);
        btn.setVisibility(use ? View.VISIBLE : View.GONE);
        //获取BottomSheetBehavior
        final BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(shareView);

        //设置折叠时的高度
        //sheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        //监听BottomSheetBehavior 状态的变化
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Logger.d(TAG, "onStateChanged", "newState = " + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Logger.d(TAG, "onSlide", "slideOffset = " + slideOffset);
            }
        });
        //下滑的时候是否可以隐藏
        sheetBehavior.setHideable(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED &&
                        sheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });
    }
}
