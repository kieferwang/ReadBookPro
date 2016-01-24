package com.krisitine.readbookpro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.krisitine.readbookpro.R;
import com.krisitine.readbookpro.ui.SearchAcitvity;
import com.krisitine.readbookpro.ui.ViewActivity;

/**
 * Created by Kristine on 2016/1/24.
 */
public class FragmentLeft extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_menu,container,false);

        Button btn_search= (Button) view.findViewById(R.id.btn_search);
        Button btn_view= (Button) view.findViewById(R.id.btn_view);

        btn_search.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), SearchAcitvity.class);
                startActivity(intent);

            }
        });

        btn_view.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), ViewActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }
}
