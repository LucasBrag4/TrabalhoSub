package com.example.trabalhosub;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.ProgressDialog;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.trabalhosub.objeto.Paises;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public ProgressDialog carregando;
    Spinner spinnerPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerPaises = findViewById(R.id.activity_main_spinner_paises);


        String respostaPais = null;

        //Chamar execução em segundo plano
        BuscaPais buscaPais = new BuscaPais(this);
        try {
           respostaPais = buscaPais.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Gson gsonPaises = new GsonBuilder().setPrettyPrinting().create();
        Paises[] pais = gsonPaises.fromJson(String.valueOf(respostaPais), Paises[].class);

        ArrayList<String> paisesParaSpinner = new ArrayList<>();

            for(Paises paises: pais){
            paisesParaSpinner.add(paises.getDescricao());
        }

        ArrayAdapter<String> adapterPaises = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                paisesParaSpinner);

            spinnerPaises.setAdapter(adapterPaises);
    }
}