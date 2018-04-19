package com.fireboom.fireboomapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYu on 2018/4/12.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestRecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<TestItemData> dataList = new ArrayList<>();
        for (int i=0;i<30;i++) {
            TestItemData itemData = new TestItemData();
            itemData.setGroupName("组名"+i);
            ArrayList<String> list = new ArrayList<>();
            for (int j=0;j<=i;j++) {
                list.add("这是内容"+i+"-"+j);
            }
            itemData.setGroupList(list);
            dataList.add(itemData);
        }
        recyclerView.setItemList(dataList);
        TextView tv1 = new TextView(this);
        tv1.setText("我来组成头部");
        TextView tv2 = new TextView(this);
        tv2.setText("我来组成尾巴");
        recyclerView.addHeader(tv1);
//        recyclerView.addFooter(tv2);
        recyclerView.setSpreadEnable(true);
    }
}
