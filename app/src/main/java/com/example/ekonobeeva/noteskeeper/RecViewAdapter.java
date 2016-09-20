package com.example.ekonobeeva.noteskeeper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ekonobeeva.noteskeeper.Helper.IDragListener;
import com.example.ekonobeeva.noteskeeper.Helper.IntItemTouchHelperAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by e.konobeeva on 20.09.2016.
 */
public class RecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IntItemTouchHelperAdapter {
    private final static String TAG = "RecViewAdapter";

    private ArrayList<String> cards;
    private Context context;
    private IDragListener dragListener;

    public RecViewAdapter(Context context, IDragListener dragListener){
        this.context = context;
        this.dragListener = dragListener;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final CardViewHolder cvh = (CardViewHolder)holder;
        setTextView(cvh.cardView, cvh.textView, cards.get(position));
        cvh.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dragListener.onDragStarted(cvh);
                return false;
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
        return new CardViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public void onMoveItem(int fromPos, int toPos) {
        String card = cards.remove(fromPos);
        cards.add(toPos, card);
        notifyItemMoved(fromPos, toPos);
    }

    @Override
    public void onRemoveItem() {

    }

    public void setAdapterItemList(ArrayList list){
        this.cards = list;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        public CardViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            textView = (TextView)itemView.findViewById(R.id.text_view);
        }


    }


    protected void setTextView(CardView cardView, TextView textView, String text){
//        TextPaint textPaint = textView.getPaint();
        textView.setText(text);
//        float avail = textView.getMeasuredWidth();
//        CharSequence t = TextUtils.ellipsize(text, textPaint, avail, TextUtils.TruncateAt.END);
//        textView.setText(t);
        if(text.length() < 3){
            textView.setTextSize(60);
        }else if(text.length() < 15){
            textView.setTextSize(40);
        }else {
            textView.setTextSize(20);
        }
        cardView.removeAllViews();
        cardView.addView(textView);
    }

}
