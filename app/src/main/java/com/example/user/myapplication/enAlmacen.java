package com.example.user.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link enAlmacen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link enAlmacen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class enAlmacen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public enAlmacen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment enAlmacen.
     */
    // TODO: Rename and change types and number of parameters
    public static enAlmacen newInstance(String param1, String param2) {
        enAlmacen fragment = new enAlmacen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_en_almacen, container, false);

        ListView l=(ListView)view.findViewById(R.id.lista) ;
        SQLite sqlite;

        //base de datos
        try{
            sqlite = new SQLite(getContext());
            sqlite.abrir();
            Cursor cursor = sqlite.getRegistrosProductos();
            ArrayList<String> reg = sqlite.getPROD(cursor);

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,reg);
            l.setAdapter(adaptador);
        }catch (Exception e){
            System.out.println("lolol");
        }





        return view;
    }
}
