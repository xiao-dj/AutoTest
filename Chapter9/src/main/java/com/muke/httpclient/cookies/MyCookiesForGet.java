package com.muke.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        HttpGet get = new HttpGet(this.url + bundle.getString("getCookies.uri"));

        cookieStore = new BasicCookieStore();

        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

        HttpResponse response = client.execute(get);

        //List cookiesList= response.getFirstHeader("cookies");

        result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);

        List<Cookie> cookieList = cookieStore.getCookies();

        for (Cookie cookie: cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();

            System.out.println("cookie name= " + name + "\n" + "cookie value= " + value);

        }


    }



    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {

        HttpGet get = new HttpGet(this.url + bundle.getString("test.get.with.cookies"));

        HttpClient client = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = client.execute(get);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode= " + statusCode);

        if(statusCode == 200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");

            System.out.println(result);

        }

    }

    @Test(dependsOnMethods = "testGetCookies")
    public void testPostMethod() throws IOException {

        //HttpClient client = HttpClientBuilder.create().build();
        HttpClient client = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
        HttpPost post = new HttpPost(this.url + bundle.getString("test.post.with.cookies"));

        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);


        JSONObject resultJson = new JSONObject(result);

        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");

        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);


    }
}
