package com.example.mknight.sql_1;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    private TextView editName, editMarks, edit_Id;
    private Button btnaddData;
    private Button btnViewAll;
    private Button btn_delete;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.txtName);
        editMarks = (EditText) findViewById(R.id.txtMarks);
        edit_Id = (EditText) findViewById(R.id.txtId);
        btnaddData = (Button) findViewById(R.id.button);
        btnViewAll = (Button) findViewById(R.id.btnView);
        btn_delete = (Button) findViewById(R.id.btnDel);
        AddData();
        viewAll();
        delet_Data();

        // ATTENTION: This was auto-generated to implement  the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public void delet_Data(){
        btn_delete.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Integer deleteRow = mydb.deleteData(edit_Id.getText().toString());
                        if(deleteRow>0)
                            Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void AddData() {
        btnaddData.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        boolean inInserted = mydb.insertData(editName.getText().toString(), editMarks.getText().toString());
                        if (inInserted = true)
                            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new  View.OnClickListener(){
                    public void onClick(View view){
                        Cursor res = mydb.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing to show");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Marks :"+ res.getString(2)+"\n\n");
                            buffer.append("Time :"+ res.getString(3)+"\n\n");
                        }
                        showMessage("Data",buffer.toString());
                        //show all the data
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    public Action getIndexApiAction() {
//        Thing object = new Thing.Builder()
//                .setName("Main Page") // TODO: Define a title for the content shown.
//                // TODO: Make sure this auto-generated URL is correct.
//                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
//                .build();
//        return new Action.Builder(Action.TYPE_VIEW)
//                .setObject(object)
//                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
//                .build();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
//    }
}
