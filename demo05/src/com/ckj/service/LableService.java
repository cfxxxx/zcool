package com.ckj.service;

import com.ckj.pojo.Lable;
import com.ckj.reids.Redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LableService {
    Redis redis = new Redis();
    List<Lable> lables = new ArrayList<>();

    public List<Lable> getLableByTheme(String theme) {
        Set<String> keys = redis.themeKeys(theme);
        for (String key : keys) {
            addLable(key);
        }
        return lables;
    }

    public List<Lable> getLableByName(String name) {
        Set<String> keys = redis.nameKeys(name);
        for (String key : keys) {
            addLable(key);
        }
        return lables;
    }

    public List<Lable> orderByView() {
        Set<String> indexSortByView = redis.zrange("indexSortByView", -11, -1);
        return getLables(indexSortByView);
    }

    public List<Lable> orderByGood() {
        Set<String> indexSortByGood = redis.zrange("indexSortByGood", -11, -1);
        return getLables(indexSortByGood);
    }

    private List<Lable> getLables(Set<String> indexSortByGood) {
        for (String img_name : indexSortByGood) {
            String key = "dataClassByTheme:" + img_name;
            addLable(key);
        }
        return lables;
    }

    private void addLable(String key) {
        List<String> values = redis.hmget(key, "img_name", "theme", "view", "common", "good", "img_src");
        Lable lable = new Lable();
        lable.setName(values.get(0));
        lable.setTheme(values.get(1));
        lable.setView(Integer.parseInt(values.get(2)));
        lable.setCommon(Integer.parseInt(values.get(3)));
        lable.setGood(Integer.parseInt(values.get(4)));
        lable.setSrc(values.get(5));
        lables.add(lable);
    }

    public List<Lable> orderByDegree() {
        Set<String> indexSortByDegree = redis.zrange("indexSortByDegree", -11, -1);
        return getLables(indexSortByDegree);
    }
}
