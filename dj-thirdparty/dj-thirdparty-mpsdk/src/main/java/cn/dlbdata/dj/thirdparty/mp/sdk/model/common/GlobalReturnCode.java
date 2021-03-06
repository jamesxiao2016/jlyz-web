package cn.dlbdata.dj.thirdparty.mp.sdk.model.common;



public class GlobalReturnCode {
	
	public static String getGlobalReturnMsg(int code, ConstantUtil.MOBILE_TYPE mobileType ) {
		
		switch (mobileType) {
			case wx:
				return getGlobalReturnMsgWx(code);
			case ali:
				return getGlobalReturnMsgAli(code);
	
			default:
				return "移动类型不确定或不存在！mobileType:"+mobileType.toString();
		}
	}
	
	private static String getGlobalReturnMsgWx(int code) {
		switch (code) {
			case -1:
				return "系统繁忙！";
			case 0:
				return "系统繁忙！";
			case 40001:
				return "获取access_token时AppSecret错误，或者access_token无效！";
			case 40002:
				return "不合法的凭证类型！";
			case 40003:
				return "不合法的OpenID！";
			case 40004:
				return "不合法的媒体文件类型！";
			case 40005:
				return "不合法的文件类型！";
	
			default:
				return "code不确定或不存在！";
		}
	}
	
	private static String getGlobalReturnMsgAli(int code) {
		return null;
	}
	
}

