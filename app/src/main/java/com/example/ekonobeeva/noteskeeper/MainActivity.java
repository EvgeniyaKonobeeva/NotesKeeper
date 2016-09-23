package com.example.ekonobeeva.noteskeeper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture_w3555_h690);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.picture_w384_h549);

        ArrayList<Object> texts = new ArrayList<>();
        texts.add("0");
        texts.add("1");
        texts.add("2");
        texts.add("3");
//        texts.add("4");
//        texts.add("5");
//        texts.add("6");
//        texts.add("7");


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecViewAdapter adapter = new RecViewAdapter(getApplicationContext(), this);
        adapter.setAdapterItemList(texts);

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        StaggeredGridLayoutManager layoutMan = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);



        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutMan);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onDragStarted(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
