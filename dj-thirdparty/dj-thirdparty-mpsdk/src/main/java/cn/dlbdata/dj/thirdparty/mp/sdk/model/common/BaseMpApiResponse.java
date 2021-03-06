// ======================================================================
//
//      Copyright (C) 北京国双科技有限公司
//                    http://www.gridsum.com
//
//      保密性声明：此文件属北京国双科技有限公司所有，仅限拥有由国双科技
//      授予了相应权限的人所查看和所修改。如果你没有被国双科技授予相应的
//      权限而得到此文件，请删除此文件。未得国双科技同意，不得查看、修改、
//      散播此文件。
//
//
// ======================================================================

package cn.dlbdata.dj.thirdparty.mp.sdk.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.dlbdata.dj.common.IValueObject;

public class BaseMpApiResponse implements IValueObject {
  /**
   * 状态码
   */
  @JsonProperty
  private Integer errcode;
  /**
   * 错误信息描述，如果code=200的话，message=""
   */
  @JsonProperty
  private String errmsg;

  public Integer getErrcode() {
    return errcode;
  }

  public void setErrcode(Integer errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

}
