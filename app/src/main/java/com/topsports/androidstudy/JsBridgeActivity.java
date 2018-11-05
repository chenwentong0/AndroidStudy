package com.topsports.androidstudy;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.topsports.androidstudy.jsbridge.IJsBridge;
import com.topsports.androidstudy.jsbridge.MyJsBridge;

/**
 *
 */
public class JsBridgeActivity extends AppCompatActivity implements IJsBridge{

    private WebView mWebview;
    private TextView mTvShow;
    private Handler mHandler = new Handler();
    private Button mBtn;
    private String[] mStrings = new String[]{"aaa", "bbb","ccc"};
    private EditText mEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_bridge);
        initView();
    }

    private void initView() {
        mWebview = findViewById(R.id.webview);
        mTvShow = findViewById(R.id.tv_show);
        mBtn = findViewById(R.id.btn);
        mEdittext = findViewById(R.id.edit_text);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = mEdittext.getText().toString();
                mWebview.loadUrl("javascript:callJS("+mStrings+")");
            }
        });
        WebSettings settings = mWebview.getSettings();
        //设置js可用
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            mWebview.addJavascriptInterface(new MyJsBridge(this), "myluncher");
        }
        mWebview.loadUrl("file:///android_asset/myjs.html");
        //设置是否开启浏览器调试模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    @Override
    public void setTextValue(final String value) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTvShow.setText(value);
            }
        });
    }
}
