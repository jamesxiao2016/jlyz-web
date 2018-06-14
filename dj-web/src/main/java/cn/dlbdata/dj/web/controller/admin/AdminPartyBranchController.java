package cn.dlbdata.dj.web.controller.admin;

import cn.dlbdata.dj.common.core.util.constant.CoreConst;
import cn.dlbdata.dj.common.core.web.vo.ResultVo;
import cn.dlbdata.dj.db.dto.dept.DeptAddOrUpdateDto;
import cn.dlbdata.dj.service.IDeptService;
import cn.dlbdata.dj.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.dlbdata.dj.web.base.BaseController;

/**
 * 党支部管理Controller
 * 
 * @author xiaowei
 *
 */
@Controller
@RequestMapping("/admin/branch")
public class AdminPartyBranchController extends BaseController {
	@Autowired
	private IDeptService deptService;
	/**
	 * 查询列表
	 * 
	 * @return
	 */
	@RequestMapping("/branchInfo.html")
	public String list() {
		return "/branch/branchInfo.html";
	}

	/**
	 * 详情界面
	 * 
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail() {
		return "detail.html";
	}

	/**
	 * 添加或修改界面
	 * 
	 * @return
	 */
	@RequestMapping("/add.html")
	public String add() {
		return "add.html";
	}

	@PostMapping("/addBranch")
	@ResponseBody
	public ResultVo addBranch(@RequestBody DeptAddOrUpdateDto dto) {
		UserVo user = getCurrentUserFromCache();
		ResultVo resultVo = new ResultVo<>();
		if (user == null) {
			logger.error("用户未登录");
			resultVo.setCode(CoreConst.ResultCode.NOT_LOGIN.getCode());
			resultVo.setMsg("用户未登录或用户已退出");
			return resultVo;
		}
		deptService.addBranch(dto,user);
		resultVo.setCode(CoreConst.ResultCode.OK.getCode());
		resultVo.setMsg("新增党支部成功!");
		return resultVo;
	}

	@PostMapping("updateBranch/{id}")
	@ResponseBody
	public ResultVo updateBranch(@RequestBody DeptAddOrUpdateDto dto, @PathVariable Long id){
		UserVo user = getCurrentUserFromCache();
		ResultVo resultVo = new ResultVo<>();
		if (user == null) {
			logger.error("用户未登录");
			resultVo.setCode(CoreConst.ResultCode.NOT_LOGIN.getCode());
			resultVo.setMsg("用户未登录或用户已退出");
			return resultVo;
		}
		deptService.updateBranch(id,dto,user);
		resultVo.setCode(CoreConst.ResultCode.OK.getCode());
		resultVo.setMsg("修改党支部成功!");
		return resultVo;
	}

	@PostMapping("invalidBranch/{id}")
	@ResponseBody
	public ResultVo invalidBranch(@PathVariable Long id) {
		UserVo user = getCurrentUserFromCache();
		ResultVo resultVo = new ResultVo<>();
		if (user == null) {
			logger.error("用户未登录");
			resultVo.setCode(CoreConst.ResultCode.NOT_LOGIN.getCode());
			resultVo.setMsg("用户未登录或用户已退出");
			return resultVo;
		}
		deptService.invalidBranch(id,user);
		resultVo.setCode(CoreConst.ResultCode.OK.getCode());
		resultVo.setMsg("作废党支部成功!");
		return resultVo;
	}
}
