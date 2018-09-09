package com.example.soring.bandcv12.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.soring.bandcv12.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LocationFragment extends Fragment {

    private static LocationFragment instance;

    TextView get_text;
    Button get_btn;
    Elements contents;
    Document doc = null;

    String name;

    public static LocationFragment getInstance() {
        if(instance == null) instance = new LocationFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        get_text = view.findViewById(R.id.get_text);
        get_btn = view.findViewById(R.id.get_btn);

        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask(){

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        try {
                            Log.e("try로 빠짐 ","try");
                            doc = Jsoup.connect("https://myaccount.google.com/privacy?utm_source=OGB&utm_medium=act#personalinfo").get(); //naver페이지를 불러옴
                            contents = doc.select("div.K0zRed > a.bsFZu");//셀렉터로 span태그중 class값이 ah_k인 내용을 가져옴
                            Log.e("contents ",""+contents);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("catch로 빠짐 ","catch");
                        }

                        for(Element element: contents) {
                            name = element.text();
                        }
                        Log.e("이름 ",""+name);

                        return null;
                    }
                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        Log.e("post 이름 ",""+name);
                        get_text.setText(name);
                    }

                }.execute();

            }
        });

        return view;
    }

}
