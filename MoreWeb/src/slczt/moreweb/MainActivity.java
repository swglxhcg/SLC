package slczt.moreweb;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;

public class MainActivity extends Activity implements View.OnClickListener
{

	private EditText edt_addr1,edt_addr2;
	private Button btn_enter1,btn_enter2;
	private WebView wv1,wv2;
	private ProgressBar pb1,pb2;
	private String wvt1,wvt2;

	@Override
	public void onClick(View v)
	{
		// TODO: Implement this method
		switch(v.getId()){
			case R.id.enterButton1:
				String s1=edt_addr1.getText().toString();
				wv1.loadUrl(s1);
				break;
			case R.id.enterButton2:
				String s2=edt_addr2.getText().toString();
				wv2.loadUrl(s2);
				break;
		}
	}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		wvt1="";wvt2="";
		/*find view by id-begin*/
		edt_addr1=(EditText)findViewById(R.id.addrEdittext1);
		edt_addr2=(EditText)findViewById(R.id.addrEdittext2);
		btn_enter1=(Button)findViewById(R.id.enterButton1);
		btn_enter2=(Button)findViewById(R.id.enterButton2);
		btn_enter1.setOnClickListener(this);
		btn_enter2.setOnClickListener(this);
		wv1=(WebView)findViewById(R.id.mainWebView1);
		wv2=(WebView)findViewById(R.id.mainWebView2);
		pb1=(ProgressBar)findViewById(R.id.mainProgressBar1);
		pb2=(ProgressBar)findViewById(R.id.mainProgressBar2);
		/*find view by id-end*/
		
		wv1.loadUrl("http://www.baidu.com");
		wv2.loadUrl("http://www.baidu.com");
		
		//系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
		wv1.setWebViewClient(new WebViewClient(){
		     @Override
			 public boolean shouldOverrideUrlLoading(WebView view, String url) {
				 view.loadUrl(url);
				 edt_addr1.setText(url);
				 return true;
			 }});
		wv2.setWebViewClient(new WebViewClient(){
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					edt_addr2.setText(url);
					return true;
				}
				
				});
		
		WebSettings wset1=wv1.getSettings();
		WebSettings wset2=wv2.getSettings();
		String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36";
		wset1.setUserAgentString(ua);
		wset2.setUserAgentString(ua);
		/*wset1.setJavaScriptEnabled(true);
		wset2.setJavaScriptEnabled(true);*/
		webviewsettingYiTiaoLong(wset1);
		webviewsettingYiTiaoLong(wset2);
		wset1.setJavaScriptCanOpenWindowsAutomatically(true);
		wset2.setJavaScriptCanOpenWindowsAutomatically(true);
		
		wv1.setWebChromeClient(new WebChromeClient(){
				@Override
				public void onProgressChanged(WebView wv,int progress){
					pb1.setProgress(progress);
				}
				@Override
				public void onReceivedTitle(WebView wv,String title){
					wvt1=title;
					setTitle(wvt1+"<O|O>"+wvt2);
				}
				
		});
		wv2.setWebChromeClient(new WebChromeClient(){
				@Override
				public void onProgressChanged(WebView wv,int progress){
					pb2.setProgress(progress);
				}
				@Override
				public void onReceivedTitle(WebView wv,String title){
					wvt2=title;
					setTitle(wvt1+"<O|O>"+wvt2);
				}
				
			});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		getMenuInflater().inflate(R.menu.main,menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.back1:
				/*if(wv1.canGoBack()){
					wv1.goBack();
				}*/
				WebViewBackOrForward(wv1,0,wvt1);
				setTitle(wvt1+"<O|O>"+wvt2);
				break;
			case R.id.back2:
				/*if(wv2.canGoBack()){
					wv2.goBack();
				}*/
				WebViewBackOrForward(wv2,0,wvt2);
				setTitle(wvt1+"<O|O>"+wvt2);
				break;
			case R.id.forw1:
				/*if(wv1.canGoForward()){
					wv1.goForward();
				}*/
				WebViewBackOrForward(wv1,1,wvt1);
				setTitle(wvt1+"<O|O>"+wvt2);
				break;
			case R.id.forw2:
				/*if(wv2.canGoForward()){
					wv2.goForward();
				}*/
				WebViewBackOrForward(wv2,1,wvt2);
				setTitle(wvt1+"<O|O>"+wvt2);
				break;
		}
		edt_addr1.setText(wv1.getUrl());
		edt_addr2.setText(wv2.getUrl());
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO: Implement this method
		switch(keyCode){
			case event.KEYCODE_CTRL_LEFT:
				WebViewBackOrForward(wv1,0,wvt1);
				setTitle(wvt1+"<O|O>"+wvt2);
				
				break;
			case event.KEYCODE_ALT_LEFT:
				WebViewBackOrForward(wv1,1,wvt1);
				setTitle(wvt1+"<O|O>"+wvt2);
				break;
			case event.KEYCODE_CTRL_RIGHT:
				WebViewBackOrForward(wv2,0,wvt2);
				setTitle(wvt1+"<O|O>"+wvt2);
				break;
			case event.KEYCODE_ALT_RIGHT:
				WebViewBackOrForward(wv2,1,wvt2);
				setTitle(wvt1+"<O|O>"+wvt2);
				break;
			case event.KEYCODE_BACK:
				break;
		}
		edt_addr1.setText(wv1.getUrl());
		edt_addr2.setText(wv2.getUrl());
		return true;
	}
	private void WebViewBackOrForward(WebView webview,int action,String title){
		if(action==0){				//Webview go back.
			if(webview.canGoBack()){
				webview.goBack();
			}
			title=webview.getTitle();
		}else if(action==1){		//Webview go forward.
			if(webview.canGoForward()){
				webview.goForward();
				
			}
			title=webview.getTitle();
		}
	}
	private void webviewsettingYiTiaoLong(WebSettings webSettings){
		webSettings.setJavaScriptEnabled(true);
		//设置自适应屏幕，两者合用
		webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
		webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
		
//缩放操作
		webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
		webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
		webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件
		
//其他细节操作
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
		webSettings.setAllowFileAccess(true); //设置可以访问文件
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
		webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
		webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
		
		
	}
}
