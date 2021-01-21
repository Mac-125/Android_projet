 package com.example.dev_projet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.dev_projet.Sharedhelper.sha256;

 public class MainActivity extends AppCompatActivity {


    EditText _txtlogin,_txtPassword ;
    Button _btnConnection ;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtlogin = (EditText) findViewById(R.id.txtlogin);
        _txtPassword = (EditText) findViewById(R.id.txtPassword );
        _btnConnection = (Button) findViewById(R.id.btnConnection );


        db = openOrCreateDatabase(  "Conctats",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS USERS (login varchar primary key, password varchar);");
        SQLiteStatement s = db.compileStatement("select count(*) from users;");
        long c = s.simpleQueryForLong();
        if ( c==0){
            db.execSQL("insert into users (login, password) values (?,?)", new String[]{"admin",sha256("123")});
        }
        _btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strlogin = _txtlogin.getText().toString();
                String strPwd = _txtPassword.getText().toString();
                Cursor cur = db.rawQuery("select password from users where login = ?" , new String[] {strlogin });
                try  {
                    cur.moveToFirst();
                    String P = cur.getString( 0);
                    if (P.equals(sha256(strPwd))){
                        Toast.makeText(getApplicationContext(),"Bienvenue" + strlogin, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),ContactsActivity2.class);
                        startActivity(i);

                    }else  {
                        _txtlogin.setText("");
                        _txtPassword.setText("");
                        Toast.makeText(getApplicationContext(), "Echec de connexion", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e ) {
                    _txtlogin.setText("");
                    _txtPassword.setText("");
                    Toast.makeText(getApplicationContext(), "Utilisateur Inexistant ", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}