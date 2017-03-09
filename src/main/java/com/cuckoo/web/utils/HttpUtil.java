package com.cuckoo.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;

/**
 * Created by tanmq on 2017/3/9.
 */
public class HttpUtil {

    private static HttpClient client = new DefaultHttpClient();


    public static JSONObject doPost(JSONObject object, String url, String authCode, String date) throws Exception{
        HttpPost post = new HttpPost(url);
        if (authCode != null && date != null) {
            post.setHeader("Authorization", authCode);
            post.setHeader("Date", date);
        }

        if (object != null) {
            post.setHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(object.toJSONString());
            post.setEntity(entity);
        }

        HttpResponse response = client.execute(post);
        HttpEntity en = response.getEntity();
        return JSON.parseObject(EntityUtils.toString(en));
    }


    private static String buildAuthCode(long uid, int device, String date, String sessionId) {
        String str = String.valueOf(uid) + String.valueOf(device);
        str += date + sessionId;

        return String.valueOf(device) + ":" + String.valueOf(uid) + ":" + EncodeUtil.MD5(str);
    }


    private static String sessionId = "5fe94d201ef0529a89a17b1f73673c03";
    private static long uid = 5;

    private static int device = 0;

    private static String date = String.valueOf(System.currentTimeMillis());

    private static String authCode = buildAuthCode(uid, device, date, sessionId);

    public static void main(String args[]) throws Exception{



//     login();
//   loginOut();
//        follow();
//        unFollow();
//        publishFeed();
//        timeline();
//        list();
        feedDetail();
    }

    private static void feedDetail() throws Exception {
        String url = "http://localhost:9000/api/v1/feed/detail";
        JSONObject object = new JSONObject();
        object.put("fid", 7);

        JSONObject ret = doPost(object, url, authCode, date);
        System.out.println(ret.toJSONString());
    }

    private static void list() throws Exception{
        String url = "http://localhost:9000/api/v1/feed/list";
        JSONObject object = new JSONObject();
        object.put("page", 1);
        object.put("size", 10);

        JSONObject ret = doPost(object, url, authCode, date);
        System.out.println(ret.toJSONString());
    }

    private static void timeline() throws Exception{
        String url = "http://localhost:9000/api/v1/feed/timeline";
        JSONObject object = new JSONObject();
        object.put("page", 1);
        object.put("size", 10);

        JSONObject ret = doPost(object, url, authCode, date);
        System.out.println(ret.toJSONString());
    }


    private static void publishFeed() throws Exception{
        String url = "http://localhost:9000/api/v1/feed/publish";
        String date = String.valueOf(System.currentTimeMillis());

        JSONObject object = new JSONObject();
        JSONArray content = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("img", "http://www.baidu.com");
        content.add(item);
        object.put("content", content);
        object.put("title", "test2");
        object.put("coverImg", "http://www.baidu.com");
        object.put("desc", "some description");

        String authCode = buildAuthCode(uid, device, date, sessionId);
        JSONObject ret = doPost(object, url, authCode, date);
        System.out.println(ret.toJSONString());
    }


    private static void follow() throws Exception{
        String url = "http://localhost:9000/api/v1/friend/follow";
        JSONObject object = new JSONObject();
        object.put("follow_uid", 6);
        String date = String.valueOf(System.currentTimeMillis());

        long uid = 5;
        int device = 0;
        String authCode = buildAuthCode(uid, device, date, sessionId);
        JSONObject ret = doPost(object, url, authCode, date);
        System.out.println(ret.toJSONString());

    }

    private static void unFollow() throws Exception{
        String url = "http://localhost:9000/api/v1/friend/unFollow";
        JSONObject object = new JSONObject();
        object.put("follow_uid", 6);

        String date = String.valueOf(System.currentTimeMillis());

        long uid = 5;
        int device = 0;
        String authCode = buildAuthCode(uid, device, date, sessionId);
        JSONObject ret = doPost(object, url, authCode, date);
        System.out.println(ret.toJSONString());
    }


    private static void login() throws Exception{
        String password = "123456";
        String account  = "15902098345";
        String loginUrl = "http://localhost:9000/api/v1/account/signIn";

        JSONObject login = new JSONObject();
        login.put("password", password);
        login.put("account", account);

        JSONObject ret = doPost(login, loginUrl,null, null);
        System.out.println(ret.toJSONString());
    }

    private static void loginOut() throws Exception{
        String date = String.valueOf(System.currentTimeMillis());

        String authCode = buildAuthCode(uid, device, date, sessionId);
        String logoutUrl = "http://localhost:9000/api/v1/account/signOut";

        JSONObject ret = doPost(null, logoutUrl, authCode, date);
        System.out.println(ret.toJSONString());
    }




}
