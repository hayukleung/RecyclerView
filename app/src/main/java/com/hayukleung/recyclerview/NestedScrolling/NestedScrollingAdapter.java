package com.hayukleung.recyclerview.NestedScrolling;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

class NestedScrollingAdapter
    extends RecyclerView.Adapter<NestedScrollingAdapter.NestedScrollingViewHolder> {

  private Context mContext;
  private List<NestedScrollingItem> mNestedScrollingItemList;

  NestedScrollingAdapter(Context context, List<NestedScrollingItem> nestedScrollingItemList) {
    mContext = context;
    mNestedScrollingItemList = nestedScrollingItemList;
  }

  @Override public NestedScrollingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new NestedScrollingViewHolder(
        LayoutInflater.from(mContext).inflate(R.layout.item_nested_scrolling, parent, false));
  }

  @Override public void onBindViewHolder(NestedScrollingViewHolder holder, int position) {
    switch (position % 4) {
      case 0: {
        holder.image.setImageResource(R.drawable.p000);
        break;
      }
      case 1: {
        holder.image.setImageResource(R.drawable.p001);
        break;
      }
      case 2: {
        holder.image.setImageResource(R.drawable.p002);
        break;
      }
      case 3: {
        holder.image.setImageResource(R.drawable.p003);
        break;
      }
    }
  }

  @Override public int getItemCount() {
    return null == mNestedScrollingItemList ? 0 : mNestedScrollingItemList.size();
  }

  static class NestedScrollingViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private FrameLayout background;

    NestedScrollingViewHolder(View itemView) {
      super(itemView);
      image = (ImageView) itemView.findViewById(R.id.image);
      background = (FrameLayout) itemView.findViewById(R.id.background);
    }
  }
}
