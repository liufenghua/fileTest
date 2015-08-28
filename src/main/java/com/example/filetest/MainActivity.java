
package com.example.filetest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainActivity extends Activity {
    private Button button1,button2;
    private EditText text1,text2;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)this.findViewById(R.id.button1);
        button2=(Button)this.findViewById(R.id.button2);
        text1=(EditText)this.findViewById(R.id.edittext1);
        text2=(EditText)this.findViewById(R.id.edittext2);
        
        textview=(TextView)this.findViewById(R.id.textview);
        StringBuffer buf=new StringBuffer();
        buf.append(MainActivity.this.getFilesDir()).append(":|");
        for(String strr:MainActivity.this.fileList()){
            buf.append(strr);
        }
        textview.setText(buf.toString());
        button1.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                write(text1.getText().toString());     
                text1.setText("");
            }            
        });
        button2.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                text2.setText(read());
            }            
        });
    }
    private void write(String string) {
        try {
            FileOutputStream out=openFileOutput("liufenghua",Context.MODE_PRIVATE);
            PrintStream print=new PrintStream(out);
            print.println(string);
            print.close();
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }   
    }
    private String read() {
      try {
        FileInputStream input=openFileInput("liufenghua");
        byte[] data=new byte[1024];
        int hasread=0;
        StringBuffer str=new StringBuffer();
        while((hasread=input.read(data))>0){
            str.append(new String(data,0,hasread));
        }
        input.close();
        return str.toString();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
