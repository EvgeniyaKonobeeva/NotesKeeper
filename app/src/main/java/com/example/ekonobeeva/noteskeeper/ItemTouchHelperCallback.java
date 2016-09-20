package com.example.ekonobeeva.noteskeeper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by e.konobeeva on 20.09.2016.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

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
        adapter.onMoveItem(fromPos, toPos);
        //super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
    }
}
