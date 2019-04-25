package com.dictionaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnglishToNepaliDictionary extends AppCompatActivity {

    private ListView lstDictionary;
    private Map<String, String> dictionary;
    public static final String words[] = {
            "timro naam k ho", "What is your name",
            "mero ghar gorkha ho", "My home is Gorkha",
            "ma kta ho", "I am a boy",
            "mero aauta sathi xa", "I have a friend",
            "mero naam rojin ho", "my name is Rojin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_to_nepali_dictionary);

        lstDictionary = findViewById(R.id.lstDictionary);
        dictionary = new HashMap<>();
        /*for (int i = 0; i < words.length; i += 2) {
            dictionary.put(words[i], words[i + 1]);
        }*/
        ArrayAdapter english = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        lstDictionary.setAdapter(english);

        lstDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = dictionary.get(key);

                Intent intent = new Intent(EnglishToNepaliDictionary.this, SecondActivity.class);

                intent.putExtra("meaning", meaning);
                startActivity(intent);
            }
        });


    }
    private void readFromFile(){
        try {
            FileInputStream fos=openFileInput("words.txt");
            InputStreamReader isr=new InputStreamReader(fos);
            BufferedReader br=new BufferedReader(isr);
            String line="";
            while((line=br.readLine())!=null){
                String[] parts=line.split("->");
                dictionary.put(parts[0],parts[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
