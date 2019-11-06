package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link agreNece.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link agreNece#newInstance} factory method to
 * create an instance of this fragment.
 */
public class agreNece extends Fragment {

    EditText articulot,lugar;
    Button publicar;
    private DatabaseReference articulos;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public agreNece() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment agreNece.
     */
    // TODO: Rename and change types and number of parameters
    public static agreNece newInstance(String param1, String param2) {
        agreNece fragment = new agreNece();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agre_nece, container, false);
        articulot= (EditText) view.findViewById(R.id.articulo);
        lugar= (EditText) view.findViewById(R.id.lugar);
        publicar= (Button) view.findViewById(R.id.publicar);
        articulos= FirebaseDatabase.getInstance().getReference("articulo");

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrar();
            }
        });

       return view;
    }
    public void registrar(){
        String art=articulot.getText().toString();
        String lug=lugar.getText().toString();
        if (!TextUtils.isEmpty(art)&& !TextUtils.isEmpty(lug)){
            String id= articulos.push().getKey();
            articulo nuevo= new articulo(id,art,lug);
            articulos.child("Articulos").child(id).setValue(nuevo);
        }else {
            Toast.makeText(getContext(), "Error: no se a rellenado los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }
}



