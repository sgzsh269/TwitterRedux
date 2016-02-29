package com.sagarnileshshah.twitterclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.twitterclient.R;
import com.sagarnileshshah.twitterclient.models.friend.User;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sshah on 2/28/16.
 */
public class FriendsAdapter extends ArrayAdapter<User> {

    Context mContext;

    public FriendsAdapter(Context context, ArrayList<User> friends) {
        super(context, 0, friends);
        mContext = context;
    }

    public static class ViewHolder {

        @Bind(R.id.ivUserProfileImage)
        ImageView ivUserProfileImage;

        @Bind(R.id.tvUserName)
        TextView tvUserName;

        @Bind(R.id.tvScreenName)
        TextView tvScreenName;

        @Bind(R.id.tvDescription)
        TextView tvDescription;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_friend, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivUserProfileImage.setImageResource(0);
        Glide.with(mContext).load(user.getProfileImageUrl()).error(R.drawable.photo_placeholder).placeholder(R.drawable.photo_placeholder).dontAnimate().into(viewHolder.ivUserProfileImage);

        viewHolder.tvUserName.setText(user.getName());
        viewHolder.tvScreenName.setText("@" + user.getScreenName());

        if(user.getDescription() != null){
            viewHolder.tvDescription.setVisibility(View.VISIBLE);
            viewHolder.tvDescription.setText(user.getDescription());
        } else {
            viewHolder.tvDescription.setVisibility(View.GONE);
        }

        return convertView;
    }
}
