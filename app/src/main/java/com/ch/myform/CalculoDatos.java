package com.ch.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculoDatos extends AppCompatActivity implements View.OnClickListener {
    private TextView informacion;
    private TextView resultado;
    private EditText peso;
    private EditText altura;
    private Button calcular;
    private ImageView imagen;
    private Double calcpeso;
    private Double calcestatura;
    private Double calcresultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_datos);
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nameCalcularDato");
        String apellido = intent.getStringExtra("serNameCalcularDato");
        String email = intent.getStringExtra("emailCalcularDato");
        String mensaje = "Hola " + nombre + " " + apellido + " , tu correo es: " + email + " Bienvenido.";

        informacion = findViewById(R.id.tvInformacion);
        resultado = findViewById(R.id.tvResultado);
        peso = findViewById(R.id.txtPeso);
        altura = findViewById(R.id.txtEstatura);
        calcular = findViewById(R.id.btnCalcular);
        imagen = findViewById(R.id.imgEstado);

        calcular.setOnClickListener(this);
        informacion.setText(mensaje);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalcular) {
            calcpeso = Double.parseDouble(peso.getText().toString());
            calcestatura = Double.parseDouble(altura.getText().toString());

            String guardarCalculo = calcularImc(calcpeso, calcestatura);
            resultado.setText(guardarCalculo);
        }
    }

    private String calcularImc(Double calcpeso, Double calcestatura) {
        DecimalFormat formato = new DecimalFormat();
        formato.setMaximumIntegerDigits(2);

        calcresultado = calcpeso/Math.pow(calcestatura,2);

        mostrarImagen(calcresultado);

        return formato.format(calcresultado);
    }

    private void mostrarImagen(Double calcresultado) {
        if (calcresultado < 18.5){
            imagen.setImageResource(R.drawable.bajo_peso2);
        }
        else if (calcresultado >= 18.5 && calcresultado <= 24.9){
            imagen.setImageResource(R.drawable.peso_normal2);
        }
        else if (calcresultado >= 25 && calcresultado <= 29.9){
            imagen.setImageResource(R.drawable.sobre_peso2);
        }
        else if (calcresultado >= 30 && calcresultado <= 34.9){
            imagen.setImageResource(R.drawable.obesidad2);
        }
        else {
            imagen.setImageResource(R.drawable.obesidad_extrema2);
        }
    }

}