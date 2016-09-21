package com.example.ekonobeeva.noteskeeper;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ekonobeeva.noteskeeper.Helper.IDragListener;
import com.example.ekonobeeva.noteskeeper.Helper.IntItemTouchHelperAdapter;

import java.util.ArrayList;

/**
 * Created by e.konobeeva on 20.09.2016.
 */
public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.CardViewHolder> implements IntItemTouchHelperAdapter {
    private final static String TAG = "RecViewAdapter";

    private ArrayList<String> cards;
    private Context context;
    private IDragListener dragListener;
    private RecyclerView recyclerView;
    private int recWidth;


    public RecViewAdapter(Context context, IDragListener dragListener, RecyclerView recyclerView){
        this.context = context;
        this.dragListener = dragListener;
        this.recyclerView = recyclerView;
    }


    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
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
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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


    protected void setTextView(CardView cardView, TextView textView, String text){
        if(text.length() < 3){
            textView.setTextSize(60);
        }else if(text.length() < 20){
            textView.setTextSize(40);
        }else {
            textView.setTextSize(20);
        }
        cardView.removeAllViews();
        cardView.addView(textView);

        TextPaint textPaint = textView.getPaint();
        CharSequence t = TextUtils.ellipsize(text, textPaint, computeActualLength(cardView, textView), TextUtils.TruncateAt.END);
        Log.d(TAG, "received string " + t.toString());
        textView.setText(t);


        cardView.removeAllViews();
        cardView.addView(textView);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        public CardViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            textView = (TextView)itemView.findViewById(R.id.text_view);
        }

        public void onItemSelected(){
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.cardBackgroundColor2));

        }
        public void onItemDropped(){
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.cardBackgroundColor));
        }


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(CardViewHolder holder) {
        Log.d(TAG, "onViewAttachedToWindow " + holder.cardView.getMeasuredWidth());
        recWidth = recyclerView.getMeasuredWidth()/2 - context.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin);
        super.onViewAttachedToWindow(holder);
    }

    protected int dpToPx(float dp){
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(Math.abs(dp)*density);
    }

    public float computeActualLength(CardView cardView, TextView textView){
        float lineH = textView.getLineHeight();
        int cardH = 700;
        int countLines = (cardH-dpToPx(2*2))/(int)lineH - 1;
        int width = 300 - dpToPx(2*2);
        float totalMaxTextWidth = width*countLines;
        Log.d(TAG, "lineWidth " + totalMaxTextWidth);
        return totalMaxTextWidth;

    }





}
