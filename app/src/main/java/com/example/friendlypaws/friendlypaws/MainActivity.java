package com.example.friendlypaws.friendlypaws;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tv_registrar;
    EditText txtusuario,txtclave;
    Button iniciar;
    String ruta;
    String usu, tipo,id_usu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_registrar = (TextView) findViewById(R.id.tv_registrar);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(MainActivity.this,Registro.class);
                MainActivity.this.startActivity(intentReg);
            }
        });

        txtusuario = (EditText)findViewById(R.id.txtusuario);
        txtclave = (EditText)findViewById(R.id.txtclave);
        iniciar = (Button)findViewById(R.id.btniniciar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"ssss",Toast.LENGTH_SHORT).show();
                Session();
            }
        });
    }

    private void Session()  {

        ruta="https://angel-bm-23.000webhostapp.com/ACTIVIDAD_LOGIN/logueo.php";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        Toast.makeText(getApplicationContext(),"entr2",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ruta, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                    //Creating JsonObject from response String
                    JSONObject jsonObject= new JSONObject(response.toString());
                    //extracting json array from response string
                    JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                    JSONObject jsonRowV = jsonArray.getJSONObject(0);

                    //get value from jsonRow

                    String estado =jsonRowV.getString("estado");
                    if (!estado.equals("0")){
                        Toast.makeText(getApplicationContext(),"entro",Toast.LENGTH_SHORT).show();
                        JSONObject jsonRow = jsonArray.getJSONObject(1);
                        id_usu =jsonRow.getString("IdUsuario");
                        usu =jsonRow.getString("NombreUsuario");
                        tipo =jsonRow.getString("tipo");
                        Toast.makeText(getApplicationContext(),"Bienvenido " + usu,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,PrincipalActivity.class);
                        Bundle objbundle = new Bundle();
                        objbundle.putString("Usuario",id_usu);
                        objbundle.putString("NombreUsuario",usu);
                        objbundle.putString("tipo",tipo);
                        intent.putExtras(objbundle);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"verificaque los datos",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"err",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error+"",Toast.LENGTH_SHORT).show();
            }
        }){
            /*-----PARAMETROS-----*/
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("user", txtusuario.getText().toString());
                params.put("pass", txtclave.getText().toString());


                return params;
            }
            /*-----CABECERA-----
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }*/
        };
        queue.add(stringRequest);
    }



}