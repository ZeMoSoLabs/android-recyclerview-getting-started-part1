package com.zemosolabs.atif.recyclerview.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zemosolabs.atif.recyclerview.R;
import com.zemosolabs.atif.recyclerview.utils.RecyclerViewClass;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomRecyclerViewHolder>{

    List<RecyclerViewClass> mItems;
    Context mContext;
    boolean onLongPressReceived = false;
    UpdateMainClass updateMainClass;

    /**
     * Contructor to initialize context and list items.
     * @param context Context of the Activity on which RecyclerView is initialised
     * @param items List of POJO object that contains the data to update the rows
     */
    public CustomAdapter(Context context, List<RecyclerViewClass> items){
        mContext = context;
        mItems = items;
        //Check whether the Activity implements UpdateMainClass Interface or not
        if(context instanceof UpdateMainClass){
            updateMainClass = (UpdateMainClass)context;
        }
    }

    /**
     * <p>This method updates the long press status variable when called from the Activity</p>
     * <p>Helpful to prevent any unwanted changes to status variable</p>
     * @param status tell whether whether long press is clicked or not
     */
    public void setOnLongPressStatus(boolean status){
        onLongPressReceived = status;
        notifyDataSetChanged();
    }

    /**
     * Provides the long press status to the Activity
     * @return longPressReceived status
     */
    public boolean getLongPressStatus(){
        return onLongPressReceived;
    }

    @Override
    public CustomRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.custom_recyclerview_layout, parent, false);
        //set the margin if any, will be discussed in next blog
        return new CustomRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CustomRecyclerViewHolder holder, int position) {
        holder.mAvatarView.setImageDrawable(mItems.get(position).getmImage_url());
        holder.mMsg1.setText(mItems.get(position).getMessage1());
        holder.mMsg2.setText(mItems.get(position).getMessage2());
        /**
         * <p>Enable the row delete and select layout and
         * Colors the background based on check box status if onLongPressReceived variable is set to true</p>
         * <p>Otherwise renders a regular white background list with visible checkboxes that are selected.</p>
         */
        if(onLongPressReceived) {
            holder.checkboxHolder.setVisibility(View.VISIBLE);
            holder.mCheckBox.setChecked(mItems.get(position).getmIsChecked());
            holder.mDeleteRow.setVisibility(View.VISIBLE);
            if(mItems.get(holder.getAdapterPosition()).getmIsChecked()){
                holder.cardView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_selected));
            }else {
                holder.cardView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_long_pressed));
            }
        }
            //Checking whether a particular view is clicked or not
        else{
            holder.cardView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_unselected));
            holder.mDeleteRow.setVisibility(View.INVISIBLE);
            if (mItems.get(position).getmIsChecked()) {
                holder.checkboxHolder.setVisibility(View.VISIBLE);
                holder.mCheckBox.setChecked(true);
            } else {
                holder.checkboxHolder.setVisibility(View.GONE);
                holder.mCheckBox.setChecked(false);
            }
        }
        //Calls the interface method in Activity to respond to CheckBox changes
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateMainClass.updateListBackground(holder.getAdapterPosition(), b);
            }
        });
        /**
         * <p>Responds to long press made on any row</p>
         * <p>Checks the item on which long press is made
         * sets the onLongPressReceived status to true and notify the adapter to refresh the list.</p>
         */
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onLongPressReceived = true;
                mItems.get(holder.getAdapterPosition()).setmIsChecked(true);
                notifyDataSetChanged();
                return true;
            }
        });
        //Calls the interface in Activity to remove the item from the List.
        holder.mDeleteRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMainClass.updateItemList(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView mMsg1, mMsg2;
        private ImageView mAvatarView;
        private CheckBox mCheckBox;
        private LinearLayout checkboxHolder;
        private ImageView mDeleteRow;
        private CardView cardView;


        /**
         * Initializes all the views of a ViewHolder
         * @param itemView parent view in which all the List Item views are present
         */
        public CustomRecyclerViewHolder(View itemView) {
            super(itemView);
            mMsg1 = (TextView)itemView.findViewById(R.id.text_view1);
            mMsg2 = (TextView)itemView.findViewById(R.id.text_view2);
            mAvatarView = (ImageView)itemView.findViewById(R.id.avatar_holder);
            mCheckBox = (CheckBox)itemView.findViewById(R.id.checkbox);
            checkboxHolder = (LinearLayout)itemView.findViewById(R.id.checkbox_holder);
            mDeleteRow = (ImageView)itemView.findViewById(R.id.delete_row);
            cardView = (CardView)itemView.findViewById(R.id.card_holder);
        }
    }
    public interface UpdateMainClass{
        void updateItemList(int position);
        void updateListBackground(int position, boolean isChecked);
    }
}
