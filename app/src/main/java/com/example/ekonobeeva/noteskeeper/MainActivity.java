package com.example.ekonobeeva.noteskeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.ekonobeeva.noteskeeper.Helper.IDragListener;
import com.example.ekonobeeva.noteskeeper.Helper.ItemTouchHelperCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IDragListener {
    private final static int SPAN_COUNT_PORTRAIT = 2;
    private ItemTouchHelper itemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> texts = new ArrayList<>();
        texts.add("bla 1");
        texts.add("blablabla bla bla ble");
        texts.add("blablabla bla bla ble    3   blu blo lbgflflf blablabla bla bla ble");
        texts.add("blablabla bla bla ble    4   blu blo lbgflflf blablabla bla bla ble");
        texts.add("blablabla bla bla ble    5   blu blo lbgflflf");
        texts.add("blablabla bla bla ble    6   blu blo lbgflflf blablabla bla bla ble    4   blu blo lbgflflf blablabla bla bla ble blablabla bla bla ble    4   blu blo lbgflflf blablabla bla bla ble");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecViewAdapter adapter = new RecViewAdapter(getApplicationContext(), this, recyclerView );
        adapter.setAdapterItemList(texts);

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        StaggeredGridLayoutManager layoutMan = new StaggeredGridLayoutManager(SPAN_COUNT_PORTRAIT, StaggeredGridLayoutManager.VERTICAL);



        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutMan);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onDragStarted(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
