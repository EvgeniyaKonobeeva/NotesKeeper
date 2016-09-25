package com.example.ekonobeeva.noteskeeper.Helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.ekonobeeva.noteskeeper.RecViewAdapter;

/**
 * Created by e.konobeeva on 20.09.2016.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final static String  TAG = "ItemTouchHelperCallback";
    int dragFrom = -1;
    int dragTo = -1;

    private IntItemTouchHelperAdapter adapter;

    public ItemTouchHelperCallback(IntItemTouchHelperAdapter adapter){
        this.adapter = adapter;
    }
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }



    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onRemoveItem();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {

        if(dragFrom == -1){
            dragFrom = fromPos;
        }
        Log.d(TAG, "onMoved ");
        adapter.onMoveItem(dragFrom, toPos, viewHolder, target);
        dragTo = toPos;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(viewHolder != null)
            ((RecViewAdapter.CardViewHolder)viewHolder).onItemSelected();
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        ((RecViewAdapter.CardViewHolder)viewHolder).onItemDropped();
        if(dragTo != -1 && dragFrom != -1 && dragFrom != dragTo) {
            adapter.onRealMove(dragFrom, dragTo);
            Log.d(TAG, "item pos " + viewHolder.getAdapterPosition());
            Log.d(TAG, "drag from " + dragFrom);
            Log.d(TAG, "drag to " + dragTo);
        }
        dragFrom = -1;
        dragTo = -1;

    }
}
