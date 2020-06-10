package com.mustapha.mohamed.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mustapha.mohamed.HomeActivity;
import com.mustapha.mohamed.R;
import com.mustapha.mohamed.model.RestoData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



public class RestoAdapter extends RecyclerView.Adapter<FoodViewHolder>{

    private Context myContext;
    private List<RestoData> myFoodList;

    public RestoAdapter(Context myContext, List<RestoData> myFoodList) {
        this.myContext = myContext;
        this.myFoodList = myFoodList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resto_row,viewGroup,false);

        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {
        foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
        foodViewHolder.mTitle.setText(myFoodList.get(i).getItemName());
        foodViewHolder.mDescription.setText(myFoodList.get(i).getItemDescription());

        foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(myContext, HomeActivity.class);
              //  intent.putExtra("image",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemImage());
              //  intent.putExtra("name",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemName());
                myContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}
class FoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription,mPrice;
 CardView mCardView;

    public FoodViewHolder(View itemView) {
        super(itemView);


        imageView=itemView.findViewById(R.id.ivImage);
        mTitle=itemView.findViewById(R.id.tvTitle);
        mDescription=itemView.findViewById(R.id.tvDescription);
      mCardView=itemView.findViewById(R.id.mycardview);

    }
}

