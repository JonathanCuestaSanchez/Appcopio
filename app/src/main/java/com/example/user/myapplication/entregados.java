package com.example.user.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link entregados.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link entregados#newInstance} factory method to
 * create an instance of this fragment.
 */
public class entregados extends Fragment {
    EditText producto,cantidad,lugar;
    Button registrar;
    private SQLite sqlite;
    String A2,A5,A4;
    int A1,A3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public entregados() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment entregados.
     */
    // TODO: Rename and change types and number of parameters
    public static entregados newInstance(String param1, String param2) {
        entregados fragment = new entregados();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entregados, container, false);


        lugar = (EditText) view.findViewById(R.id.lugar);
        cantidad = (EditText) view.findViewById(R.id.cantidad);
        producto = (EditText) view.findViewById(R.id.idp);
        registrar = (Button) view.findViewById(R.id.registrar);
        sqlite = new SQLite(getContext());
        sqlite.abrir();



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!producto.getText().toString().equals("") && !cantidad.getText().toString().equals("") && !lugar.getText().toString().equals("")) {
                    if (sqlite.getCant(Integer.parseInt(producto.getText().toString())).getCount()==1){
                        Cursor cursor = sqlite.getCant(Integer.parseInt(producto.getText().toString()));
                        if (cursor.moveToFirst()) {
                            do {
                                A1 = cursor.getInt(0);
                                A2 = cursor.getString(1);
                                 A3 = cursor.getInt(2);
                                 A4 = cursor.getString(3);
                                 A5 = cursor.getString(4);

                            } while (cursor.moveToNext());
                        }
                        int t = Integer.parseInt(cantidad.getText().toString());
                        if (t <= A3){
                            int res=A3-t;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                            Date date = new Date();
                            String fechar = dateFormat.format(date);
                            String up=sqlite.addUpdateProd(A1,A2,res,A4,A5,fechar,lugar.getText().toString());
                            Toast.makeText(getContext(), "Almacen actualizado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "la cantidad ingresada es superior a la existente", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "el producto no se encuentra en el almacen", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(getContext(), "Error: no puede haber campos vacios", Toast.LENGTH_SHORT).show();
                }
            }

        });


        return view;
    }

}
