package com.gmail.yamen.asynctasktest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText text1 ;
    private EditText text2 ;
    private Button btn1 ;
    public TextView res1;
    private String strinUrl;
    private String result;
    int num1;
    int num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText) findViewById(R.id.edit1);
        text2 = (EditText) findViewById(R.id.edit2);
        btn1 = (Button) findViewById(R.id.calculate);
        res1 = (TextView) findViewById(R.id.res);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AsyncTest().execute();

            }
        });
    }



    public class AsyncTest extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             num1 = Integer.parseInt(text1.getText().toString());
             num2 = Integer.parseInt(text2.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            res1.setText(result);
        }

        @Override
        protected String doInBackground(String... params) {
            strinUrl = "http://www.telusko.com/addition.htm?t1="+num1+"&t2="+num2 ;
            try{
                URL url = new URL(strinUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value = bf.readLine();
                result =value;

            }catch (Exception e){
                System.out.print("there is error "+e);
            }




            return null;
        }
    }
}
