package com;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @ClassName HttpClientTest
 * @Author shuai
 * @create 2023/8/2 18:54
 * @Instruction 测试httpClient的使用
 */

@SpringBootTest
public class HttpClientTest {

    //Post http://localhost:8080/admin/employee/login 参数username,password {"username":"admin","password":"123456"}
    @Test
    public void testPost() throws IOException {

        //1. 构造httpClient对象
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //2.构造http请求对象HttpPost
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");
        HttpEntity httpEntity=new StringEntity("{\"username\":\"admin\",\"password\":\"123456\"}"); //此时还是文本
        httpPost.setEntity(httpEntity);//请求体
        httpPost.setHeader("Content-Type","application/json");//请求头

        //3.发送请求
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

        //4. 获取响应结果
        System.out.println(response.getStatusLine());//获取响应行状态码
        HttpEntity entity = response.getEntity();//响应体
        String result = EntityUtils.toString(entity);//将响应体内容转成String
        System.out.println(result);

        //5.释放资源
        response.close();
        closeableHttpClient.close();
    }

    @Test
    public void testGet() throws IOException {

        //1。构造httpClient对象
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //2.创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/admin/category/page?page=1&pageSize=5");
        httpGet.setHeader("token","eyJhbGciOiJIUzI1NiJ9.eyJlbXBJZCI6MSwiZXhwIjoxNjkwOTc2MTE0fQ.M-DsqB2-eRifV5xcopQ3VJ7E_fvFUSPHnRfX62d56cQ");//设置请求头

        //3.发送请求头
        CloseableHttpResponse response= closeableHttpClient.execute(httpGet);

        //4. 获取响应结果
        System.out.println(response.getStatusLine());//获取响应行状态码
        HttpEntity entity = response.getEntity();//响应体
        String result = EntityUtils.toString(entity);//将响应体内容转成String
        System.out.println(result);

        //5.释放资源
        response.close();
        closeableHttpClient.close();
    }
}
