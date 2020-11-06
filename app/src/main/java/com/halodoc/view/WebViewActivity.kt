package com.halodoc.view

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.halodoc.R
import com.halodoc.utils.AppConstant
import kotlinx.android.synthetic.main.activity_webview.*


class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val url = intent?.extras?.getString(AppConstant.KEY_INTENT_DATA)
        initView(url)
    }

    private fun initView(url: String?) {
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.builtInZoomControls = false
        webView.settings.setSupportZoom(false)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.allowFileAccess = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = MyWebViewClient(progressBar)
        webView.settings.userAgentString = "Android WebView"
        if (url != null) {
            webView.loadUrl(url)
        }
    }

    class MyWebViewClient(loader: ProgressBar) : WebViewClient() {
        var progressBar = loader

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun onPageStarted(
            view: WebView?, url: String, favicon: Bitmap?
        ) {
            progressBar.visibility = View.VISIBLE
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String) {
            progressBar.visibility = View.GONE
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, er: SslError?) {
            handler.proceed()
        }
    }
}
