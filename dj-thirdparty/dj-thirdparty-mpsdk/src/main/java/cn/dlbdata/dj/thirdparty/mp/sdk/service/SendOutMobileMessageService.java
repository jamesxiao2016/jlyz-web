package cn.dlbdata.dj.thirdparty.mp.sdk.service;

import java.util.Map;

/**
 * @Description: 自定义菜单服务
 */
public interface SendOutMobileMessageService {

    /**
     * 群发发送文本消息
     *
     * @param map
     * @param content
     * @return
     */
    String sendOutTextGroupMessage(Map<String, String> map, String content);

    /**
     * 群发图片消息
     *
     * @param map
     * @param pid
     * @return
     */
    String sendOutImageGroupMessage(Map<String, String> map, String pid);

    /**
     * 群发送图文消息
     *
     * @param map
     * @param activeArticleid
     * @return
     */
    String sendOutNewsGroupMessage(Map<String, String> map, String activeArticleid);
}
