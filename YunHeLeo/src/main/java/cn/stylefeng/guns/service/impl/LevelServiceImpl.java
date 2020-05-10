package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.LevelDao;
import cn.stylefeng.guns.pojo.Level;
import cn.stylefeng.guns.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelDao levelDao;

    @Override
    public List<Level> findLevel() {
        return levelDao.findLevel();
    }
}
