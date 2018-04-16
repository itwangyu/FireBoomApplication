package com.fireboom.fireboomapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYu on 2018/4/12.
 */
public class TestRecyclerView extends XGroupRecyclerView<String, String, TestItemData> {

    public TestRecyclerView(Context context) {
        super(context);
    }

    public TestRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public ViewHolder adapterOnCreateGroupHeadViewHolder(ViewGroup parent, int viewType) {
        return new NameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void adapterOnBindGroupHeadViewHolder(ViewHolder holder, int position, String content) {
        NameViewHolder viewHolder = (NameViewHolder) holder;
        viewHolder.tv.setText(content);
    }

    @Override
    public ViewHolder adapterOnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void adapterOnBindViewHolder(ViewHolder holder, int position, String content) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tv.setText(content);
    }
}

class TestItemData implements IXGroupRecyclerViewItem<String, String> {
    private String mGroupName = "";
    private List<String> mList = new ArrayList<>();
    private boolean mIsShowList=true;

    @Override
    public void setGroupName(String name) {
        this.mGroupName = name;
    }

    @Override
    public String getGroupName() {
        return mGroupName;
    }

    @Override
    public List<String> getGroupList() {
        return mList;
    }

    @Override
    public void setGroupList(List<String> list) {
        this.mList = list;
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }
}

class NameViewHolder extends RecyclerView.ViewHolder {
    TextView tv;
    public NameViewHolder(View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
        tv.setBackgroundColor(Color.GRAY);
    }
}
