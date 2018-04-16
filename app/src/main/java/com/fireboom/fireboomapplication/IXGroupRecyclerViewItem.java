package com.fireboom.fireboomapplication;

import java.util.List;

/**
 * Created by WangYu on 2018/4/12.
 */
public interface IXGroupRecyclerViewItem<A,T>  {
    void setGroupName(String name);

    A getGroupName();

    List<T> getGroupList();

    void setGroupList(List<T> list);
}
