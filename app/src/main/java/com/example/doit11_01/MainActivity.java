package com.example.doit11_01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText titleText;
    EditText authorText;
    EditText contentText;
    Button saveBtn;
    SQLiteDatabase database;
    String databaseName="book";
    String tableName="bookTable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleText=findViewById(R.id.titleText);
        authorText=findViewById(R.id.authorText);
        contentText=findViewById(R.id.contentText);
        saveBtn=findViewById(R.id.saveBtn);
        createDatabase(databaseName);

        createTable(tableName);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleText.getText().toString();
                String author=authorText.getText().toString();
                String content=contentText.getText().toString();
                insertRecord(title, author, content);
                Toast.makeText(getApplicationContext(), "저장됨", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createDatabase(String databaseName){
        try{
            database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        }catch(Exception e){
            e.printStackTrace();
        };
        Toast.makeText(getApplicationContext(), "데이타베이스 생성", Toast.LENGTH_LONG).show();
    }
    public void createTable(String tableName){
        try {
            if (database == null) {
                return;
            }
            String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, title text, author text, content text)";
            database.execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
        };
        Toast.makeText(getApplicationContext(), "테이블 생성", Toast.LENGTH_LONG).show();
    }
    public void insertRecord(String title, String author, String content){
        try {
            if (database == null) {
                return;
            }
            if (tableName == null) {
                return;
            }
            String sql = "insert into " + tableName + "(title, author , content) values('" + title + "', '" + author + "', '" + content + "')";
            database.execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
        };
    }
}