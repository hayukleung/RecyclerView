package com.hayukleung.recyclerview.ItemTouchHelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hayukleung.recyclerview.R;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.ItemTouchHelper
 * ItemTouchHelperFragment.java
 *
 * by hayukleung
 * at 2017-03-20 18:08
 */

public class ItemTouchHelperFragment extends Fragment {

  private static final int COUNT = 10;

  private RecyclerView mRecyclerView;

  private List<ItemTouchData> mItemTouchDataList = new ArrayList<>(COUNT);

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.layout_item_touch_helper, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    ItemTouchAdapter itemTouchAdapter = new ItemTouchAdapter(getActivity(), mItemTouchDataList);
    mRecyclerView.setAdapter(itemTouchAdapter);

    ItemTouchHelperCallback itemTouchHelperCallback =
        new ItemTouchHelperCallback(itemTouchAdapter, mItemTouchDataList);
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
    itemTouchHelper.attachToRecyclerView(mRecyclerView);

    for (int i = 0; i < COUNT; i++) {
      ItemTouchData itemTouchData = new ItemTouchData();
      itemTouchData.setId(i);
      itemTouchData.setName(String.valueOf(itemTouchData.getId()));
      mItemTouchDataList.add(itemTouchData);
    }

    itemTouchAdapter.notifyDataSetChanged();
  }
}
