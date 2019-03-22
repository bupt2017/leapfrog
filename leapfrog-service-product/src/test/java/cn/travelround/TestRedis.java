package cn.travelround;

import cn.travelround.core.bean.TestTb;
import cn.travelround.core.dao.TestTbDao;
import cn.travelround.core.service.TestTbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * Created by jiyuan on 2019/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:application-context.xml"})
public class TestRedis {

    @Autowired
    private Jedis jedis;
    @Test
    public void testSpringJedis() throws Exception{
        jedis.set("ooo", "aaa");
    }

    @Test
    public void testRedis() throws Exception{
        Jedis jedis = new Jedis("192.168.177.129", 6379);
        Long pno = jedis.incr("pno");
        System.out.println("服务器返回的值：" + pno);
        jedis.close();
    }

}
