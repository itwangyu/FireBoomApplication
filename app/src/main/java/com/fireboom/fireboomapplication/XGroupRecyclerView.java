package com.fireboom.fireboomapplication;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYu on 2018/4/12.
 */
public abstract class XGroupRecyclerView<A, E, T extends IXGroupRecyclerViewItem<A, E>> extends RecyclerView {
    private Context mContext;
    private AdapterWrapper mXRecycleViewAdapter;
    private final int VALUE_INT_GROUP_NAME_TYPE = 0X11;
    private List<T> mDataList = new ArrayList<>();
    private SparseArray<Integer> mGroupInfo = new SparseArray<>();
    private SparseArray<E> mItemInfo = new SparseArray<>();
    private int mCount = 0;
    private SparseBooleanArray mBooleanMap = new SparseBooleanArray();

    public XGroupRecyclerView(Context context) {
        super(context);
        _init();
    }

    public XGroupRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        _init();
    }

    protected void _init() {
        mXRecycleViewAdapter = new AdapterWrapper(new XGroupRecycleViewAdapter());
        setAdapter(mXRecycleViewAdapter);
    }

    public void addHeader(View view) {
        mXRecycleViewAdapter.addHeader(view);
        mXRecycleViewAdapter.notifyDataSetChanged();
    }

    public void addFooter(View view) {
        mXRecycleViewAdapter.addFooter(view);
        mXRecycleViewAdapter.notifyDataSetChanged();
    }

    public void setItemList(List<T> listItem) {
        if (null == listItem)
            return;
        this.mDataList = listItem;
        orderData();
        mXRecycleViewAdapter.notifyDataSetChanged();
    }

    private void orderData() {
        mGroupInfo.clear();
        mItemInfo.clear();
        mCount = 0;
        for (int i = 0; i < this.mDataList.size(); i++) {
            mGroupInfo.put(mCount,i);
            mCount++;
            if (mBooleanMap.get(i, true)) {
                for (int j = 0; j < mDataList.get(i).getGroupList().size(); j++) {
                    mItemInfo.put(mCount, mDataList.get(i).getGroupList().get(j));
                    mCount++;
                }
            }

        }
    }

    public abstract ViewHolder adapterOnCreateGroupHeadViewHolder(ViewGroup parent, int viewType);

    public abstract void adapterOnBindGroupHeadViewHolder(ViewHolder holder, int position, A content);

    public abstract ViewHolder adapterOnCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void adapterOnBindViewHolder(ViewHolder holder, int position, E content);


    class XGroupRecycleViewAdapter extends Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VALUE_INT_GROUP_NAME_TYPE) {
                return adapterOnCreateGroupHeadViewHolder(parent, viewType);
            }
            return adapterOnCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            if (mGroupInfo.get(position) != null) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean status = mBooleanMap.get(mGroupInfo.get(position), true);
                        mBooleanMap.put(mGroupInfo.get(position), !status);
                        orderData();
                        mXRecycleViewAdapter.notifyDataSetChanged();
                    }
                });
                adapterOnBindGroupHeadViewHolder(holder, position,mDataList.get(mGroupInfo.get(position)).getGroupName());
            } else {
                adapterOnBindViewHolder(holder, position, mItemInfo.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mGroupInfo.size() + mItemInfo.size();
        }


        @Override
        public int getItemViewType(int position) {
            if (mGroupInfo.get(position) != null) {
                return VALUE_INT_GROUP_NAME_TYPE;
            }
            return 0;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int viewType = getItemViewType(position);
                        if (viewType == VALUE_INT_GROUP_NAME_TYPE) {
                            return ((GridLayoutManager) layoutManager).getSpanCount();
                        }
                        return 1;
                    }
                });
            }
        }

        @Override
        public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            int position = holder.getLayoutPosition();
            if (mGroupInfo.get(position) != null) {
                ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

                if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {

                    StaggeredGridLayoutManager.LayoutParams p =
                            (StaggeredGridLayoutManager.LayoutParams) lp;

                    p.setFullSpan(true);
                }
            }
        }
    }
}
