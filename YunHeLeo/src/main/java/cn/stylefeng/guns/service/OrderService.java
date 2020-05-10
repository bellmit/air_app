package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojos.UOCCP;
import com.github.pagehelper.Page;

public interface OrderService {
    Page<UOCCP> findAll(int page, int size, String query);
    int count();
}
