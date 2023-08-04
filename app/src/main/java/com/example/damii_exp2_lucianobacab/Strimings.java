package com.example.damii_exp2_lucianobacab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Strimings extends AppCompatActivity {
    private EditText etRegistroU, etEdadRU;
    private Spinner spnTipoPerfil;
    private ListView LV;
    private TextView tvTipoUsu, tvUsuarioR, tvCategoria1, tvCategoria2, tvCategoria3;
    private ArrayList<String> Usuarios;
    private ArrayList<String> TipoUsuarios;
    private ArrayAdapter<String> adapter;
    private ImageButton ibntnPeliI1, ibntnPeliI2, ibntnPeliA1, ibntnPeliA2, ibntnPeliAD1, ibntnPeliAD2, ibnMedia;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strimings);

        Usuarios = new ArrayList<>();
        TipoUsuarios = new ArrayList<>();

        tvTipoUsu = (TextView) findViewById(R.id.tvTipoUsu);
        tvUsuarioR = (TextView) findViewById(R.id.tvUsuarioR);

        //Recuperar la informacion del Perfil Seleccionado
        SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
        Set<String> dataSet = sharedPreferences.getStringSet("dataSet", new HashSet<>());
        Usuarios.addAll(dataSet);

        SharedPreferences sharedPreferences1 = getSharedPreferences("MiSharedPreferences1", Context.MODE_PRIVATE);
        Set<String> dataSet1 = sharedPreferences1.getStringSet("dataSet1", new HashSet<>());
        TipoUsuarios.addAll(dataSet1);

        SharedPreferences Usa = getSharedPreferences("Usuario", Context.MODE_PRIVATE);

        String UsuRec = Usa.getString("UsuarioRegis", "No Registrado");
        String EdadUsr = Usa.getString("EdadUsu", "0");
        String TiUsua = Usa.getString("TipoUsuarioR", "Tipo de Usuario Null");
        String[] UsuRe = UsuRec.split("   ");
        String Usua = UsuRe[0]; // Recuperar solo el usuario
        tvUsuarioR.setText(Usua);
        tvTipoUsu.setText(TiUsua);

        int Edad = Integer.parseInt(EdadUsr);

        tvCategoria1 = (TextView) findViewById(R.id.tvCategoria1);
        tvCategoria2 = (TextView) findViewById(R.id.tvCategoria2);
        tvCategoria3 = (TextView) findViewById(R.id.tvCategoria3);

        ibntnPeliI1 = (ImageButton) findViewById(R.id.ibntnPeliI1);
        ibntnPeliI2 = (ImageButton) findViewById(R.id.ibntnPeliI2);
        ibntnPeliA1 = (ImageButton) findViewById(R.id.ibntnPeliA1);
        ibntnPeliA2 = (ImageButton) findViewById(R.id.ibntnPeliA2);
        ibntnPeliAD1 = (ImageButton) findViewById(R.id.ibntnPeliAD1);
        ibntnPeliAD2 = (ImageButton) findViewById(R.id.ibntnPeliAD2);

        //Resultados de peliculas visibles de acuerdo al tipo de perfil
        if (Edad > 0 && Edad<12){
            tvCategoria1.setVisibility(View.VISIBLE);
            ibntnPeliI1.setVisibility(View.VISIBLE);
            ibntnPeliI2.setVisibility(View.VISIBLE);
            tvCategoria2.setVisibility(View.GONE);
            ibntnPeliA1.setVisibility(View.GONE);
            ibntnPeliA2.setVisibility(View.GONE);
            tvCategoria3.setVisibility(View.GONE);
            ibntnPeliAD1.setVisibility(View.GONE);
            ibntnPeliAD2.setVisibility(View.GONE);

        } else if (Edad >= 12 && Edad <= 18){
            tvCategoria1.setVisibility(View.VISIBLE);
            ibntnPeliI1.setVisibility(View.VISIBLE);
            ibntnPeliI2.setVisibility(View.VISIBLE);
            tvCategoria2.setVisibility(View.VISIBLE);
            ibntnPeliA1.setVisibility(View.VISIBLE);
            ibntnPeliA2.setVisibility(View.VISIBLE);
            tvCategoria3.setVisibility(View.GONE);
            ibntnPeliAD1.setVisibility(View.GONE);
            ibntnPeliAD2.setVisibility(View.GONE);

        } else if (Edad > 18) {
            tvCategoria1.setVisibility(View.VISIBLE);
            ibntnPeliI1.setVisibility(View.VISIBLE);
            ibntnPeliI2.setVisibility(View.VISIBLE);
            tvCategoria2.setVisibility(View.VISIBLE);
            ibntnPeliA1.setVisibility(View.VISIBLE);
            ibntnPeliA2.setVisibility(View.VISIBLE);
            tvCategoria3.setVisibility(View.VISIBLE);
            ibntnPeliAD1.setVisibility(View.VISIBLE);
            ibntnPeliAD2.setVisibility(View.VISIBLE);

        }

        //Botones para Peliculas
        //Boton Pelicula Infantil 1
        ibntnPeliI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo
                Frozen();
            }
        });

        //Boton Pelicula Infantil 2
        ibntnPeliI2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo
                Enredados();
            }
        });


        //Boton Pelicula Adolecente 1
        ibntnPeliA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo
                Ready();
            }
        });

        //Boton Pelicula Adolecente 2
        ibntnPeliA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo
                Pixeles();
            }
        });


        //Boton Pelicula Adulto 1
        ibntnPeliAD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo
                Anabelle();
            }
        });

        //Boton Pelicula Adulto 2
        ibntnPeliAD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Metodo
                Conjuro();
            }
        });




    }

    //Metodo para mostrar y ocultar el menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Metodo de seleccion de opciones
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.MAgregar) {
            //Dialogo de Registro
            AlertDialog.Builder Registro = new AlertDialog.Builder(Strimings.this);
            Registro.setTitle("Registrar Usuario");
            View selectRegistro = getLayoutInflater().inflate(R.layout.registro, null);
            Registro.setView(selectRegistro);
            Registro.setCancelable(false);
            etRegistroU = (EditText) selectRegistro.findViewById(R.id.etRegistroU);
            etEdadRU = (EditText) selectRegistro.findViewById(R.id.etEdadRU);
            spnTipoPerfil = (Spinner) selectRegistro.findViewById(R.id.spnTipoPerfil);

            ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(Strimings.this, R.array.TipoPerfil, android.R.layout.simple_spinner_item);
            spnTipoPerfil.setAdapter(adapter1);

            Registro.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //Metodo de registro
                    String ItemSelect = spnTipoPerfil.getSelectedItem().toString();
                    int SelectItem = spnTipoPerfil.getSelectedItemPosition();

                    if (SelectItem == 1){
                        String NombreU1 = etRegistroU.getText().toString();
                        String etEdadRU1 = etEdadRU.getText().toString();
                        String PerfilSelect1 = spnTipoPerfil.getSelectedItem().toString();
                        String RegistroUs1 = NombreU1 + "   " + etEdadRU1 + "   " + PerfilSelect1;

                        if (!NombreU1.isEmpty() && !etEdadRU1.isEmpty()) {
                            Usuarios.add(RegistroUs1);
                            etRegistroU.getText().clear();
                            SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Set<String> listaGuardada = new HashSet<>(Usuarios);
                            editor.putStringSet("dataSet", listaGuardada);
                            editor.apply();
                            editor.commit();

                            TipoUsuarios.add(PerfilSelect1);
                            SharedPreferences sharedPreferences1 = getSharedPreferences("MiSharedPreferences1", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                            Set<String> listaGuardada1 = new HashSet<>(TipoUsuarios);
                            editor1.putStringSet("dataSet1", listaGuardada1);
                            editor1.apply();
                            editor1.commit();

                        } else {
                            Toast.makeText(getApplicationContext(), "No se puede guardar un registro vacio ", Toast.LENGTH_SHORT).show();
                        }

                    } else if (SelectItem == 2){
                        String NombreU1 = etRegistroU.getText().toString();
                        String etEdadRU1 = etEdadRU.getText().toString();
                        String PerfilSelect1 = spnTipoPerfil.getSelectedItem().toString();
                        String RegistroUs1 = NombreU1 + "   " + etEdadRU1 + "   " + PerfilSelect1;

                        if (!NombreU1.isEmpty() && !etEdadRU1.isEmpty()) {
                            Usuarios.add(RegistroUs1);
                            etRegistroU.getText().clear();
                            SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Set<String> listaGuardada = new HashSet<>(Usuarios);
                            editor.putStringSet("dataSet", listaGuardada);
                            editor.apply();
                            editor.commit();

                            TipoUsuarios.add(PerfilSelect1);
                            SharedPreferences sharedPreferences1 = getSharedPreferences("MiSharedPreferences1", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                            Set<String> listaGuardada1 = new HashSet<>(TipoUsuarios);
                            editor1.putStringSet("dataSet1", listaGuardada1);
                            editor1.apply();
                            editor1.commit();

                        } else {
                            Toast.makeText(getApplicationContext(), "No se puede guardar un registro vacio ", Toast.LENGTH_SHORT).show();
                        }

                    } else if (SelectItem == 3){
                        String NombreU1 = etRegistroU.getText().toString();
                        String etEdadRU1 = etEdadRU.getText().toString();
                        String PerfilSelect1 = spnTipoPerfil.getSelectedItem().toString();
                        String RegistroUs1 = NombreU1 + "   " + etEdadRU1 + "   " + PerfilSelect1;

                        if (!NombreU1.isEmpty() && !etEdadRU1.isEmpty()) {
                            Usuarios.add(RegistroUs1);
                            etRegistroU.getText().clear();
                            SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Set<String> listaGuardada = new HashSet<>(Usuarios);
                            editor.putStringSet("dataSet", listaGuardada);
                            editor.apply();
                            editor.commit();

                            TipoUsuarios.add(PerfilSelect1);
                            SharedPreferences sharedPreferences1 = getSharedPreferences("MiSharedPreferences1", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                            Set<String> listaGuardada1 = new HashSet<>(TipoUsuarios);
                            editor1.putStringSet("dataSet1", listaGuardada1);
                            editor1.apply();
                            editor1.commit();

                        } else {
                            Toast.makeText(getApplicationContext(), "No se puede guardar un registro vacio ", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });

            Registro.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialogo = Registro.create();
            dialogo.show();

        } else if(id==R.id.MCambiar) {
            //Dialogo de Cambio de Usuario
            AlertDialog.Builder Cambiar = new AlertDialog.Builder(Strimings.this);
            Cambiar.setTitle("Cambiar de Usuario");
            View selectCambiar = getLayoutInflater().inflate(R.layout.usuarios, null);
            Cambiar.setView(selectCambiar);
            Cambiar.setCancelable(false);

            LV = (ListView) selectCambiar.findViewById(R.id.LV);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(Strimings.this, R.layout.listview_p, R.id.TVP, Usuarios);
            adapter2.notifyDataSetChanged();
            LV.setAdapter(adapter2);

            LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences Usa1 = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                    String cadena = LV.getItemAtPosition(position).toString();
                    String[] partes = cadena.split("   ");

                    String UsuarioPe = partes[0]; // Primera palabra
                    String EdadUsu = partes[1]; // Segunda palabra
                    String TipoUsuarioR = partes[2]; // Segunda palabra

                    String UsuarioRegis = UsuarioPe;

                    SharedPreferences.Editor UsaR1 = Usa1.edit();
                    UsaR1.putString("UsuarioRegis", UsuarioRegis.toString());
                    UsaR1.putString("EdadUsu", EdadUsu.toString());
                    UsaR1.putString("TipoUsuarioR", TipoUsuarioR.toString());
                    UsaR1.commit();
                    Toast.makeText(getApplicationContext(), "Bienvenido " + UsuarioPe, Toast.LENGTH_SHORT).show();

                    Log.i("INFO:", "Perfil");
                    Intent intent = new Intent(Strimings.this, Strimings.class);
                    startActivity(intent);


                }
            });

            Cambiar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialogo1 = Cambiar.create();
            dialogo1.show();

        } else if(id==R.id.MFinalizar) {
            Toast.makeText(getApplicationContext(), "Aplicacion Finalizada!", Toast.LENGTH_SHORT).show();
            Log.i("INFO:", "App Finish");
            finishAffinity();

        }

        return super.onOptionsItemSelected(item);

    }

    //Metodos para Peliculas
    private void Frozen(){
        SharedPreferences Peli = getSharedPreferences("Pelicula", Context.MODE_PRIVATE);
        String PeliculaSelect = "frozendos";

        SharedPreferences.Editor editPeli = Peli.edit();
        editPeli.putString("PeliculaSelect", PeliculaSelect.toString());
        editPeli.commit();
        Toast.makeText(getApplicationContext(), "Pelicula Seleccionada " + PeliculaSelect, Toast.LENGTH_SHORT).show();
        Log.i("INFO:", "Pelicula");
        Intent intent = new Intent(Strimings.this, Peliculas.class);
        startActivity(intent);

    }

    private void Enredados(){
        SharedPreferences Peli = getSharedPreferences("Pelicula", Context.MODE_PRIVATE);
        String PeliculaSelect = "enredadospeli";

        SharedPreferences.Editor editPeli = Peli.edit();
        editPeli.putString("PeliculaSelect", PeliculaSelect.toString());
        editPeli.commit();
        Toast.makeText(getApplicationContext(), "Pelicula Seleccionada " + PeliculaSelect, Toast.LENGTH_SHORT).show();
        Log.i("INFO:", "Pelicula");
        Intent intent = new Intent(Strimings.this, Peliculas.class);
        startActivity(intent);

    }

    private void Ready(){
        SharedPreferences Peli = getSharedPreferences("Pelicula", Context.MODE_PRIVATE);
        String PeliculaSelect = "readyplayer";

        SharedPreferences.Editor editPeli = Peli.edit();
        editPeli.putString("PeliculaSelect", PeliculaSelect.toString());
        editPeli.commit();
        Toast.makeText(getApplicationContext(), "Pelicula Seleccionada " + PeliculaSelect, Toast.LENGTH_SHORT).show();
        Log.i("INFO:", "Pelicula");
        Intent intent = new Intent(Strimings.this, Peliculas.class);
        startActivity(intent);

    }

    private void Pixeles(){
        SharedPreferences Peli = getSharedPreferences("Pelicula", Context.MODE_PRIVATE);
        String PeliculaSelect = "pixelespeli";

        SharedPreferences.Editor editPeli = Peli.edit();
        editPeli.putString("PeliculaSelect", PeliculaSelect.toString());
        editPeli.commit();
        Toast.makeText(getApplicationContext(), "Pelicula Seleccionada " + PeliculaSelect, Toast.LENGTH_SHORT).show();
        Log.i("INFO:", "Pelicula");
        Intent intent = new Intent(Strimings.this, Peliculas.class);
        startActivity(intent);

    }

    private void Anabelle(){
        SharedPreferences Peli = getSharedPreferences("Pelicula", Context.MODE_PRIVATE);
        String PeliculaSelect = "annable";

        SharedPreferences.Editor editPeli = Peli.edit();
        editPeli.putString("PeliculaSelect", PeliculaSelect.toString());
        editPeli.commit();
        Toast.makeText(getApplicationContext(), "Pelicula Seleccionada " + PeliculaSelect, Toast.LENGTH_SHORT).show();
        Log.i("INFO:", "Pelicula");
        Intent intent = new Intent(Strimings.this, Peliculas.class);
        startActivity(intent);

    }

    private void Conjuro(){
        SharedPreferences Peli = getSharedPreferences("Pelicula", Context.MODE_PRIVATE);
        String PeliculaSelect = "elconjuro";

        SharedPreferences.Editor editPeli = Peli.edit();
        editPeli.putString("PeliculaSelect", PeliculaSelect.toString());
        editPeli.commit();
        Toast.makeText(getApplicationContext(), "Pelicula Seleccionada " + PeliculaSelect, Toast.LENGTH_SHORT).show();
        Log.i("INFO:", "Pelicula");
        Intent intent = new Intent(Strimings.this, Peliculas.class);
        startActivity(intent);

    }

}