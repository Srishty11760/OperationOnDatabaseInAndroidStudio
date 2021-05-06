package com.example.q15;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity<onClick> extends AppCompatActivity {
    EditText e1, e2, e3,e4,e5,e6;
    Button b1,b2,b3,b4;
    dbclass myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new dbclass(this);
        e1=(EditText)findViewById(R.id.t1);
        e2=(EditText)findViewById(R.id.t2);
        e3=(EditText)findViewById(R.id.t3);
        e4=(EditText)findViewById(R.id.t4);
        e5=(EditText)findViewById(R.id.t5);
        e6=(EditText)findViewById(R.id.t6);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b4=(Button)findViewById(R.id.b4);
        AddData();
        viewAll();
        b3.setOnClickListener(new delrec());
        b4.setOnClickListener(new updrec());
    }
    class delrec implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            myDb.delete(e4.getText().toString());
        }
    }
    class updrec implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            myDb.update(e5.getText().toString(),
                    e6.getText().toString());
        }
    }
    public void AddData()
    {
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted=myDb.insertMyData(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                        if(isInserted=true) {
                            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                        }
                        else
                            Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll()
    {
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDb.getMyAllData();
                        if(res.getCount()==0)
                        {

                            showMessage("Error","No data found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while(res.moveToNext())
                        {
                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("NAME :" + res.getString(1) + "\n");
                            buffer.append("COURSE :" + res.getString(2) + "\n\n");

                            showMessage("data", buffer.toString());
                        }
                    }
                }
        );
    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}