package com.example.dev_projet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ContactsActivity2 extends AppCompatActivity {



    Cursor cur;
    SQLiteDatabase db;
    LinearLayout layNaviguer , layRecherche ;
    EditText _txtId, _txtNom, _txtAdresse, _txtTel1, _txtTel2, _txtEntreprise, _txtRechercheContact;
    ImageButton _btnRecherche;
    Button _btnFirst, _btnPrevious, _btnNext, _btnLast;
    Button _btnAdd, _btnUpdate, _btnDelete;
    Button _btnCancel, _btnSave, _btnCall;
    int op = 0;
    String x ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts2);

        layNaviguer = (LinearLayout) findViewById(R.id.layNaviguer);


        _txtRechercheContact = (EditText) findViewById(R.id.txtRechercheContact);
        _txtId = (EditText) findViewById(R.id.txtId);
        _txtNom= (EditText) findViewById(R.id.txtNom);
        _txtAdresse = (EditText) findViewById(R.id.txtAdresse);
        _txtTel1 = (EditText) findViewById(R.id.txtTel1);
        _txtTel2= (EditText) findViewById(R.id.txtTel2);
        _txtEntreprise= (EditText) findViewById(R.id.txtEntreprise);

        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);

        _btnFirst = (Button) findViewById(R.id.btnFirst);
        _btnPrevious = (Button) findViewById(R.id.btnPrevious);
        _btnNext= (Button) findViewById(R.id.btnNext);
        _btnLast = (Button) findViewById(R.id.btnLast);

        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        _btnCall = (Button) findViewById(R.id.btnCall);

        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);

        db = openOrCreateDatabase("Contact",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS CONTACT (id integer primary key ,autoincriment, Nom VARCHAR , Adresse VARCHAR , Tel1 VARCHAR , Tel2 VARCHAR , Entreprise VARCHAR);" );

        layNaviguer.setVisibility(View.INVISIBLE);
        _btnCancel.setVisibility(View.INVISIBLE);
        _btnSave.setVisibility(View.INVISIBLE);
        _btnCall.setVisibility(View.INVISIBLE);

        _btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur = db.rawQuery("select * from Contact where id like?" ,new String[]{"%" + _txtRechercheContact.getText().toString() + "%"});
                try{
                    cur.moveToFirst();
                    _txtId.setText(cur.getString(1));
                    _txtNom.setText(cur.getString(2));
                    _txtAdresse.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
                    _txtEntreprise.setText(cur.getString(6));
                    if(cur.getCount() == 1){
                        layNaviguer.setVisibility(View.INVISIBLE);
                    } else {
                        layNaviguer.setVisibility(View.VISIBLE);
                        _btnPrevious.setEnabled(false);
                        _btnNext.setEnabled(false);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Aucun resultat.", Toast.LENGTH_SHORT).show();
                    _txtId.setText("");
                    _txtNom.setText("");
                    _txtAdresse.setText("");
                    _txtTel1.setText("");
                    _txtTel2.setText("");
                    _txtEntreprise.setText("");
                    layNaviguer.setVisibility(View.INVISIBLE);

                }

            }
        });

        _btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToFirst();
                    _txtId.setText(cur.getString(1));
                    _txtNom.setText(cur.getString(2));
                    _txtAdresse.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
                    _txtEntreprise.setText(cur.getString(6));
                    _btnPrevious.setEnabled(false);
                    _btnNext.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun contact n'est existant.",Toast.LENGTH_SHORT).show();

                }
            }
        });

        _btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToFirst();
                    _txtId.setText(cur.getString(1));
                    _txtNom.setText(cur.getString(2));
                    _txtAdresse.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
                    _txtEntreprise.setText(cur.getString(6));
                    _btnPrevious.setEnabled(false);
                    _btnNext.setEnabled(true);



                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun contact n'est existant.",Toast.LENGTH_SHORT).show();

                }
            }
        });

        _btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToNext();
                    _txtId.setText(cur.getString(1));
                    _txtNom.setText(cur.getString(2));
                    _txtAdresse.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
                    _txtEntreprise.setText(cur.getString(6));
                    _btnPrevious.setEnabled(true);
                    if (cur.isLast()){
                        _btnNext.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });

        _btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cur.moveToPrevious();
                    _txtId.setText(cur.getString(1));
                    _txtNom.setText(cur.getString(2));
                    _txtAdresse.setText(cur.getString(3));
                    _txtTel1.setText(cur.getString(4));
                    _txtTel2.setText(cur.getString(5));
                    _txtEntreprise.setText(cur.getString(6));

                    _btnNext.setEnabled(true);
                    if (cur.isFirst()){
                        _btnPrevious.setEnabled(false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });

        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
                _txtId.setText("");
                _txtNom.setText("");
                _txtAdresse.setText("");
                _txtTel1.setText("");
                _txtTel2.setText("");
                _txtEntreprise.setText("");
                _btnSave.setVisibility(View.VISIBLE);
                _btnCancel.setVisibility(View.VISIBLE);
                _btnUpdate.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.INVISIBLE);
                _btnAdd.setEnabled(false);
                layNaviguer.setVisibility(View.INVISIBLE);
                layRecherche.setVisibility(View.INVISIBLE);
            }
        });

        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // tester si les champs ne sont pas vides
                try {
                    x = cur.getString(0);
                    op = 2;

                    _btnSave.setVisibility(View.VISIBLE);
                    _btnCancel.setVisibility(View.VISIBLE);

                    _btnDelete.setVisibility(View.INVISIBLE);
                    _btnUpdate.setEnabled(false);
                    _btnAdd.setVisibility(View.INVISIBLE);

                    layNaviguer.setVisibility(View.INVISIBLE);
                    layRecherche.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Sélectionnez un conctact puis appyuer sur le bouton de modification",Toast.LENGTH_SHORT).show();
                }

            }
        });

        _btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1){
                    // insertion
                    db.execSQL("insert into contact (id,Nom,Adresse,Tel1,Tel2,Entreprise) values (?,?,?,?,?,?);", new String[] {_txtId.getText().toString(), _txtNom.getText().toString(),_txtAdresse.getText().toString(),_txtTel1.getText().toString(),_txtTel2.getText().toString(),_txtEntreprise.getText().toString()});
                } else if (op == 2) {
                    // Mise à jour
                    db.execSQL("update contact set id=?, Nom=?, Adresse=?, Tel1=? ,  Tel2=? ,  Entreprise=? where id=?;", new String[] {_txtId.getText().toString(), _txtNom.getText().toString(),_txtAdresse.getText().toString(),_txtTel1.getText().toString(),_txtTel2.getText().toString(),_txtEntreprise.getText().toString(),x});
                }

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);
                _btnRecherche.performClick();
                layRecherche.setVisibility(View.VISIBLE);
            }
        });

        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 0;

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);

                layRecherche.setVisibility(View.VISIBLE);
            }
        });


        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    x=  cur.getString(0);
                    AlertDialog dial = MesOptions();
                    dial.show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Sélectionner un contact puis appyuer sur le bouton de suppresssion",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        _btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=_txtTel1.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });



    }
    private AlertDialog MesOptions(){
        AlertDialog MiDia =  new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Est ce que vous voulez supprimer contact?")
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.execSQL("delete from comptes where id=?;",new String[] {cur.getString(0)});
                        _txtId.setText("");
                        _txtNom.setText("");
                        _txtAdresse.setText("");
                        _txtTel1.setText("");
                        _txtTel2.setText("");
                        _txtEntreprise.setText("");
                        layNaviguer.setVisibility(View.INVISIBLE);
                        cur.close();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        return MiDia;
    }



}
