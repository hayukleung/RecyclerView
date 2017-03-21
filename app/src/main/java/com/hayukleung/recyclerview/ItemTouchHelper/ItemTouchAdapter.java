package com.hayukleung.recyclerview.ItemTouchHelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.hayukleung.recyclerview.R;
import java.util.List;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.ItemTouchHelper
 * ItemTouchAdapter.java
 *
 * by hayukleung
 * at 2017-03-20 17:59
 */

class ItemTouchAdapter extends RecyclerView.Adapter<ItemTouchAdapter.ItemTouchViewHolder> {

  private Context mContext;
  private List<ItemTouchData> mItemTouchDataList;

  ItemTouchAdapter(Context context, List<ItemTouchData> itemTouchDataList) {
    mContext = context;
    mItemTouchDataList = itemTouchDataList;
  }

  @Override public ItemTouchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ItemTouchViewHolder(
        LayoutInflater.from(mContext).inflate(R.layout.item_item_touch_helper, parent, false));
  }

  @Override public void onBindViewHolder(ItemTouchViewHolder holder, int position) {
    holder.name.setText(mItemTouchDataList.get(position).getName());
  }

  @Override public int getItemCount() {
    return null == mItemTouchDataList ? 0 : mItemTouchDataList.size();
  }

  static class ItemTouchViewHolder extends RecyclerView.ViewHolder {

    private FrameLayout background;
    private TextView name;

    ItemTouchViewHolder(View itemView) {
      super(itemView);
      background = (FrameLayout) itemView.findViewById(R.id.background);
      name = (TextView) itemView.findViewById(R.id.name);
    }

    public FrameLayout getBackground() {
      return background;
    }

    public TextView getName() {
      return name;
    }
  }
}
