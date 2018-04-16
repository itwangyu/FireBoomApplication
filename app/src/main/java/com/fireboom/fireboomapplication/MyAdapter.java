//package com.fireboom.fireboomapplication;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.util.SparseBooleanArray;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class MyAdapter extends ClassAdapter<MyAdapter.ClassHolder, MyAdapter.StudentHolder> {
//
//    private Context context;
//    private List<ClassBean> mContent;
//
//    //用于记录当前班级是隐藏还是显示
//    private SparseBooleanArray mBooleanMap;
//
//    public MyAdapter(Context context, List mContent) {
//        this.context = context;
//        this.mContent = mContent;
//
//        mBooleanMap = new SparseBooleanArray();
//    }
//
//    @Override
//    public int getHeadersCount() {
//        return mContent.size();
//    }
//
//    @Override
//    public int getContentCountForHeader(int headerPosition) {
//
//        int count = mContent.get(headerPosition).classStudents.size();
//
//        if (!mBooleanMap.get(headerPosition)) {
//            count = 0;
//        }
//        return count;
//    }
//
//    /**
//     * 创建头布局header的viewholder
//     * @param parent
//     * @param viewType
//     * @return
//     */
//    @Override
//    public MyAdapter.ClassHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
//        return new ClassHolder(View.inflate(context, R.layout.item, null));
//    }
//
//    /**
//     * 创建内容布局item的viewholder
//     * @param parent
//     * @param viewType
//     * @return
//     */
//    @Override
//    public MyAdapter.StudentHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
//        return new StudentHolder(View.inflate(context, R.layout.item, null));
//    }
//
//    @Override
//    public void onBindHeaderViewHolder(MyAdapter.ClassHolder holder, int position) {
//        holder.tvClassName.setOnClickListener(null);
//        holder.tvClassName.setText(mContent.get(position).className);
//
//        holder.tvClassName.setTag(position);
//        holder.tvClassName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = (int) view.getTag();
//
//                boolean isOpen = mBooleanMap.get(position);
//
//                mBooleanMap.put(position, !isOpen);
//                notifyDataSetChanged();
//            }
//        });
//    }
//
//    @Override
//    public void onBindContentViewHolder(StudentHolder holder, int HeaderPosition, int ContentPositionForHeader) {
//        holder.tvInfo.setText(mContent.get(HeaderPosition).classStudents.get(ContentPositionForHeader));
//    }
//
//
//
//    class ClassHolder extends RecyclerView.ViewHolder{
//
//        public TextView tvClassName;
//
//        public ClassHolder(View itemView) {
//            super(itemView);
//            tvClassName = itemView.findViewById(R.id.tvInfo);
//        }
//    }
//
//    class StudentHolder extends RecyclerView.ViewHolder{
//
//        public TextView tvInfo;
//
//        public StudentHolder(View itemView) {
//            super(itemView);
//            tvInfo = itemView.findViewById(R.id.tvInfo);
//        }
//    }
//
//}