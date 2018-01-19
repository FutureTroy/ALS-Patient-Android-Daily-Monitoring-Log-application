package com.example.frosty.als_involve_v3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by frosty on 10/8/17.
 */

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //test user is in main activity, so Context.testuser
        MainActivity Context = (MainActivity) getActivity();

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        //set all text views on the profile fragment
        TextView fname = (TextView) view.findViewById(R.id.profile_first_name_textView);
        fname.setText(Context.user.fname);

        TextView lname = (TextView) view.findViewById(R.id.profile_last_name_textView);
        lname.setText(Context.user.lname);

        TextView age = (TextView) view.findViewById(R.id.profile_age_text_view);
        age.setText(String.valueOf(Context.user.age));

        TextView height = (TextView) view.findViewById(R.id.profile_height_text_view);
        height.setText(String.valueOf(Context.user.height));

        TextView startDate = (TextView) view.findViewById(R.id.profile_start_date_text_view);
        startDate.setText(Context.user.startDate);

        return view;
    }

}
