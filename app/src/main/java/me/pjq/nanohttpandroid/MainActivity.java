package me.pjq.nanohttpandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fi.iki.elonen.NanoHTTPD;
import nanohttpd.NanoConstant;
import nanohttpd.NanoFileServer;
import nanohttpd.NanoWebViewActivity;

public class MainActivity extends AppCompatActivity {
    TextView content;
    NanoHTTPD nanoServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NanoWebViewActivity.start(MainActivity.this, NanoConstant.LOCAL_URL);
                NanoWebViewActivity.start(MainActivity.this);
                updateServerStatus("launch in local webview");
            }
        });

        content = (TextView) findViewById(R.id.content);
        nanoServer = NanoFileServer.startFileServerWithAppDataDir(getApplicationContext());
//        nanoServer = NanoServer.startServer();
        updateServerStatus("Running on " + NanoConstant.LOCAL_URL);
    }

    void updateServerStatus(String status) {
        content.append(status + '\n');
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        nanoServer.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
