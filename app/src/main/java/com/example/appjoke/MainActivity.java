package com.example.appjoke;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvContent, tvYes, tvNo;
    int indexListJoke = 0;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.text_view_content);
        tvYes = findViewById(R.id.text_view_yes);
        tvNo = findViewById(R.id.text_view_no);

        tvContent.setText(Joke.getMock().get(indexListJoke).getContent());
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result += "Joke " + Joke.getMock().get(indexListJoke).getId() + ": Funny\n";
                if (indexListJoke < 3) {
                    indexListJoke++;
                    tvContent.setText(Joke.getMock().get(indexListJoke).getContent());
                } else {
                    dialogNotification();
                }
            }
        });

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result += "Joke " + Joke.getMock().get(indexListJoke).getId() + ": No funny\n";
                if (indexListJoke < 3) {
                    indexListJoke++;
                    tvContent.setText(Joke.getMock().get(indexListJoke).getContent());
                } else {
                    dialogNotification();
                }
            }
        });
    }

    private void dialogNotification() {
        AppCache appCache = AppCache.getInstance(MainActivity.this);
        appCache.clearCache();
        appCache.saveDataString("result", result);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("That's all the jokes for today! Come back another day!\n\nThank you.");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }
}