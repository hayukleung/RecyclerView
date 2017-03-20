package com.hayukleung.recyclerview.diffutil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hayukleung.recyclerview.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * DiffUtil Demo
 *
 * RecyclerView
 * com.hayukleung.recyclerview.diffutil
 * DiffUtilFragment.java
 *
 * by hayukleung
 * at 2017-03-19 21:53
 */

public class DiffUtilFragment extends Fragment {
  private static final int COUNT = 10;

  private RecyclerView mRecyclerView;

  private List<DiffUtilItem> mDiffUtilItemList = new ArrayList<>(COUNT);
  private List<DiffUtilItem> mDiffUtilItemListNew = new ArrayList<>(COUNT);

  private UIHandler mUIHandler;
  private final Lock mLock = new Lock();

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mUIHandler = new UIHandler(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.layout_diff_util, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    mRecyclerView.setAdapter(new DiffUtilAdapter(getActivity(), mDiffUtilItemList));

    for (int i = 0; i < COUNT; i++) {
      DiffUtilItem diffUtilItem = new DiffUtilItem();
      diffUtilItem.setId(i);
      diffUtilItem.setName(String.valueOf(i));
      mDiffUtilItemList.add(diffUtilItem);
    }
    mRecyclerView.getAdapter().notifyDataSetChanged();

    view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        add();
      }
    });

    view.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        remove();
      }
    });
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    new Thread(new Runnable() {
      @Override public void run() {
        while (true) {

          add();

          try {
            Thread.sleep(new Random().nextInt(5) * 1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();

    new Thread(new Runnable() {
      @Override public void run() {
        while (true) {

          remove();

          try {
            Thread.sleep(new Random().nextInt(5) * 1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }

  /**
   * 通知 UI 线程刷新
   *
   * @param diffResult
   * @param type 1 - add 2 - remove
   */
  private void notifyRefresh(DiffUtil.DiffResult diffResult, int type) {
    Message message = new Message();
    message.what = 0;
    message.arg1 = type;
    message.obj = diffResult;
    mUIHandler.sendMessage(message);
  }

  private void refresh(DiffUtil.DiffResult diffResult, final int type) {

    diffResult.dispatchUpdatesTo(mRecyclerView.getAdapter());
    mUIHandler.postDelayed(new Runnable() {
      @Override public void run() {
        switch (type) {
          case 1: {
            // add
            mRecyclerView.smoothScrollToPosition(mDiffUtilItemList.size() - 1);
            break;
          }
          case 2: {
            // remove
            mRecyclerView.smoothScrollToPosition(0);
            break;
          }
        }
        mLock.setLock(false);
      }
    }, 200);
  }

  /**
   * 生产
   */
  private void add() {

    if (mLock.isLock()) {
      return;
    }

    mLock.setLock(true);

    mDiffUtilItemListNew.clear();
    mDiffUtilItemListNew.addAll(mDiffUtilItemList);
    DiffUtilItem diffUtilItem = new DiffUtilItem();
    diffUtilItem.setId(0 == mDiffUtilItemListNew.size() ? 0
        : (mDiffUtilItemListNew.get(mDiffUtilItemListNew.size() - 1).getId() + 1));
    diffUtilItem.setName(String.valueOf(diffUtilItem.getId()));
    mDiffUtilItemListNew.add(diffUtilItem);
    DiffUtil.DiffResult diffResult =
        DiffUtil.calculateDiff(new DiffUtilCallback(mDiffUtilItemList, mDiffUtilItemListNew));

    mDiffUtilItemList.clear();
    mDiffUtilItemList.addAll(mDiffUtilItemListNew);

    notifyRefresh(diffResult, 1);
  }

  /**
   * 消费
   *
   * @return
   */
  private boolean remove() {

    if (mLock.isLock()) {
      return false;
    }

    mLock.setLock(true);

    if (0 == mDiffUtilItemList.size()) {
      mLock.setLock(false);
      return false;
    }
    mDiffUtilItemListNew.clear();
    mDiffUtilItemListNew.addAll(mDiffUtilItemList);
    mDiffUtilItemListNew.remove(0);
    DiffUtil.DiffResult diffResult =
        DiffUtil.calculateDiff(new DiffUtilCallback(mDiffUtilItemList, mDiffUtilItemListNew));

    mDiffUtilItemList.clear();
    mDiffUtilItemList.addAll(mDiffUtilItemListNew);

    notifyRefresh(diffResult, 2);
    return true;
  }

  static class UIHandler extends Handler {
    private WeakReference<DiffUtilFragment> diffUtilFragmentWeakReference;

    UIHandler(DiffUtilFragment diffUtilFragment) {
      this.diffUtilFragmentWeakReference = new WeakReference<>(diffUtilFragment);
    }

    @Override public void handleMessage(Message msg) {
      DiffUtilFragment diffUtilFragment = this.diffUtilFragmentWeakReference.get();
      if (null == diffUtilFragment) {
        return;
      }
      switch (msg.what) {
        case 0: {
          diffUtilFragment.refresh((DiffUtil.DiffResult) msg.obj, msg.arg1);
          break;
        }
      }
    }
  }

  static class Lock {

    private volatile boolean lock = false;

    synchronized boolean isLock() {
      return lock;
    }

    synchronized void setLock(boolean lock) {
      this.lock = lock;
    }
  }
}
