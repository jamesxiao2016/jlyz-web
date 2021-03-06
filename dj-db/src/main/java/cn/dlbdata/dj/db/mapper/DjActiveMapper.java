package cn.dlbdata.dj.db.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.dlbdata.dj.db.pojo.DjActive;
import cn.dlbdata.dj.db.vo.ToDoVo;
import tk.mybatis.mapper.common.Mapper;

public interface DjActiveMapper extends Mapper<DjActive> {
	/**
	 * 获取用户参与活动次数
	 * 
	 * @param userId
	 * @param activeType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getUserActiveCountByActiveTypeAndTime(@Param("userId") Long userId, @Param("activeType") Long activeType,
			@Param("startTime") Date startTime, @Param("endTime") Date endTime);

	/**
	 * <p>
	 * Title: getRunningActive
	 * </p>
	 * <p>
	 * Description:党员生活通知
	 * </p>
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getRunningActive(Map<String, Object> map);
	
	/**
	 * 党支书获取活动列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getActiveListByDeptId(Map<String, Object> map);
	
	/**
	 * 
	 * <p>Title: getParticipateActiveOne</p> 
	 * <p>Description: 获取党员生活第一条</p> 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getParticipateActiveOne(Map<String, Object> map);
	/**
	 * 
	 * <p>
	 * Title: getParticipateActiveCount
	 * </p>
	 * <p>
	 * Description: 党员生活通知总数
	 * </p>
	 * 
	 * @param PartyMemberLifeNotice
	 * @return
	 */
	public int getParticipateActiveCount(Map<String, Object> map);

	/**
	 * 获取自己创建的已开始/已结束的没有图片的活动
	 * @return
	 */
	List<ToDoVo> getUnFinishedActive(Long userId);
	/**
	 * 
	 * <p>Title: selectActiveIndexById</p> 
	 * <p>Description: 根据活动id查询活动详情</p> 
	 * @param activeId
	 * @return
	 */
	public DjActive selectActiveIndexById(Long activeId);

	List<ToDoVo> getNoPicActive(Long userId);

	/**
	 * 获取自己创建的未开始的活动
	 * @param userId
	 * @return
	 */
	List<ToDoVo> getUnStartedActive(Long userId);
	
	/**
	 * 
	 * <p>Title: selectActiveIndexById</p> 
	 * <p>Description: 根据活动id查询活动详情</p> 
	 * @param activeId
	 * @return
	 */
	public DjActive selectAdminActiveIndexById(Long activeId);
	
	int selectActiveCount(int year);
	
	public DjActive getActiveIndex(Long activeId);
}