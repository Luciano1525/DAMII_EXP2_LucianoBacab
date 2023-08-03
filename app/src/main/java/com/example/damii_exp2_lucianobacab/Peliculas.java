package com.example.damii_exp2_lucianobacab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Peliculas extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView ivPerfil;
    private Uri photoUri;
    private VideoView vvPelicula;
    private MediaController Control;
    private TextView tvUsuario1, tvEdadUsuario, tvClasificacion;
    private LinearLayout linearLayout3, linearLayout4;
    private boolean validar = false;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas);


        tvUsuario1 = (TextView) findViewById(R.id.tvUsuario1);
        tvEdadUsuario = (TextView) findViewById(R.id.tvEdadUsuario);
        tvClasificacion = (TextView) findViewById(R.id.tvClasificacion);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        ivPerfil = (ImageView)findViewById(R.id.ivPerfil);
        vvPelicula = findViewById(R.id.vvPelicula);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        //Recuperar datos del Usuario
        SharedPreferences Usa = getSharedPreferences("Usuario", Context.MODE_PRIVATE);

        String UsuRec = Usa.getString("UsuarioRegis", "No Registrado");
        String EdadUsr = Usa.getString("EdadUsu", "0");
        String TiUsua = Usa.getString("TipoUsuarioR", "Tipo de Usuario Null");
        tvUsuario1.setText(UsuRec);
        tvEdadUsuario.setText("Edad: " + EdadUsr);
        tvClasificacion.setText("Categoria: " + TiUsua);

        //Recuperar la Validacion
        SharedPreferences vali1 = getSharedPreferences("Validacion", Context.MODE_PRIVATE);
        String Valid = vali1.getString("ValidarTF", "false");
        validar = Boolean.parseBoolean(Valid);

        //Dialogo para registrar foto
        if(!validar){
            AlertDialog.Builder builder = new AlertDialog.Builder(Peliculas.this);
            builder.setTitle("Reproduccion");
            builder.setMessage("Necesita Registrar una Foto para Reproducir la Pelicula \n " +
                    "Desea registrar una Foto?");
            builder.setCancelable(false);

            builder.setPositiveButton("Registrar Foto", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Registrar Foto
                    if (ContextCompat.checkSelfPermission(Peliculas.this, Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {
                        // Si el permiso para acceder a la cámara está concedido
                        openCamera();
                        SharedPreferences vali = getSharedPreferences("Validacion", Context.MODE_PRIVATE);
                        String valida = "true";
                        SharedPreferences.Editor editVal = vali.edit();
                        editVal.putString("ValidarTF", valida.toString());
                        editVal.commit();
                    } else {
                        // Si no está concedido, solicitamos el permiso
                        ActivityCompat.requestPermissions(Peliculas.this,
                                new String[]{Manifest.permission.CAMERA},
                                REQUEST_CAMERA_PERMISSION);
                    }

                    ivPerfil.setVisibility(View.VISIBLE);
                }
            });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("INFO:", "StrimingsApp");
                Intent intent = new Intent(Peliculas.this, Strimings.class);
                startActivity(intent);

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        } else {
            //No existe dato
        }

        SharedPreferences Peli = getSharedPreferences("Pelicula", Context.MODE_PRIVATE);
        String PeliSelect = Peli.getString("PeliculaSelect", "Pelicula No Existente");

        Control = new MediaController(this);
        int Video = getVideo(PeliSelect);
        Uri videoU = Uri.parse("android.resource://" + getPackageName() + "/" + Video);

        int orientacion = getResources().getConfiguration().orientation;
        if (orientacion == Configuration.ORIENTATION_LANDSCAPE) {
            tvUsuario1.setVisibility(View.GONE);
            tvEdadUsuario.setVisibility(View.GONE);
            tvClasificacion.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.GONE);
            ivPerfil.setVisibility(View.GONE);
            btnSalir.setVisibility(View.GONE);

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT, // Ancho
                    ConstraintLayout.LayoutParams.MATCH_PARENT  // Altura
            );
            vvPelicula.setLayoutParams(layoutParams);

            // Configuracion del controlador del reproductor de video
            vvPelicula.setMediaController(Control);
            Control.setAnchorView(vvPelicula);

            // Establece la URI del video en el VideoView
            vvPelicula.setVideoURI(videoU);

        } else {
            tvUsuario1.setVisibility(View.VISIBLE);
            tvEdadUsuario.setVisibility(View.VISIBLE);
            tvClasificacion.setVisibility(View.VISIBLE);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout4.setVisibility(View.VISIBLE);
            ivPerfil.setVisibility(View.VISIBLE);
            btnSalir.setVisibility(View.VISIBLE);

            // Configuracion del controlador del reproductor de video
            vvPelicula.setMediaController(Control);
            Control.setAnchorView(vvPelicula);

            // Establece la URI del video en el VideoView
            vvPelicula.setVideoURI(videoU);
        }


        //Boton para salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences vali = getSharedPreferences("Validacion", Context.MODE_PRIVATE);
                String valida = "false";
                SharedPreferences.Editor editVal = vali.edit();
                editVal.putString("ValidarTF", valida.toString());
                editVal.commit();
                Log.i("INFO:", "Pelicula");
                Intent intent = new Intent(Peliculas.this, Strimings.class);
                startActivity(intent);
            }
        });


    }

    //Metodo para permitir acceso a la camara, tomar foto y guardarla en el ImageView
    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                if (imageBitmap != null) {
                    ivPerfil.setImageBitmap(imageBitmap);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
        }
    }


    //Seleccion de Pelicula
    private int getVideo(String PeliSelect) {
        switch (PeliSelect) {
            case "frozendos":
                return R.raw.frozendos;
            case "enredadospeli":
                return R.raw.enredadospeli;
            case "readyplayer":
                return R.raw.readyplayer;
            case "pixelespeli":
                return R.raw.pixelespeli;
            case "annable":
                return R.raw.annable;
            case "elconjuro":
                return R.raw.elconjuro;
            default:
                return 0;
        }

    }



}