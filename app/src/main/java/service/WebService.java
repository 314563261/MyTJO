package service;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import entity.Response;
import utils.JsonUtil;

/**
 * Created by wei on 2017/12/25.
 */

public class WebService {


    /**
     * Created by xy on 2016/4/10.
     */

        private final static String TAG = "WebService";
        /**服务器接口根路径*/
        public final static String WEB_ROOT = "http://technicaltesting.herokuapp.com/api/v1/city_guides";

        /**
         * 解析服务器返回的JSON数据
         * @param content JSON数据
         * @return Response对象
         */
        private static Response parseResponse(String content) {
            Log.e(TAG, "state======" + content);
            if (TextUtils.isEmpty(content)) {
                return null;
            }
            return JsonUtil.getEntity(content, Response.class);
        }

        /**
         * 得到服务器返回的输入流数据
         * @param path 请求路径
         * @param map 包含密文的map集合
         * @return 服务器返回的数据
         */
        public static InputStream connection(String path, Map<String, String> map) {
            try {
                String pathUrl = path;
                URL url = new URL(pathUrl);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                StringBuffer sb = new StringBuffer();
                if (map != null) {
                    if (!map.isEmpty()) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), "UTF-8")).append('&');
                        }
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
                byte[] entityData = sb.toString().getBytes();
                httpConn.setDoOutput(true);
                httpConn.setDoInput(true);
                httpConn.setUseCaches(false);
                httpConn.setRequestMethod("POST");
                //设置请求服务器连接的超时时间
                httpConn.setConnectTimeout(5 * 1000);
                //设置服务器返回数据的超时时间
//                httpConn.setReadTimeout(30 * 1000);
                httpConn.setRequestProperty("Content-length", "" + entityData.length);
                httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStream outStream = httpConn.getOutputStream();
                outStream.write(entityData);
                outStream.flush();
                outStream.close();
                int responseCode = httpConn.getResponseCode();
                if (HttpURLConnection.HTTP_OK == responseCode) {
                    InputStream is = httpConn.getInputStream();
                    return is;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
            return null;
        }

        /**
         * 将服务器返回的输入流转换为字符串
         * @param is 服务器返回的输入流
         * @return 输入流转换之后的字符串
         */
        public static String getStringFromIS(InputStream is) {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String reString = new String(os.toByteArray());

            Log.e(TAG, "geStringFromIS reString======" + reString);

            return reString;
        }
    public static String connection() {
        String mStrContent = "";
        StringBuilder resultData = new StringBuilder("");
        URL url = null;
        try {
            url = new URL(WEB_ROOT);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            InputStreamReader isr = new InputStreamReader(urlConn.getInputStream());
            //使用缓冲一行行的读入，加速InputStreamReader的速度
            BufferedReader buffer = new BufferedReader(isr);
            String inputLine = null;

            while((inputLine = buffer.readLine()) != null){
                resultData.append(inputLine);
                resultData.append("\n");
            }
            buffer.close();
            isr.close();
            urlConn.disconnect();
            mStrContent = resultData.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mStrContent;
    }
}
