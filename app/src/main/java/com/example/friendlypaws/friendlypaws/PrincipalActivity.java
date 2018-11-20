package com.example.friendlypaws.friendlypaws;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout mainLayout;
    private ListView menuLateral;
    private  String usr, lvl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent objintent = getIntent();
        Bundle objbundle = objintent.getExtras();
        usr = (String)objbundle.getString("Usuario");
        lvl = (String)objbundle.getString("tipo");
        //usr="";
        //lvl="2";
        ActionBar aBar = getSupportActionBar();
        aBar.setHomeButtonEnabled(true);
        aBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        menuLateral = (ListView)findViewById(R.id.menuLateral);

        if(lvl.equals("3")){
            String[] opciones = {"Mascota","Buscar Vetenidaria","Reservacion de citas","Modificar Perfil","Cerrar Sesión"};
            ArrayAdapter<String> adp = new ArrayAdapter<String>(PrincipalActivity.this,
                    android.R.layout.simple_list_item_1,opciones);
            menuLateral.setAdapter(adp);
        }else if(lvl.equals("2")) {
            String[] opciones = {"Mascota","Solicitud","Reservacion de citas","Modificar Perfil","Cerrar Sesión"};
            ArrayAdapter<String> adp = new ArrayAdapter<String>(PrincipalActivity.this,
                    android.R.layout.simple_list_item_1, opciones);
            menuLateral.setAdapter(adp);
        }else if(lvl.equals("1")) {
            String[] opciones = {"Mascota","modificar","Reservacion de citas","Modificar Perfil","Cerrar Sesión"};
            ArrayAdapter<String> adp = new ArrayAdapter<String>(PrincipalActivity.this,
                    android.R.layout.simple_list_item_1, opciones);
            menuLateral.setAdapter(adp);
        }


        menuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String opcSeleccionado =(String) menuLateral.getAdapter().getItem(i);

                drawerLayout.closeDrawer(menuLateral);

                if(opcSeleccionado.equals("Cerrar Sesión")){
                    Toast.makeText(PrincipalActivity.this, "Sesión Cerrada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PrincipalActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(opcSeleccionado.equals("Mascota")){
                    Toast.makeText(PrincipalActivity.this, "Hacer Reservas", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PrincipalActivity.this,MascotasFragment.class);
                    Bundle objbundle = new Bundle();
                    objbundle.putString("Usuario",usr);
                    objbundle.putString("Nivel",lvl);
                    intent.putExtras(objbundle);
                    startActivity(intent);
                }
                if(opcSeleccionado.equals("Buscar Vetenidaria")){
                    Toast.makeText(PrincipalActivity.this, "Confirmar Reservas", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PrincipalActivity.this,veterinariasFragment.class);
                    Bundle objbundle = new Bundle();
                    objbundle.putString("Usuario",usr);
                    objbundle.putString("Nivel",lvl);
                    intent.putExtras(objbundle);
                    startActivity(intent);
                }
                if(opcSeleccionado.equals("Reservacion de citas")){
                    Toast.makeText(PrincipalActivity.this, "Programar Citas", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PrincipalActivity.this,ReservasFragment.class);
                    Bundle objbundle = new Bundle();
                    objbundle.putString("Usuario",usr);
                    objbundle.putString("Nivel",lvl);
                    intent.putExtras(objbundle);
                    startActivity(intent);
                }
                if(opcSeleccionado.equals("Modificar Perfil")){
                    Toast.makeText(PrincipalActivity.this, "Consultar Citas", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PrincipalActivity.this,PerfilFragment.class);
                    Bundle objbundle = new Bundle();
                    objbundle.putString("Usuario",usr);
                    objbundle.putString("Nivel",lvl);
                    intent.putExtras(objbundle);
                    startActivity(intent);
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id==android.R.id.home){
            if(drawerLayout.isDrawerOpen(menuLateral)){
                drawerLayout.closeDrawer(menuLateral);
            }else{
                drawerLayout.openDrawer(menuLateral);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
