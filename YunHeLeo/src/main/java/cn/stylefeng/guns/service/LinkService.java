package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojo.Link;
import cn.stylefeng.guns.pojos.LinkDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LinkService {
    void addVideo(String classRowGuid, String userId, Link link, HttpServletRequest request);
    List<LinkDetails> findLinkId(String rowGuid);

    LinkDetails findByVideo(String rowguid);

    void updateVideo(String rowguid, String userId, String videoUrl);

    void downVideo(String rowguid);

    void upVideo(String rowguid);
}
