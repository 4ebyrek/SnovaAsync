package com.example.batman.snovaasync;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    public Elements content;
    public Document document;
    ListView NewsList ;
    ArrayList<String> NewsArray = new ArrayList<String>();
    ArrayList<String> UrlsArray = new ArrayList<String>();
    ArrayList<String> NewsItem  = new ArrayList<String>();
    ArrayList<String> NewsitemArray = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsList = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NewsArray);
        new NewsThread().execute();
        NewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1;
                id1 = (int) id;
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("first", NewsItem.get(id1));
                startActivity(intent);
            }
        });
    }

    public class NewsThread extends AsyncTask<String,Void,String>{

            Document doc ;
            Elements cont;
        @Override
        protected String doInBackground(String... params) {
            try {
                document = Jsoup.connect("http://www.sports.ru/topnews/").get();
                content = document.select(".short-text");
                NewsArray.clear();
                UrlsArray.clear();
                NewsItem.clear();
                for (Element elements : content){
                    urls = "http://www.sports.ru"+elements.attr("href");
                    doc = Jsoup.connect(urls).get();
                    cont = doc.select(".news-item__content");
                    for(Element elementi :cont){
                        NewsItem.add(elementi.text());
                   }
                    UrlsArray.add(urls);
                    NewsArray.add(elements.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            for(int i=0;i<UrlsArray.size();i++){
                Log.d("****************",UrlsArray.get(i));
            }
            NewsList.setAdapter(adapter);
        }
    }
}
