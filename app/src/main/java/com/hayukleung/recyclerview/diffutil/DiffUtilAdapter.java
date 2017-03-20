package com.hayukleung.recyclerview.diffutil;

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
 * com.hayukleung.recyclerview.diffutil
 * DiffUtilAdapter.java
 *
 * by hayukleung
 * at 2017-03-19 22:16
 */

class DiffUtilAdapter extends RecyclerView.Adapter<DiffUtilAdapter.DiffUtilViewHolder> {

  private Context mContext;
  private List<DiffUtilItem> mDiffUtilItemList;

  DiffUtilAdapter(Context context, List<DiffUtilItem> diffUtilItemList) {
    mContext = context;
    mDiffUtilItemList = diffUtilItemList;
  }

  @Override public DiffUtilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(mContext);
    View view = layoutInflater.inflate(R.layout.item_diff_util, parent, false);
    return new DiffUtilViewHolder(view);
  }

  @Override public void onBindViewHolder(DiffUtilViewHolder holder, int position) {
    holder.name.setText(mDiffUtilItemList.get(position).getName());
  }

  @Override public int getItemCount() {
    return null == mDiffUtilItemList ? 0 : mDiffUtilItemList.size();
  }

  static class DiffUtilViewHolder extends RecyclerView.ViewHolder {

    private TextView name;

    DiffUtilViewHolder(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.name);
    }
  }
}
