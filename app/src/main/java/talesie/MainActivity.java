/*
*  Create by Tales on 20/09/2017
*
* */
package talesie;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nabilfreeman.androidstudiowebviewboilerplate.R;

public class MainActivity extends Activity {

    //declaration
    private WebView webview;
    private String url;
    private ProgressDialog prDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webview);
        url = "https://infocos.com.br/minhaconta";
        prDialog = ProgressDialog.show(MainActivity.this, null, "Conectando...",true, false);

        //this setting here is to prevent the Webview from opening links in a new window.
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                prDialog.show();
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webview.loadUrl("file:///android_asset/web_content/index.html");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                prDialog.dismiss();
                if(prDialog.isShowing())
                    prDialog.dismiss();
            }

        });
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        //webview.loadUrl("file:///android_asset/web_content/offline.html");
        webview.loadUrl(url);

    }
}
