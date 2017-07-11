package com.fame.plumbum.chataround.emergency.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.fame.plumbum.chataround.R;
import com.fame.plumbum.chataround.activity.MainActivity;
import com.fame.plumbum.chataround.emergency.model.data.EmergencyContactsFeed;
import com.fame.plumbum.chataround.helper.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Completable;

/**
 * Created by ramya on 2/7/17.
 */

public class EmergencyContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<EmergencyContactsFeed> emergencyContactsFeedList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private SharedPrefs sharedPrefs;
    private EmergencyFragment emergencyFragment;
    private Context mContext;


    public EmergencyContactsAdapter(Context context,List<EmergencyContactsFeed> feedList) {
        this.mContext=context;
        this.sharedPrefs = new SharedPrefs(context);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.contacts_feed_item, parent, false);
        return new EmergencyContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final EmergencyContactsFeed emergencyContactsFeed = emergencyContactsFeedList.get(position);
        EmergencyContactsViewHolder emergencyContactsViewHolder = (EmergencyContactsViewHolder) holder;
        String contactName = emergencyContactsFeed.getContactName();
        final String contactNumber = emergencyContactsFeed.getContactNumber();
       // String contactImage = emergencyContactsFeed.getContactImage();
        if (contactName != null && !contactName.isEmpty()) {
            emergencyContactsViewHolder.contactName.setText(emergencyContactsFeed.getContactName());
        }
        if (contactNumber != null && !contactNumber.isEmpty()) {
            emergencyContactsViewHolder.contactNumber.setText(emergencyContactsFeed.getContactNumber());
        }
        /*if (contactImage != null) {
            emergencyContactsViewHolder.contactsImage.setImageURI(Uri.parse(emergencyContactsFeedList.get(position).getContactImage()));
        }
*/
        emergencyContactsViewHolder.contactCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emergencyContactsFeed.setChecked(emergencyContactsFeed.isChecked());
                emergencyContactsFeedList.get(position).setChecked(emergencyContactsFeed.isChecked());
                /*EmergencyContactsFeed emergencyContactsFeed= emergencyContactsFeedList.get(position);
                emergencyFragment.requestContactsUpdate(sharedPrefs.getUserId(),emergencyContactsFeed,isChecked,position);*/

            }
        });
        emergencyContactsViewHolder.contactCheckBox.setChecked(emergencyContactsFeed.isChecked());

    }

    public void setEmergencyContactsFeedList(List<EmergencyContactsFeed> list) {
        this.emergencyContactsFeedList = list;
    }


    @Override
    public int getItemCount() {
        return emergencyContactsFeedList.size();
    }

    public class EmergencyContactsViewHolder extends RecyclerView.ViewHolder {
        /*@BindView(R.id.contacts_image)
        ImageView contactsImage;*/
        @BindView(R.id.contacts_name)
        TextView contactName;
        @BindView(R.id.contacts_number)
        TextView contactNumber;
        @BindView(R.id.select_contact_check_box)
        CheckBox contactCheckBox;

        public EmergencyContactsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
