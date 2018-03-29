package io.zeroxp.pullcontacts;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by parthbhavsar on 2018-03-29.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.e("onMove", "");
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.e("onSelectedChanged", "");

        if (viewHolder != null) {
            final View foregroundView = ((ContactsRecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {

        final View foregroundView = ((ContactsRecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;

        ContactsRecyclerViewAdapter.ViewHolder contactViewHolder = ((ContactsRecyclerViewAdapter.ViewHolder) viewHolder);


        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

            //swipe Right
            if(dX > 0){
                contactViewHolder.viewBackgroundSwipeLeft.setVisibility(View.VISIBLE);
                contactViewHolder.viewBackgroundSwipeRight.setVisibility(View.GONE);
            }
            //swipe left
            else {
                contactViewHolder.viewBackgroundSwipeRight.setVisibility(View.VISIBLE);
                contactViewHolder.viewBackgroundSwipeLeft.setVisibility(View.GONE);
            }
        }


        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((ContactsRecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        final View foregroundView = ((ContactsRecyclerViewAdapter.ViewHolder) viewHolder).viewForeground;

        ContactsRecyclerViewAdapter.ViewHolder contactViewHolder = ((ContactsRecyclerViewAdapter.ViewHolder) viewHolder);


        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

            //swipe Right
            if(dX > 0){
                contactViewHolder.viewBackgroundSwipeLeft.setVisibility(View.VISIBLE);
                contactViewHolder.viewBackgroundSwipeRight.setVisibility(View.GONE);
            }
            //swipe left
            else {
                contactViewHolder.viewBackgroundSwipeRight.setVisibility(View.VISIBLE);
                contactViewHolder.viewBackgroundSwipeLeft.setVisibility(View.GONE);
            }
        }



        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        Log.e("convertToAbsDir", "");
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}