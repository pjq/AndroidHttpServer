package nanohttpd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.webkit.WebSettings;
import android.webkit.WebView;

import me.pjq.nanohttpandroid.R;

/**
 * Created by i329817(Jianqing.Peng@sap.com) on 22/02/2017.
 */

public class NanoWebViewActivity extends Activity {
    public static void start(Context context) {
        Uri uri = Uri.parse(NanoConstant.LOCAL_URL);
        // create an intent builder
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        // Begin customizing
        // set toolbar colors
        intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        intentBuilder.enableUrlBarHiding();

        // build custom tabs intent
        CustomTabsIntent customTabsIntent = intentBuilder.build();

        // launch the url
        customTabsIntent.launchUrl(context, uri);
    }

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, NanoWebViewActivity.class);
        starter.putExtra("url", url);
        context.startActivity(starter);
    }

    private WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nano_webview);

        url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(url);
    }
}
