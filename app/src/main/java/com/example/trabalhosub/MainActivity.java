package com.example.trabalhosub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("Local", "MainActivity");

        //Chamar execução em segundo plano
        BuscaPais buscaPais = new BuscaPais();
        buscaPais.execute();


    }
}