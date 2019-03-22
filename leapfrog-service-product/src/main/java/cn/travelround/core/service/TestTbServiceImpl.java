package cn.travelround.core.service;

import cn.travelround.core.bean.TestTb;
import cn.travelround.core.dao.TestTbDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiyuan on 2019/3/8.
 */
@Service(value = "testTbService")
@Transactional
public class TestTbServiceImpl implements TestTbService{

    @Autowired
    private TestTbDao testTbDao;

    @Override
    public void insertTestTb(TestTb testTb) {
        testTbDao.insertTestTb(testTb);

        //测试事务，手动抛出异常
        //throw new RuntimeException();
    }
}
