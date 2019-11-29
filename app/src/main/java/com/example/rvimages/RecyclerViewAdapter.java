package com.example.rvimages;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageViews  = new ArrayList<>();
    private  ArrayList<String> mPlayerDescriptions = new ArrayList<>();
    private Context mContext;
    public RecyclerViewAdapter mViewAdapter;


    public RecyclerViewAdapter(ArrayList<String> imageViews, ArrayList<String> playerDescriptions, Context context) {
        mImageViews = imageViews;
        mPlayerDescriptions = playerDescriptions;
        mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImageViews.get(position))
                .into(holder.mImageView);

        holder.mPlayerDescription.setText(mPlayerDescriptions.get(position));

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mPlayerDescriptions.get(position));
                Toast.makeText(mContext, mPlayerDescriptions.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPlayerDescriptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView mImageView;
        TextView mPlayerDescription;
        RelativeLayout mRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView =itemView.findViewById(R.id.circular_image);
            mPlayerDescription = itemView.findViewById(R.id.player_description);
            mRelativeLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
