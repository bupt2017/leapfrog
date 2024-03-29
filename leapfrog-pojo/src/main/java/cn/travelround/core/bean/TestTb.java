package cn.travelround.core.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jiyuan on 2019/3/8.
 */
public class TestTb implements Serializable {

    private static final long SerialVersionUID = 1L;

    private Integer id;
    private String name;
    private Date birthday;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "TestTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
