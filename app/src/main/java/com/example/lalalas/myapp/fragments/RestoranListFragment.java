package com.example.lalalas.myapp.fragments;

import android.content.Context;
import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyApiRequest;
import com.example.lalalas.myapp.helper.MyConfig;
import com.example.lalalas.myapp.helper.MyFragmentUtils;
import com.example.lalalas.myapp.helper.MyGson;
import com.example.lalalas.myapp.helper.MyRunnable;
import com.example.lalalas.myapp.helper.MyUrlConnection;
import com.example.lalalas.myapp.model.Restoran;
import com.example.lalalas.myapp.model.RestoranPregledVM;
import com.example.lalalas.myapp.model.Storage;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class RestoranListFragment extends Fragment  {
    private ListView lvRestorani;
    RestoranPregledVM.Row restoran=new RestoranPregledVM.Row();

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
     popuniPodatkeTask();
        return view;
    }


        private void popuniPodatkeTask() {
            MyApiRequest.get(getActivity(),"Restoran/Index", new MyRunnable<RestoranPregledVM>() {
                @Override
                public void run(RestoranPregledVM x) {
                    popuniPodatke(x);
                }
            });
        }



    private void popuniPodatke(final RestoranPregledVM podaci) {

        lvRestorani.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return podaci.rows.size();
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


                RestoranPregledVM.Row x=podaci.rows.get(i);
 img.setImageResource(Storage.getRestorani().get(i).getImageID());
  txtFirstLine.setText(x.naziv);
  txtSecindLine.setText(x.adresa);
                txtThirdLine.setText(x.grad);
                txtMeta.setText("0" + (i+1));





                return view;
            }
        });

        lvRestorani.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                restoran= podaci.rows.get(position);




                MyFragmentUtils.openAsReplace(getActivity(),R.id.mjestoFragment,RestoranDetaljiFragment.newInstance(restoran));
            }
        });

        }




    }



