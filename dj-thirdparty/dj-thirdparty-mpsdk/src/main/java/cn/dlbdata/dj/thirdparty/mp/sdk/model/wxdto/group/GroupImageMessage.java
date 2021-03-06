package cn.dlbdata.dj.thirdparty.mp.sdk.model.wxdto.group;


import cn.dlbdata.dj.thirdparty.mp.sdk.model.common.ConstantUtil;

/**
 * 主动文本消息
 * 
 */
public class GroupImageMessage extends GroupBaseMessage {
	
	// 回复的消息内容
	private GroupImage image;

	public GroupImageMessage() {
		this.setMsgtype(ConstantUtil.WX_MSG_TYPE.image.toString());
	}

	public GroupImage getImage() {
		return image;
	}

	public void setImage(GroupImage image) {
		this.image = image;
	}

}
