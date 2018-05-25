package cn.dlbdata.dj.service;

import java.util.List;

import cn.dlbdata.dj.db.pojo.DjDept;
import cn.dlbdata.dj.db.vo.party.BranchDeptInfoVo;

/**
 * 部门处理相关的业务逻辑
 * @author xiaowei
 *
 */
public interface IDeptService {

	/**
	 * 根据ID获取部门信息
	 * @param id 部门ID
	 * @return
	 */
	public DjDept getDeptInfoById(Long id);
	
	/**
	 * 根据ID获取下级部门列表
	 * @param parentId 部门ID
	 * @return
	 */
	public List<DjDept> getDeptListByParentId(Long parentId);
	/**
	 * 
	 * <p>Title: getDeptMessage</p> 
	 * <p>Description:党支部相关信息 </p> 
	 * @param departmentId
	 * @return
	 */
	public DjDept getDeptMessage(Long departmentId);

	/**
	 * 片区负责人获取支部信息列表
	 * @param sectionId
	 * @return
	 */
	List<BranchDeptInfoVo>getBranchDeptInfo(Long sectionId);
}
