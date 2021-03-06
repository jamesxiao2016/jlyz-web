package cn.dlbdata.dj.thirdparty.mp.sdk.model.access;

import cn.dlbdata.dj.common.http.annotations.Param;
import cn.dlbdata.dj.thirdparty.mp.sdk.model.common.BaseParam;

public class GetaAccessTokenParam extends BaseParam {

    @Param(key = "grant_type", required = true)
    private GrantType grantType;
    @Param(key = "appid", required = true)
    private String appid;
    @Param(key = "secret", required = true)
    private String secret;

    private String token;

    private long timestamp;

    private String nonceStr;

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    private String signature;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
