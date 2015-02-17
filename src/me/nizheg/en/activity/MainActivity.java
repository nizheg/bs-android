package me.nizheg.en.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.nizheg.en.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeListView();
    }

    private void initializeListView() {
        ListView listView = (ListView) findViewById(R.id.listView);

        String[] captions = new String[]{
                getResources().getString(R.string.activity_caption_browser),
                getResources().getString(R.string.activity_caption_braille),
                getResources().getString(R.string.activity_caption_morse)
        };

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, captions);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private Class<?>[] activities = new Class<?>[]{
                    BrowserActivity.class,
                    BrailleActivity.class,
                    MorseActivity.class
            };

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                startActivity(activities[position]);
            }
        });
    }


    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
