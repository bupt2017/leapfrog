package cn.travelround;

import cn.travelround.core.bean.TestTb;
import cn.travelround.core.dao.TestTbDao;
import cn.travelround.core.service.TestTbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by jiyuan on 2019/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:application-context.xml"})
public class TestTbTest {

    @Autowired
    private TestTbDao testTbDao;

    @Autowired
    private TestTbService testTbService;

    @Test
    public void testAdd(){
        TestTb testTb = new TestTb();
        testTb.setName("ZQQ");
        testTb.setBirthday(new Date());

        //testTbDao.insertTestTb(testTb);
        testTbService.insertTestTb(testTb);




    }
}
