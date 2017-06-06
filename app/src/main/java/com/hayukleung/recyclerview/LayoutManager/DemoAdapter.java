package com.hayukleung.recyclerview.LayoutManager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hayukleung.recyclerview.R;
import java.util.List;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.NestedScrolling
 * NestedScrollingAdapter.java
 *
 * by hayukleung
 * at 2017-03-21 11:06
 */

class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

  private Context mContext;
  private List<DemoItem> mDemoItemList;

  DemoAdapter(Context context, List<DemoItem> demoItemList) {
    mContext = context;
    mDemoItemList = demoItemList;
  }

  @Override public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DemoViewHolder(
        LayoutInflater.from(mContext).inflate(R.layout.item_demo, parent, false));
  }

  @Override public void onBindViewHolder(DemoViewHolder holder, int position) {
    holder.name.setText(mDemoItemList.get(position).getName());
  }

  @Override public int getItemCount() {
    return null == mDemoItemList ? 0 : mDemoItemList.size();
  }

  static class DemoViewHolder extends RecyclerView.ViewHolder {

    TextView name;

    DemoViewHolder(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.name);
    }
  }
}
