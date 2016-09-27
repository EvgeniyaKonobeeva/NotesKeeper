package com.example.ekonobeeva.noteskeeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ekonobeeva.noteskeeper.Helper.IDragListener;
import com.example.ekonobeeva.noteskeeper.Helper.IntItemTouchHelperAdapter;

import java.util.ArrayList;

/**
 * Created by e.konobeeva on 20.09.2016.
 */
public class RecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IntItemTouchHelperAdapter {
    private final static String TAG = "RecViewAdapter";
//    private final static int EMPTY_VIEW = 0;
    private final static int FILLED_VIEW = 1;

    private ArrayList<Object> cards;
    private Context context;
    private IDragListener dragListener;
    private int prevPos = -1;

    public RecViewAdapter(Context context, IDragListener dragListener){
        this.context = context;
        this.dragListener = dragListener;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder");
        if(holder instanceof CardViewHolder) {
            setCardContent((CardViewHolder) holder, cards.get(position));
            ((CardViewHolder) holder).cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    dragListener.onDragStarted(holder);
                    return false;
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(TAG, "onCreateViewHolder");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
        return new CardViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public void onMoveItem(int fromPos, int toPos, RecyclerView.ViewHolder holder, RecyclerView.ViewHolder target) {

        notifyItemMoved(fromPos, toPos);
        if(toPos < fromPos){
            Log.d(TAG, "up");
            notifyItemMoved(toPos+1, fromPos);
        }else{
            Log.d(TAG, "down");
            notifyItemMoved(toPos-1, fromPos);
        }
    }

    @Override
    public void onRealMove(int fromPos, int toPos) {

    }

    @Override
    public void onRemoveItem() {

    }

    @Override
    public int getItemViewType(int position) {

//        if(cards.get(position) == null){
//            return EMPTY_VIEW;
//        }
        return FILLED_VIEW;
    }

    public void setAdapterItemList(ArrayList list){
        this.cards = new ArrayList<>();
        this.cards.addAll(list);
//        for(int i = 0; i < 15; i++ ){
//            cards.add(new Object());
//        }
    }


    protected void setTextContent(CardView cardView, Object obj){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv = setTextView();
        tv.setText((String)obj);
        cardView.removeAllViews();
        cardView.addView(tv, params);

    }

    protected void setImageContent(CardView cardView, Object obj){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView imv = setImageView();
        imv.setImageBitmap(resizeBitmap((Bitmap) obj));
        cardView.removeAllViews();
        cardView.addView(imv, params);

    }

    protected void setCardContent(CardViewHolder holder, Object obj){
        if(obj instanceof String){
            setTextContent(holder.cardView, obj);
        }else if(obj instanceof Bitmap){
            setImageContent(holder.cardView, obj);
        }
    }

    protected Bitmap resizeBitmap(Bitmap bitmap){
        int maxCardHeight = context.getResources().getInteger(R.integer.imageViewMaxHeight);
        if(bitmap.getHeight()> maxCardHeight){
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, true);
            return resizedBitmap;
        }
        return bitmap;
    }

    protected TextView setTextView(){
        TextView tv = new TextView(context);
        tv.setTypeface(Typeface.MONOSPACE);
        tv.setTextColor(context.getResources().getColor(R.color.textColor));
        int padding = context.getResources().getInteger(R.integer.textViewPadding);
        tv.setPadding(padding, padding, padding, padding);
        return tv;
    }
    protected ImageView setImageView(){
        ImageView imv = new ImageView(context);
        imv.setMaxHeight(context.getResources().getInteger(R.integer.imageViewMaxHeight));
        imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imv;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public CardViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
        }

        public void onItemSelected(){
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.cardBackgroundColor2));
            Log.d(TAG, "holder drag position " + this.getAdapterPosition());

        }
        public void onItemDropped(){
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.cardBackgroundColor));
        }


    }




}
