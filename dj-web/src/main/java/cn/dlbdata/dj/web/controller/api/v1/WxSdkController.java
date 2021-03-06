package cn.dlbdata.dj.web.controller.api.v1;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dlbdata.dj.common.DangjianException;
import cn.dlbdata.dj.common.core.util.ConfigUtil;
import cn.dlbdata.dj.common.core.util.constant.CoreConst.ResultCode;
import cn.dlbdata.dj.common.core.web.vo.ResultVo;
import cn.dlbdata.dj.constant.DlbConstant;
import cn.dlbdata.dj.thirdparty.mp.sdk.model.access.AccessTokenResponse;
import cn.dlbdata.dj.thirdparty.mp.sdk.model.access.GetUserInfo;
import cn.dlbdata.dj.thirdparty.mp.sdk.model.access.GetaAccessTokenParam;
import cn.dlbdata.dj.thirdparty.mp.sdk.model.access.GrantType;
import cn.dlbdata.dj.thirdparty.mp.sdk.service.AccessService;
import cn.dlbdata.dj.thirdparty.mp.sdk.service.CustomMenuService;
import cn.dlbdata.dj.thirdparty.mp.sdk.service.UserInfoService;
import cn.dlbdata.dj.thirdparty.mp.sdk.util.LocalCache;
import cn.dlbdata.dj.web.base.BaseController;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/v1/wx")
public class WxSdkController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(WxSdkController.class);
	@Autowired
	private CustomMenuService customMenuService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AccessService accessService;

	@RequestMapping(value = "/createMenu", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo<Map<String, Object>> createMenu(String json) {
		ResultVo<Map<String, Object>> result = new ResultVo<Map<String, Object>>();
		try {
			customMenuService.createMenu(json);
			result.setCode(ResultCode.OK.getCode());
		} catch (DangjianException e) {
			result.setMsg(e.getErrorMsg());
			result.setCode(ResultCode.BadRequest.getCode());
		}

		return result;
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo<JSONObject> userInfo(String openid) {
		ResultVo<JSONObject> result = new ResultVo<>();
		GetUserInfo userInfo = new GetUserInfo();
		userInfo.setLang("zh_CN");
		userInfo.setOpenid(openid);

		JSONObject json = null;
		try {
			json = userInfoService.userInfo(userInfo);
		} catch (DangjianException e) {
			logger.error("", e);
			result.setMsg("数据获取异常.");
			result.setCode(ResultCode.BadRequest.getCode());
			return result;
		}
		result.setData(json);
		result.setCode(ResultCode.OK.getCode());
		return result;
	}

	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo<GetaAccessTokenParam> getToken(HttpServletRequest httpRequest, String href) {
		ResultVo<GetaAccessTokenParam> result = new ResultVo<GetaAccessTokenParam>();
		String url = "http://" + httpRequest.getServerName() + httpRequest.getContextPath()
				+ httpRequest.getServletPath();

		if (httpRequest.getQueryString() != null) {
			url += "?" + httpRequest.getQueryString();
		}

		if (href != null) {
			url = href;
		}

		GetaAccessTokenParam getaAccessTokenParam = new GetaAccessTokenParam();
		// getaAccessTokenParam.setSecret("8d72463ffdf8a2232241985b442c1c93");
		// getaAccessTokenParam.setAppid("wxef4c83c01085bb38");
		getaAccessTokenParam.setAppid(ConfigUtil.get(DlbConstant.KEY_WX_APP_ID));
		getaAccessTokenParam.setSecret(ConfigUtil.get(DlbConstant.KEY_WX_SECRET));
		getaAccessTokenParam.setGrantType(GrantType.client_credential);
		try {
			String token = LocalCache.TICKET_CACHE.getIfPresent(DlbConstant.KEY_ACCESS_TOKEN);
			if (null == token || "".equals(token)) {
				AccessTokenResponse accessTokenResponse = accessService.getAccessToken(getaAccessTokenParam);
				token = accessTokenResponse.getAccessToken();
				LocalCache.TICKET_CACHE.put(DlbConstant.KEY_ACCESS_TOKEN, token);
			}
			getaAccessTokenParam.setToken(token);
			// 获取Ticket
			String jsapi_ticket = getTicket(token);

			// 拿noncestr
			String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);// 随机字符串
			getaAccessTokenParam.setNonceStr(noncestr);

			// 拿timestamp
			long timestamp = System.currentTimeMillis() / 1000;// 时间戳
			getaAccessTokenParam.setTimestamp(timestamp);

			// 获取signature
			String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
					+ url;
			String signature = getSHA1(str);
			getaAccessTokenParam.setSignature(signature);

		} catch (DangjianException e) {
			logger.error("", e);

		}

		result.setData(getaAccessTokenParam);
		result.setCode(ResultCode.OK.getCode());
		return result;
	}

	public String getTicket(String access_token) {
		String ticket = LocalCache.TOKEN_CACHE.getIfPresent(DlbConstant.KEY_TICKET);
		if (null != ticket && !"".equals(ticket)) {
			return ticket;
		}
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";// 这个url链接和参数不能变
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);
			logger.info("JSON字符串：" + demoJson);
			if (demoJson != null) {
				ticket = demoJson.getString("ticket");
			}

			if (StringUtils.isEmpty(ticket)) {
				ticket = "";
			}
			LocalCache.TOKEN_CACHE.put("TICKET", ticket);
			is.close();
		} catch (Exception e) {
			logger.error("获取TICKET失败",e);
		}
		return ticket;
	}

	/**
	 * 获取字符串的SHA1编码
	 *
	 * @param requestStr
	 * @return
	 */
	private String getSHA1(String requestStr) {
		String signature = new String();
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(requestStr.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signature;
	}

	/**
	 * byte转string(hex)
	 *
	 * @param hash
	 * @return
	 */
	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

}
