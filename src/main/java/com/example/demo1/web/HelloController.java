package com.example.demo1.web;

import com.dianping.cat.Cat;
import com.example.demo1.MyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class HelloController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/test1")
    public String hello() {
        Cat.Context context = new MyContext();
        Cat.logRemoteCallClient(context);

        String root = context.getProperty(Cat.Context.ROOT);
        String parent = context.getProperty(Cat.Context.PARENT);
        String child = context.getProperty(Cat.Context.CHILD);

        logger.info("client context root : "+context.getProperty(Cat.Context.ROOT));
        logger.info("client context parent : "+context.getProperty(Cat.Context.PARENT));
        logger.info("client context child : "+context.getProperty(Cat.Context.CHILD));


        String param = "?root="+root+"&parent="+parent+"&child="+child;

        HttpURLConnection urlConnection = null;
        try {
            //创建其对象
            urlConnection = (HttpURLConnection) new URL("http://localhost:8082/test2"+param).openConnection();

            //设置连接时间，10秒
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(10 * 1000);

            //数据编码格式，这里utf-8
            urlConnection.setRequestProperty("Charset", "utf-8");

            //设置返回结果的类型，这里是json
            urlConnection.setRequestProperty("accept", "application/json");

            //这里设置post传递的内容类型，这里json
            urlConnection.setRequestProperty("Content-Type", "application/json");

            logger.info("result : " + urlConnection.getResponseMessage());

            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return "Hello 1";
    }

}
