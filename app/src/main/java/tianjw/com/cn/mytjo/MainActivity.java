package tianjw.com.cn.mytjo;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import entity.MyList;
import entity.Response;
import fragment.HomeFragmentActivity;
import service.WebService;
import utils.JsonUtil;
import utils.NetworkUtil;


public class MainActivity extends Activity {
    private NetworkUtil networkUtil;
    private TextView tv;
    private String stringFromIS;
//    private WebService webService;
    private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 1:
                List<Response> response = JsonUtil.getResponseList(stringFromIS, "response");
                MyList list = new MyList();
                list.setList(response);
                Intent intent = new Intent(MainActivity.this, HomeFragmentActivity.class);
                intent.putExtra("list", list);
                startActivity(intent);
                break;
        }

    }
}   ;
    private List<Response> entityList;
    private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv_name);

    }

    private void initData() {
        if (null == networkUtil){
            networkUtil = new NetworkUtil();
        }
        if (networkUtil.isNetworkAvailable(this))
        {
//            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_LONG).show();
//            InputStream connection = WebService.connection(WebService.WEB_ROOT, null);
//            String stringFromIS = WebService.getStringFromIS(connection);
//            Toast.makeText(getApplicationContext(), stringFromIS, Toast.LENGTH_LONG).show();
            //                    entityList = JsonUtil.getEntityList(stringFromIS,Response.class);
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    stringFromIS = WebService.connection();
//                    entityList = JsonUtil.getEntityList(stringFromIS,Response.class);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }
            });
            thread.start();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_LONG).show();
        }
    }

    private void initEvent() {

    }

}
