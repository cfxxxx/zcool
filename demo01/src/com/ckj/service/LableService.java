package com.ckj.service;

import com.ckj.bean.Lable;
import com.ckj.dao.LableDAO;

public class LableService {
    LableDAO lableDAO = new LableDAO();

    public Lable getLableById(int id) {
        return lableDAO.querySingle("select * from info where id = ?", Lable.class, id);
    }
}
