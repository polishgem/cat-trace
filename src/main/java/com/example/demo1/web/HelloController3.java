package com.example.demo1.web;

import com.dianping.cat.Cat;
import com.example.demo1.MyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController3 {

    Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/test3")
    public String hello(String root, String parent, String child) {
        logger.info("root : "+root);
        logger.info("parent : "+parent);
        logger.info("child : "+child);

        Cat.Context context = new MyContext();
        context.addProperty(Cat.Context.CHILD, child);
        context.addProperty(Cat.Context.PARENT, parent);
        context.addProperty(Cat.Context.ROOT, root);

        Cat.logRemoteCallServer(context);


        return "Hello 3";
    }

}
