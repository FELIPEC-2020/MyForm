package com.ch.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ib.custom.toast.CustomToastView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAceptar;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCorreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAceptar = findViewById(R.id.btnDone);
        txtNombre = findViewById(R.id.txtName);
        txtApellido = findViewById(R.id.txtSurName);
        txtCorreo = findViewById(R.id.txtEmail);
        btnAceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDone){
            String name = txtNombre.getText().toString();
            String apellido = txtApellido.getText().toString();
            String correo = txtCorreo.getText().toString();
            if (name.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al Validar el Nombre", R.layout.custom_toast).show();
                return;
            }
            if (apellido.isEmpty()){
                CustomToastView.makeInfoToast(this, "Error al Validar el Apellido", R.layout.custom_toast).show();
                return;
            }
            if (!isValidEmail(correo)){
                CustomToastView.makeInfoToast(this, "Error al validar el Email", R.layout.custom_toast).show();
                return;
            }
            Intent myIntent = new Intent( this, CalculoDatos.class);
            myIntent.putExtra("nameCalcularDato", name);
            myIntent.putExtra("serNameCalcularDato", apellido);
            myIntent.putExtra("emailCalcularDato", correo);
            startActivity(myIntent);
        }
    }

    private boolean isValidEmail(String correo) {
        Pattern patron = Patterns.EMAIL_ADDRESS;
        return patron.matcher(correo).matches();

    }
}