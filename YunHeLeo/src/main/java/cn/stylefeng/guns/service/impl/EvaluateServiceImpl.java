package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.EvaluateDao;
import cn.stylefeng.guns.pojos.Evaluate;
import cn.stylefeng.guns.service.EvaluateService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateDao evaluateDao;

    @Override
    public List<Evaluate> findAll(int page, int size, String query) {
        PageHelper.startPage(page, size);
        Evaluate evaluate = new Evaluate();
        evaluate.settUsername(query);
        evaluate.settCourseName(query);
        evaluate.settMobile(query);
        List<Evaluate> all = evaluateDao.findAll(evaluate);
        System.out.println(all);
        return all;
    }

    @Override
    public int count() {
        return evaluateDao.count();
    }
}
