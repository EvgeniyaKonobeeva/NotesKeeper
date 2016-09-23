package com.example.ekonobeeva.noteskeeper.Helper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by e.konobeeva on 20.09.2016.
 */
public interface IntItemTouchHelperAdapter {
    void onMoveItem(int fromPos, int toPos, RecyclerView.ViewHolder holder, RecyclerView.ViewHolder target);
    void onRemoveItem();
}
