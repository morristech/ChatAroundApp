package com.fame.plumbum.chataround.pollution.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fame.plumbum.chataround.R;
import com.fame.plumbum.chataround.pollution.model.air_model.AirPollutionIndividualValue;
import com.github.pavlospt.CircleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by meghal on 23/2/17.
 */

public class PollutuionAqiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AirPollutionIndividualValue> valueList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    PollutuionAqiAdapter(Context context, List<AirPollutionIndividualValue> valueList) {
        this.valueList = valueList;
        layoutInflater = LayoutInflater.from(context);
        this.context=context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.aqi_item, parent, false);
        return new IndividualAqiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        IndividualAqiViewHolder individualAqiViewHolder = (IndividualAqiViewHolder) holder;
        AirPollutionIndividualValue value = valueList.get(position);


        // Setting Color
        individualAqiViewHolder.title.setBackgroundColor(value.getColor());
        individualAqiViewHolder.circleView.setFillColor(value.getColor());
        individualAqiViewHolder.circleView.setBackgroundColor(value.getColor());

        // Setting Up Values
        if(value.getV()!=-9999) {
            individualAqiViewHolder.circleView.setTitleText(String.valueOf(value.getV()));
            individualAqiViewHolder.title.setText(value.getName());
        }else{
            individualAqiViewHolder.circleView.setTitleText("NA");
            individualAqiViewHolder.title.setText(value.getName());

        }



    }

    @Override
    public int getItemCount() {


        Toast.makeText(context, String.valueOf(valueList.size()), Toast.LENGTH_SHORT).show();
        return valueList.size();
    }

    public class IndividualAqiViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.circleView)
        CircleView circleView;
        @BindView(R.id.title)
        TextView title;

        public IndividualAqiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}