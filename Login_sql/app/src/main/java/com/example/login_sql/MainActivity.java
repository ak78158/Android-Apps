package com.example.login_sql;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editId,editPass;
    Button btnAddData;
    Button btnLogin;
    private Object DatabaseOperation;
    private Build TableInfo;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creating DatabaseHelper object which is used to create the database
        myDb = new DatabaseHelper(this);
        //Creating objects for all the components on the xml layout
        editId = (EditText)findViewById(R.id.idno);
        editPass = (EditText)findViewById(R.id.password);
        btnAddData = (Button)findViewById(R.id.addRecord);
        btnLogin= (Button)findViewById(R.id.login);
//Invoking the functions for the operations

        AddData();
    }


    public void AddData() { btnAddData.setOnClickListener( new View.OnClickListener() { @Override public void onClick(View v) {
        boolean isInserted = myDb.insertData(editId.getText().toString(), editPass.getText().toString());
        if(isInserted == true) Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show(); } } ); }

    public void Login() { btnLogin.setOnClickListener( new View.OnClickListener() { @Override public void onClick(View v) {
        EditText id = findViewById(R.id.idno);
        EditText pass = findViewById(R.id.password);
        String Id=id.getText().toString();
        String pwd = pass.getText().toString();

        public Cursor getInformation(DatabaseOperation myDb){
            SQLiteDatabase SQ=myDb.getReadableDatabase();
            String[] coloumns={TableInfo.ID,}
        }
        if(Id==){

            if(pwd==){

                if( == true) Toast.makeText(MainActivity.this,"Login Sucessfull",Toast.LENGTH_LONG).show();
        else Toast.makeText(MainActivity.this,"Incorrect Input",Toast.LENGTH_LONG).show();
            }
        }

         } } ); }

}