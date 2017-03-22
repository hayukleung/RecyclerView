# RecyclerView
Demo for Android RecyclerView

## DiffUtil
> 数据集变更

1. inserted
2. removed
3. moved
4. changed

## ItemTouchHelper
> 手势

1. 拖拽
2. 侧滑

### ItemTouchHelper.Callback

1. getMovementFlags(): 设置支持的拖拽和滑动的方向，此处我们支持的拖拽方向为上下，滑动方向为从左到右和从右到左，内部通过makeMovementFlags()设置
2. onMove(): 拖拽时回调
3. onSwiped(): 滑动时回调
4. onSelectedChanged(): 状态变化时回调，一共有三个状态，分别是ACTION_STATE_IDLE(空闲状态)，ACTION_STATE_SWIPE(滑动状态)，ACTION_STATE_DRAG(拖拽状态)。此方法中可以做一些状态变化时的处理，比如拖拽的时候修改背景色。
5. clearView(): 用户交互结束时回调。此方法可以做一些状态的清空，比如拖拽结束后还原背景色
6. isLongPressDragEnabled(): 是否支持长按拖拽，默认为true。如果不想支持长按拖拽，则重写并返回false

## NestedScrolling
> 嵌套滑动

> 为了支持嵌套滑动

1. 子View必须实现NestedScrollingChild接口
2. 父View必须实现NestedScrollingParent接口
3. RecyclerView实现了NestedScrollingChild接口
4. CoordinatorLayout实现了NestedScrollingParent接口

## RecyclerView的四大组成

1. Adapter：为Item提供数据
2. Layout Manager：Item的布局
3. Item Animator：添加、删除Item动画
4. Item Decoration：Item之间的Divider

### LayoutManager

> LinearLayoutManager

1. onLayoutChildren(): 对RecyclerView进行布局的入口方法
2. fill(): 负责填充RecyclerView
3. scrollVerticallyBy(): 根据手指的移动滑动一定距离，并调用fill()填充
4. canScrollVertically()或canScrollHorizontally(): 判断是否支持纵向滑动或横向滑动

### ItemAnimator

### ItemDecoration

1. onDraw(): 绘制分割线
2. getItemOffsets(): 设置分割线的宽、高

## RecyclerView回收机制

> 回收站分为Scrap Heap和Recycle Pool

1. mAttachedScrap: 缓存在屏幕上的ViewHolder
2. mCachedViews: 缓存屏幕外的ViewHolder，默认为2个。ListView对于屏幕外的缓存都会调用getView()
3. mViewCacheExtensions: 需要用户定制，默认不实现
4. mRecyclerPool: 缓存池，多个RecyclerView共用

