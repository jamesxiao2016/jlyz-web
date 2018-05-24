/**
 *  <p>Title: IActiveUserService.java</p>  
 *  <p>Description: </p>  
 *  @author zhouxuan
 *  @date 2018年5月23日 
 */
package cn.dlbdata.dj.service;

import java.util.List;

import cn.dlbdata.dj.common.core.web.vo.ResultVo;
import cn.dlbdata.dj.db.pojo.DjActiveUser;
import cn.dlbdata.dj.db.resquest.ActiveSignUpRequest;

/**
 * <p>Title: IActiveUserService</p>
 * @author zhouxuan
 * <p>Description: </p>
 * @date 2018年5月23日  
 */
public interface IActiveUserService {
	/**
	 * 
	 * <p>Title: insertActiveSignUp</p> 
	 * <p>Description: 金领驿站活动报名</p> 
	 * @param activeSignUpRequest
	 * @return
	 */
	public ResultVo<String> insertActiveSignUp(ActiveSignUpRequest activeSignUpRequest);
	
	/**
	 * 
	 * <p>Title: selectByExample</p> 
	 * <p>Description: 查询该活动是否报名</p> 
	 * @param userId
	 * @param activeId
	 * @return
	 */
	public List<DjActiveUser> selectByExample(Long userId, Long activeId);
}
