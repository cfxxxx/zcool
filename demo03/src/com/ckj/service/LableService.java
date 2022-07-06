package com.ckj.service;

import com.ckj.dao.LableDAO;
import com.ckj.pojo.Lable;

import java.util.List;

public class LableService {
    LableDAO lableDAO = new LableDAO();

    public Lable getLableById(int id) {
        return lableDAO.querySingle("select * from data02 where id = ? ", Lable.class, id);
    }

    public List<Lable> orderByView() {
        return lableDAO.queryMulti("select name,theme,view,common,good,src from data02 order by view desc limit 10", Lable.class);
    }

    public List<Lable> orderByGood() {
        return lableDAO.queryMulti("select name,theme,view,common,good,src from data02 order by good desc limit 10", Lable.class);
    }

    public List<Lable> orderByDegree() {
        return lableDAO.queryMulti("select name,theme,view,common,good,src from data02 order by good*0.49+common*0.49+view*0.02 desc limit 10", Lable.class);
    }
}
