package com.hayukleung.recyclerview.ItemTouchHelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import java.util.Collections;
import java.util.List;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.ItemTouchHelper
 * ItemTouchHelperCallback.java
 *
 * by hayukleung
 * at 2017-03-20 17:49
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

  private ItemTouchAdapter mItemTouchAdapter;
  private List<ItemTouchData> mItemTouchDataList;

  public ItemTouchHelperCallback(ItemTouchAdapter itemTouchAdapter,
      List<ItemTouchData> itemTouchDataList) {
    mItemTouchAdapter = itemTouchAdapter;
    mItemTouchDataList = itemTouchDataList;
  }

  /**
   * 设置支持的拖拽和滑动的方向，此处我们支持的拖拽方向为上下，滑动方向为从左到右和从右到左，内部通过makeMovementFlags()设置
   *
   * @param recyclerView
   * @param viewHolder
   * @return
   */
  @Override public int getMovementFlags(RecyclerView recyclerView,
      RecyclerView.ViewHolder viewHolder) {
    return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
        ItemTouchHelper.START | ItemTouchHelper.END);
  }

  /**
   * 拖拽时回调
   *
   * @param recyclerView
   * @param viewHolder
   * @param target
   * @return
   */
  @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      RecyclerView.ViewHolder target) {
    int from = viewHolder.getAdapterPosition();
    int to = target.getAdapterPosition();
    Collections.swap(mItemTouchDataList, from, to);
    mItemTouchAdapter.notifyItemMoved(from, to);
    return true;
  }

  /**
   * 是否支持长按拖拽，默认为true。如果不想支持长按拖拽，则重写并返回false
   *
   * @return
   */
  @Override public boolean isLongPressDragEnabled() {
    return super.isLongPressDragEnabled();
  }

  /**
   * 滑动时回调
   *
   * @param viewHolder
   * @param direction
   */
  @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    int pos = viewHolder.getAdapterPosition();
    mItemTouchDataList.remove(pos);
    mItemTouchAdapter.notifyItemRemoved(pos);
  }

  /**
   * 状态变化时回调，一共有三个状态，分别是
   * ACTION_STATE_IDLE(空闲状态)，
   * ACTION_STATE_SWIPE(滑动状态)，
   * ACTION_STATE_DRAG(拖拽状态)。
   * 此方法中可以做一些状态变化时的处理，比如拖拽的时候修改背景色
   *
   * @param viewHolder
   * @param actionState
   */
  @Override public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    super.onSelectedChanged(viewHolder, actionState);
    if (ItemTouchHelper.ACTION_STATE_IDLE != actionState) {
      // 设置拖拽侧滑背景色
      ItemTouchAdapter.ItemTouchViewHolder itemTouchViewHolder =
          (ItemTouchAdapter.ItemTouchViewHolder) viewHolder;
      itemTouchViewHolder.getBackground().setBackgroundColor(0xffbcbcbc);
    }
  }

  /**
   * 用户交互结束时回调。此方法可以做一些状态的清空，比如拖拽结束后还原背景色
   *
   * @param recyclerView
   * @param viewHolder
   */
  @Override public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);
    // 背景色还原
    ItemTouchAdapter.ItemTouchViewHolder itemTouchViewHolder =
        (ItemTouchAdapter.ItemTouchViewHolder) viewHolder;
    itemTouchViewHolder.getBackground().setBackgroundColor(0x00000000);
  }
}
