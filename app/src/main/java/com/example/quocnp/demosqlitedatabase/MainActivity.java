package com.example.quocnp.demosqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper mDatabaseHelper;
    private Button mBtnAddData;
    private EditText mEdtName;
    private EditText mEdtSurName;
    private EditText mEdtMarks;
    private Button mBtnShowData;
    private Button mBtnUpdateData;
    private EditText mEdtId;
    private Button mBtnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);
        initViews();
        initListenerViews();
    }

    private void initListenerViews() {
        mBtnAddData.setOnClickListener(this);
        mBtnShowData.setOnClickListener(this);
        mBtnUpdateData.setOnClickListener(this);
        mBtnDeleteData.setOnClickListener(this);
    }

    private void addData() {
        boolean isCheckInserted = mDatabaseHelper.insertData(mEdtName.getText().toString(),
                mEdtSurName.getText().toString(),
                mEdtMarks.getText().toString());
        if (isCheckInserted = true) {
            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
        }
    }

    private void showData() {
        Cursor res = mDatabaseHelper.getAllData();
        if (res.getCount() == 0) {
            //show message
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :").append(res.getString(0)).append("\n");
            buffer.append("Name :").append(res.getString(1)).append("\n");
            buffer.append("SurName :").append(res.getString(2)).append("\n");
            buffer.append("Marks :").append(res.getString(3)).append("\n\n");
        }
        //show all data
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void updateData() {
        boolean isCheckUpdate = mDatabaseHelper.updateData(mEdtId.getText().toString(),
                mEdtName.getText().toString(),
                mEdtSurName.getText().toString(),
                mEdtMarks.getText().toString());
        if (isCheckUpdate == true) {
            Toast.makeText(MainActivity.this, "Data update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Data not update", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData() {
        Integer deleteRow = mDatabaseHelper.deleteData(mEdtId.getText().toString());
        if (deleteRow > 0) {
            Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Data not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtSurName = (EditText) findViewById(R.id.edtSurName);
        mEdtMarks = (EditText) findViewById(R.id.edtMarks);
        mEdtId = (EditText) findViewById(R.id.edtId);


        mBtnAddData = (Button) findViewById(R.id.btnAddData);
        mBtnShowData = (Button) findViewById(R.id.btnShowData);
        mBtnUpdateData = (Button) findViewById(R.id.btnUpdateData);
        mBtnDeleteData = (Button) findViewById(R.id.btnDeleteData);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddData:
                addData();
                break;
            case R.id.btnShowData:
                showData();
                break;
            case R.id.btnUpdateData:
                updateData();
                break;
            case R.id.btnDeleteData:
                deleteData();
                break;
        }
    }
}
