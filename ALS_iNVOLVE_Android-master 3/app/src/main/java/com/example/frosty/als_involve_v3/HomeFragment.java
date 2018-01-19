package com.example.frosty.als_involve_v3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by frosty on 10/8/17.
 */

public class HomeFragment extends Fragment {

    String armResultsNum;
    String tapResultNum;

    //inflate the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    
}
