package ren.yale.android.testlocalhostserverdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import ren.yale.android.testlocalhostserverdemo.R;



import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * create by shenqinwei on 2023/10/10
 * tip:
 */
public class FirstMainActivity  extends Activity {
    private WebView webView;
    private LocalhostServer localhostServer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        localhostServer = new LocalhostServer();
     Button btn = findViewById(R.id.btn);
     btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        webView.loadUrl("http://127.0.0.1:8080/files/article/1/1268/5028890.html");
       // webView.loadUrl("http://127.0.0.1:8080/v2/contents/2fe568cb-8f40-4fa6-8d7c-60a8a92ebe41#/");
    }
});
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
       // webView.loadUrl("http://localhost:8080");

       // webView.loadUrl("http://10.23.5.203:18811/v2/contents/5016a24e-093f-4b65-84bb-e1a7d7ffb2f4#/");
        // 启动 LocalhostServer
       try {
            localhostServer.start();
            System.out.println("Server started on port 8080");
        } catch (IOException e) {
            e.printStackTrace();
        }
       webView.setWebViewClient(new WebViewClient(){
           @Nullable
           @Override
           public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
               System.out.println("11qian===---===:"+request.getUrl().toString());
               return super.shouldInterceptRequest(view, request);
           }
       });



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 停止 LocalhostServer
        if (localhostServer != null) {
            localhostServer.stop();
        }
    }
}
