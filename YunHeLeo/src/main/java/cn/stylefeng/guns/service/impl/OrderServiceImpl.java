package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.OrderDao;
import cn.stylefeng.guns.pojos.UOCCP;
import cn.stylefeng.guns.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Page<UOCCP> findAll(int page, int size, String query) {
        PageHelper.startPage(page,size);
        UOCCP uoccp = new UOCCP();
        uoccp.settMobile(query);
        uoccp.settUsername(query);
        uoccp.settOrderNo(query);
        Page<UOCCP> uoccps = orderDao.findAll(uoccp);
        System.out.println(uoccps);
        return uoccps;
    }

    @Override
    public int count() {
        return orderDao.count();
    }
}
