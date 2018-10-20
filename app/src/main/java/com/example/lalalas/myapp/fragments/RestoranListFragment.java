package com.example.lalalas.myapp.fragments;

import android.app.Dialog;
import android.content.Context;
import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.app.LoginActivity;
import com.example.lalalas.myapp.app.MainActivity;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.model.Restoran;
import com.example.lalalas.myapp.model.Storage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class RestoranListFragment extends Fragment  {
    private ListView lvRestorani;
    Restoran restoran;

    public static RestoranListFragment newInstance() {
        
     //   Bundle args = new Bundle();
        
       // RestoranListFragment fragment = new RestoranListFragment();
        //fragment.setArguments(args);
        //return fragment;

        return  new RestoranListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.fragment_restoran_list,container,false);
   lvRestorani=(ListView) view.findViewById(R.id.lvRestorani);
popuniPodatke();
        return view;
    }

    private void popuniPodatke() {
        final List<Restoran> podaci=Storage.getRestorani();

        lvRestorani.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup parent) {
                if (view==null)
                {
                    LayoutInflater inflater=(LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.restoran_stavka,parent,false);
                }
              ImageView img=view.findViewById(R.id.img);
                TextView txtFirstLine=view.findViewById(R.id.txtFirstLine);
                TextView txtSecindLine=view.findViewById(R.id.txtSecondLine);
                TextView txtThirdLine=view.findViewById(R.id.txtThirdLine);
                TextView txtMeta=view.findViewById(R.id.txtMeta);


                Restoran x=podaci.get(i);
  img.setImageResource(x.getImageID());
  txtFirstLine.setText(x.getTitle());
  txtSecindLine.setText(x.getAddress());
                txtThirdLine.setText(x.getCity());
                txtMeta.setText("0" + i);





                return view;
            }
        });

        lvRestorani.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  restoran = (Restoran) lvRestorani.getItemAtPosition(position);




                MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RestoranDetaljiFragment.newInstance(restoran));
            }
        });

        }




    }



