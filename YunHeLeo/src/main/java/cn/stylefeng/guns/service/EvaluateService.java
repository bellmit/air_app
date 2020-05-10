package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojos.Evaluate;

import java.util.List;

public interface EvaluateService {
    List<Evaluate> findAll(int page, int size, String query);
    int count();
}
