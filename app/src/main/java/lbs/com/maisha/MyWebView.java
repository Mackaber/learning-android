package lbs.com.maisha;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MyWebView extends ActionBarActivity {
    public static final String URL = "Url";
    private android.webkit.WebView webView;

    private String url;
    private ProgressDialog progrDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // Show the Up button in the action bar.
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        url = extras.getString(URL);
//        str[20..str.length]
//        url = "http://todomvc.com/examples/jquery/#/all";
        progrDialog = new ProgressDialog(MyWebView.this);
        progrDialog.setMessage("Cargando...");
        progrDialog.show();

        webView = (android.webkit.WebView) findViewById(R.id.winero_webview);
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(android.webkit.WebView view, String url) {
                progrDialog.dismiss();
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setDatabasePath("Conti");
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(this, "android");
        webView.loadUrl("http://nightly-winero.herokuapp.com" + url.substring(20,url.length()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


