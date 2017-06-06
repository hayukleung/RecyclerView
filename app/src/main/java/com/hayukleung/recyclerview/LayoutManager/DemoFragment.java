package com.hayukleung.recyclerview.LayoutManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hayukleung.recyclerview.R;
import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.NestedScrolling
 * NestedScrollingFragment.java
 *
 * by hayukleung
 * at 2017-03-21 11:00
 */

public class DemoFragment extends Fragment {

  private static final int COUNT = 100;

  private RecyclerView mRecyclerView;

  private List<DemoItem> mDemoItemList = new ArrayList<>(COUNT);

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.layout_nested_scrolling, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // 主题设置为 Theme.AppCompat.Light.NoActionBar
    Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(new DemoLayoutManager());
    DemoAdapter demoAdapter = new DemoAdapter(getActivity(), mDemoItemList);
    demoAdapter.setHasStableIds(true);
    ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    mRecyclerView.setAdapter(demoAdapter);

    for (int i = 0; i < COUNT; i++) {
      DemoItem demoItem = new DemoItem();
      demoItem.setId(i);
      demoItem.setName(String.valueOf(demoItem.getId()));
      mDemoItemList.add(demoItem);
    }
    demoAdapter.notifyDataSetChanged();
  }
}
