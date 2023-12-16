package com.example.trabalhosub;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class BuscaPais extends AsyncTask<Void, Void, String> {

    MainActivity mainActivity = new MainActivity();
    ProgressDialog carregando = mainActivity.carregando;

    private Context bpContext;

    public BuscaPais(Context context) {
        bpContext = context;

    }

    @Override
    protected void onPreExecute() {
        carregando = ProgressDialog.show(bpContext, "Carregando Paises", "Por favor, Aguarde!");
    }

    @Override
    protected String doInBackground(Void... voids) {

        StringBuilder respostaPais = new StringBuilder();


        try {
            URL urlPaises = new URL("https://falabr.cgu.gov.br/api/paises");

            HttpURLConnection conexao = (HttpURLConnection) urlPaises.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setDoOutput(true);
            conexao.setConnectTimeout(3000);
            conexao.connect();

            Scanner scanner = new Scanner(urlPaises.openStream());
            while (scanner.hasNext()) {
                respostaPais.append(scanner.next());
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return respostaPais.toString();
    }

    @Override
    protected void onPostExecute(String dados) {
        carregando.dismiss();

    }

}
