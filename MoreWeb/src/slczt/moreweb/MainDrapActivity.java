package slczt.moreweb;

import android.app.Activity;

import android.content.Context;

import android.os.Bundle;

import android.util.Log;

import android.view.Menu;

import android.view.MotionEvent;

import android.view.View;

import android.view.WindowManager;

import android.widget.TextView;
import android.webkit.*;
import android.widget.*;
import android.view.View.*;

/**

 * 实现Activity悬浮和拖拽

 * @author rwl_bjb

 *

 */

public class MainDrapActivity extends Activity
{


	String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36";
	
    int mActivityWindowWidth = 600; //activity宽度

    int mActivityWindowHeight = 700; //activity高度

    int width = 0; //屏幕宽度

    int height = 0; //屏幕高度



    private TextView titleDrag = null;

    private float x;

    private float y;

    private float startX;

    private float startY;
	
	private WebView wv;
	private Button btn,gbtn;;
	private EditText et;
	private TextView tv;



    private View view;

    private WindowManager.LayoutParams lp;



    private String TAG = MainDrapActivity.class.getName();



    @Override

    protected void onCreate(Bundle savedInstanceState)
	{

        super.onCreate(savedInstanceState);

        setContentView(R.layout.d_main);

		wv=(WebView)findViewById(R.id.dmainWebView);
		btn=(Button)findViewById(R.id.dmainEnterButton);
		et=(EditText)findViewById(R.id.editText1);
		tv=(TextView)findViewById(R.id.title_drag);
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				switch(v.getId()){
					case R.id.dmainEnterButton:
						wv.loadUrl(et.getText().toString());
						WebSettings wvs=wv.getSettings();
						wvs.setUserAgentString(ua);
						webviewsettingYiTiaoLong(wvs);
						break;
				}
			}
		});

        titleDrag = (TextView)findViewById(R.id.title_drag);



        titleDrag.setOnTouchListener(new View.OnTouchListener() {



				@Override

				public boolean onTouch(View arg0, MotionEvent event)
				{

					x = event.getRawX();     

					y = event.getRawY();  

					Log.d(TAG, "------X: " + x + "------Y:" + y);  

					switch (event.getAction())
					{  

						case MotionEvent.ACTION_DOWN:  

							startX = event.getX();  

							startY = event.getY();  

							break;  

						case MotionEvent.ACTION_MOVE:  

							updatePosition();  

							break;  

						case MotionEvent.ACTION_UP:  

							updatePosition();  

							startX = startY = 0;  

							break;  

					}  

					return true; 

				}

			});



    }



    private void updatePosition()
	{  

        // View的当前位置  

        int xmove = (int)(x - startX);  

        int ymove = (int) (y - startY);

        Log.i(TAG, "计算位置:" + xmove + " " + ymove);



        lp.x = mActivityWindowWidth / 2 - width / 2 + xmove;

        lp.y = mActivityWindowHeight / 2 - height / 2 + ymove;



        getWindowManager().updateViewLayout(view, lp);



    }





    @Override

    public void onAttachedToWindow()
	{

        // TODO Auto-generated method stub



        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();

        height = wm.getDefaultDisplay().getHeight();



        super.onAttachedToWindow();

        view = getWindow().getDecorView();

        lp = (WindowManager.LayoutParams) view.getLayoutParams();



        //lp.gravity = Gravity.CENTER;

        //lp.gravity = Gravity.TOP;



        lp.width = mActivityWindowWidth;

        lp.height = mActivityWindowHeight;

        lp.x = mActivityWindowWidth / 2 - width / 2;

        lp.y = mActivityWindowHeight / 2 - height / 2;



        getWindowManager().updateViewLayout(view, lp);

    }





    @Override

    public boolean onCreateOptionsMenu(Menu menu)
	{

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.d_main, menu);

        return true;

    }
	public void WebViewBackOrForward(WebView webview,int action,String title){
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
	public void webviewsettingYiTiaoLong(WebSettings webSettings){
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
