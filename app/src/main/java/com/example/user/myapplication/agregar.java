package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class agregar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private DatePickerDialog.OnDateSetListener fecha;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String fechas = "";
    Button plus;
    TextView vencimiento;
    EditText medida, cantidad, producto;
    private SQLite sqlite;
    private int mes, dia, year, id;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public agregar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment agregar.
     */
    // TODO: Rename and change types and number of parameters
    public static agregar newInstance(String param1, String param2) {
        agregar fragment = new agregar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar, container, false);

        vencimiento = (TextView) view.findViewById(R.id.vencimiento);

        cantidad = (EditText) view.findViewById(R.id.cantidad);
        producto = (EditText) view.findViewById(R.id.idp);
        plus = (Button) view.findViewById(R.id.plus);
        sqlite = new SQLite(getContext());
        sqlite.abrir();

        vencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar C = Calendar.getInstance();
                dia = C.get(Calendar.DAY_OF_MONTH);
                year = C.get(Calendar.YEAR);
                mes = C.get(Calendar.MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        vencimiento.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, dia, mes, year);
                datePickerDialog.show();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!producto.getText().toString().equals("") && !cantidad.getText().toString().equals("")) {



                    try {
                        int t = Integer.parseInt(cantidad.getText().toString());
                        if (!vencimiento.getText().toString().equals("Fecha de vencimiento")) {
                            fechas = vencimiento.getText().toString();
                        }

                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                            Date date = new Date();
                            String res="No a sido retirado";
                            String fechar = dateFormat.format(date);
                            if (sqlite.addRegistroProductos(producto.getText().toString(), t, fechas,fechar,res,"")) {
                                //recuperar id del ultimo registtro y pasa como parmetro

                                Toast.makeText(getContext(), "Registro añadido", Toast.LENGTH_SHORT).show();
                                producto.setText("");
                                cantidad.setText("");

                                vencimiento.setText("Fecha de vencimiento");

                            } else {
                                Toast.makeText(getContext(), "Error: compruebe que los datos sean correctos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Error: compruebe que los datos sean correctos", Toast.LENGTH_SHORT).show();
                        }

                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Error: compruebe que los datos sean correctos", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getContext(), "Error: no puede haber campos vacios", Toast.LENGTH_SHORT).show();
                }
            }

        });


        return view;
    }


}
