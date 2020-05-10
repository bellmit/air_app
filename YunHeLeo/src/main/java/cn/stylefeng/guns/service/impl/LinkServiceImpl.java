package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.LinkDao;
import cn.stylefeng.guns.dao.LinkDetailsDao;
import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.pojo.Link;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojos.LinkDetails;
import cn.stylefeng.guns.service.LinkService;
import cn.stylefeng.guns.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private LinkDetailsDao linkDetailsDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public void addVideo(String classRowGuid, String userId, Link link, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        String uid = (String) request.getSession().getAttribute("id");

        String id = idWorker.nextId()+"";
        link.setRowGuid(id);
        link.settClassId(classRowGuid);
        link.settUpdateDate(date);
        if (link.gettLinkName().equals("1"))
            link.settLinkName("预习");
        if (link.gettLinkName().equals("2"))
            link.settLinkName("上课");
        if (link.gettLinkName().equals("3"))
            link.settLinkName("作品");
        link.settVideoUrl(link.gettVideoUrl());
        if (userId!=null) {
            link.settCreateMan(userId);
        } else {
            link.settCreateMan(uid);
        }

        link.settStatus(0); // 状态 0 已发布 1未发布 2已下架
        // 上课学习状态 0未开始 1已开始
        link.setStudyStatus(0);
        linkDao.insert(link);

        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            if (userId!=null) {
                log.settOperatorMan(userId);
            } else {
                log.settOperatorMan(uid);
            }
            log.settOperatorRecord("保存"+link.gettLinkName()+"视频");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据课节rowguid查询环节
     * @param rowGuid
     * @return
     */
    @Override
    public List<LinkDetails> findLinkId(String rowGuid) {
        List<LinkDetails> linkId = linkDetailsDao.findLinkId(rowGuid);
        System.out.println(linkId);
        for (LinkDetails linkDetails : linkId) {
            if (linkDetails.gettLinkName().equals("预习")) {
                linkDetails.settLinkType(1);
            } else if (linkDetails.gettLinkName().equals("上课")) {
                linkDetails.settLinkType(2);
            } else if (linkDetails.gettLinkName().equals("作品")) {
                linkDetails.settLinkType(3);
            }
        }
        return linkId;
    }

    @Override
    public LinkDetails findByVideo(String rowguid) {
        return linkDetailsDao.findByVideo(rowguid);
    }

    @Override
    public void updateVideo(String rowguid,String userId,String videoUrl) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        Link link = new Link();
        LinkDetails byVideo = linkDetailsDao.findByVideo(rowguid);
        link.settVideoUrl(videoUrl);
        link.settUpdateDate(date);
        link.settCreateMan(userId);
        link.setRowGuid(rowguid);
        linkDao.updateByVideo(link);
    }

    @Override
    public void downVideo(String rowguid) {
        linkDao.updateDownVideo(rowguid);
    }

    @Override
    public void upVideo(String rowguid) {
        linkDao.upVideo(rowguid);
    }
}
