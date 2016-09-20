package com.example.ekonobeeva.noteskeeper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by e.konobeeva on 20.09.2016.
 */
public class RecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IntItemTouchHelperAdapter{
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
        cvh.cardView.removeAllViews();
        cvh.cardView.addView(setTextToCardView(cards.get(position)));
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
        toPos = toPos > fromPos ? toPos - 1 : toPos;
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
        public CardViewHolder(View itemView) {
            super(itemView);
            cardView = setCardView();
            ((LinearLayout)itemView).addView(cardView);
        }


    }

    protected CardView setCardView(){
        CardView card = new CardView(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8,8,8,8);
        card.setLayoutParams(params);

        card.setRadius(9);

        card.setContentPadding(15, 15, 15, 15);

        card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));

        card.setMaxCardElevation(15);

        card.setCardElevation(9);
        return card;
    }

    protected TextView setTextView(String text){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8,8,8,8);
        TextView tv = new TextView(context);
        tv.setLayoutParams(params);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        tv.setTextColor(Color.RED);
        return tv;
    }

    protected TextView setTextToCardView(String text){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8,8,8,8);
        TextView tv = new TextView(context);
        tv.setLayoutParams(params);
        tv.setText(text);
        tv.setTextColor(Color.BLACK);
        if(text.length() < 5){
            tv.setTextSize(50);
        }else if(text.length() < 36){
            tv.setTextSize(30);
        }else{
            tv.setTextSize(20);
        }
        return tv;
    }

}
