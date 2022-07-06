package com.ckj.service;

import com.ckj.pojo.Lable;
import com.ckj.reids.Redis;

import java.util.*;

public class LableService {
    Redis redis = new Redis();
    List<Lable> lables = new ArrayList<>();

    public List<Lable> getLableSrc() {
        Set<String> indexSortByDegree = redis.zrange("indexSortByDegree", -7, -1);
        return getLables(indexSortByDegree);
    }

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
        Set<String> indexSortByView = redis.zrange("indexSortByView", -10, -1);
        List<Lable> list = getLables(indexSortByView);
        Collections.sort(list, new Comparator<Lable>() {
            @Override
            public int compare(Lable o1, Lable o2) {
                return o2.getView()-o1.getView();
            }
        });
        return list;
    }

    public List<Lable> orderByGood() {
        Set<String> indexSortByGood = redis.zrange("indexSortByGood", -10, -1);
        List<Lable> list = getLables(indexSortByGood);
        Collections.sort(list, new Comparator<Lable>() {
            @Override
            public int compare(Lable o1, Lable o2) {
                return o2.getGood()-o1.getGood();
            }
        });
        return list;
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
        Set<String> indexSortByDegree = redis.zrange("indexSortByDegree", -10, -1);
        List<Lable> list = getLables(indexSortByDegree);
        Collections.sort(list, new Comparator<Lable>() {
            @Override
            public int compare(Lable o1, Lable o2) {
                return o2.getDegree()-o1.getDegree();
            }
        });
        return list;
    }
}
