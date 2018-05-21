package cn.dlbdata.dj.thirdparty.mp.sdk.model.wxdto.passive;

/**
 * 被动消息
 * 
 * @author Administrator
 *
 */
public abstract class PassiveBaseMessage {
	
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	
	// 开发者微信号
	private String FromUserName;
	
	// 消息创建时间 （整型）
	private String CreateTime;
	
	// 消息类型（text/music/news）
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
}