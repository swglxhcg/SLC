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
		
		/*find view by id-begin*/
		edt_addr1=(EditText)findViewById(R.id.addrEdittext1);
		edt_addr2=(EditText)findViewById(R.id.addrEdittext2);
		btn_enter1=(Button)findViewById(R.id.enterButton1);
		btn_enter2=(Button)findViewById(R.id.enterButton2);
		btn_enter1.setOnClickListener(this);
		btn_enter2.setOnClickListener(this);
		wv1=(WebView)findViewById(R.id.mainWebView1);
		wv2=(WebView)findViewById(R.id.mainWebView2);
		/*find view by id-end*/
		
		wv1.loadUrl("http://www.baidu.com");
		wv2.loadUrl("http://www.baidu.com");
		
		//系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
		wv1.setWebViewClient(new WebViewClient(){
		     @Override
			 public boolean shouldOverrideUrlLoading(WebView view, String url) {
				 view.loadUrl(url);
				 return true;
			 }});
		wv2.setWebViewClient(new WebViewClient(){
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;
				}});
		
		WebSettings wset1=wv1.getSettings();
		WebSettings wset2=wv2.getSettings();
		String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36";
		wset1.setUserAgentString(ua);
		wset2.setUserAgentString(ua);
		wset1.setJavaScriptEnabled(true);
		wset2.setJavaScriptEnabled(true);
		wset1.setJavaScriptCanOpenWindowsAutomatically(true);
		wset2.setJavaScriptCanOpenWindowsAutomatically(true);
		
		
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
				if(wv1.canGoBack()){
					wv1.goBack();
				}
				break;
			case R.id.back2:
				if(wv2.canGoBack()){
					wv2.goBack();
				}
				break;
			case R.id.forw1:
				if(wv1.canGoForward()){
					wv1.goForward();
				}
				break;
			case R.id.forw2:
				if(wv2.canGoForward()){
					wv2.goForward();
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
