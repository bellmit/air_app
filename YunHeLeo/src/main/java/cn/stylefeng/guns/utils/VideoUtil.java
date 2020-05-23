package cn.stylefeng.guns.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

public class VideoUtil {

    //账号AccessKey信息请填写(必选)  LTAI4Fcpmj437CLu3fYVD3gm
    private static String access_key_id = "LTAI4FgFCe1fEaUBL7fNSJnj";
    //账号AccessKey信息请填写(必选)
    private static String access_key_secret = "IsvdGXmFh3Vwi3338b7mWchDqAt749";
    //STS临时授权方式访问时该参数为必选，使用主账号AccessKey和RAM子账号AccessKey不需要填写
    private static String security_token = "";
    //VOD API 的服务接入地址为：vod.{ApiRegion}.aliyuncs.com，{ApiRegion}请替换成对应API接入区域的标识
    private final static String VOD_DOMAIN = "http://vod.cn-shanghai.aliyuncs.com";

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client, String VideoId) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(VideoId);
        return client.getAcsResponse(request);
    }

}
