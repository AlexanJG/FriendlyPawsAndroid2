package com.example.friendlypaws.friendlypaws;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.view.View.OnClickListener;


public class ReservasFragment extends Fragment {

    private TableLayout tablaReserva;
    private String[]header ={"Mascota","Veterinaria","Estado"};
    private ArrayList<String[]>rows= new ArrayList<>();
    private TablaDinamica tablaDinamica;
    public View view;
    public TextView textView;
    Context thiscontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inicialziación del Fragmento
        view = inflater.inflate(R.layout.fragment_reservas, container, false);
        thiscontext = container.getContext();
        //Buscar la Tabla
        tablaReserva=(TableLayout) view.findViewById (R.id.tablaReserva);

        //LLenar Tabla con la clase tablaDinamica (Creada con el estilo)
        tablaDinamica  = new TablaDinamica (tablaReserva, container.getContext().getApplicationContext());
        tablaDinamica.addHeader(header);
        tablaDinamica.addData(getReservas());
        tablaDinamica.colorHeader(Color.parseColor("#009688"));
        tablaDinamica.colorData(Color.parseColor("#B2DFDB"), Color.parseColor("#B2DFDB"));

        //Inicializar fragmento NuevaReserva
        final Fragment fragmentUNO = new NuevaReservaFragment();
        //Buscar Boton
        Button boton = (Button) view.findViewById(R.id.btnNuevaReserva);
        //Lo que pasa si presiono el boton
        boton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction FT = getActivity().getSupportFragmentManager().beginTransaction();
                FT.replace(R.id.contenedor, fragmentUNO);
                FT.addToBackStack(null);
                FT.commit();
            }
        });


        return view;
    }








    private  ArrayList<String[]> getReservas(){
        rows.add(new String[]{"Salem","VetCat","Concretado"});
        rows.add(new String[]{"Gigi","VetCat","Concretado"});
        rows.add(new String[]{"Salem","VetCat","Por Confirmar"});
        rows.add(new String[]{"Gigi","Oasis","Rechazado"});
        rows.add(new String[]{"Gigi","Oasis","Confirmado"});
        return rows;
    }





}
