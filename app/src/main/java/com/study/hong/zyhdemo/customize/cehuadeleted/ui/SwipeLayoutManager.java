package com.study.hong.zyhdemo.customize.cehuadeleted.ui;

/**
 * Created by 洪 on 2019/5/15.
 * 记录滑动打开还是关闭
 */

public class SwipeLayoutManager {
    private static SwipeLayoutManager fragment = new SwipeLayoutManager();

    public static SwipeLayoutManager getInstance() {
        return fragment;
    }

    //记录当前是否打开
    private SwipeLayout currentLayout;

    public void setSwipeLayout(SwipeLayout layout) {
        this.currentLayout = layout;
    }
    /**
     * 清空当前所记录的已经打开的layout
     */
    public void clearCurrentLayout(){
        currentLayout = null;
    }

    /**
     * 关闭当前已经打开的SwipeLayout
     */
    public void closeCurrentLayout(){
        if(currentLayout!=null){
            currentLayout.close();
        }
    }

    /**
     * 判断当前是否应该能够滑动，如果没有打开的，则可以滑动。
     * 如果有打开的，则判断打开的layout和当前按下的layout是否是同一个
     *总之，能够滑动就返回true，说明没有打开或者滑动自己 ，不能滑动就返回flase,说明有打开的，但是又点了其他
     * @return
     */
    public boolean isShouldSwipe(SwipeLayout swipeLayout){
        if(currentLayout==null){
            //说明当前木有打开的layout
            return true;
        }else {
            //说明有打开的layout
            return currentLayout==swipeLayout;
        }
    }
}
