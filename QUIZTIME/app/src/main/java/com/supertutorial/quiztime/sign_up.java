package com.supertutorial.quiztime;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {
    DatabaseHelper mydb;
    Button sb,lb;
    EditText ue,pe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sb=(Button)findViewById(R.id.button7);
        lb=(Button)findViewById(R.id.button8);
        ue=(EditText)findViewById(R.id.editText);
        pe=(EditText)findViewById(R.id.editText3);
       mydb=new DatabaseHelper(this);
       lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(sign_up.this,login.class);
                startActivity(it);
            }
        });
      sb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int f = 0;
               Cursor cursor = mydb.getAllData();
               while (cursor.moveToNext()) {
                   if (cursor.getString(1).equals(ue.getText().toString())) {
                       f = 1;
                   }
               }
               if (f == 0) {
                   boolean check = mydb.insertdata(ue.getText().toString(), pe.getText().toString());
                   Toast.makeText(sign_up.this, "Data inserted,Login to start", Toast.LENGTH_SHORT).show();
                   Intent it = new Intent(sign_up.this, login.class);
                   startActivity(it);
               }
               else{
                   Toast.makeText(sign_up.this, "Username already exits", Toast.LENGTH_LONG).show();
           }
          }
       });
    }
}
