package cn.dlbdata.dj.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.dlbdata.dj.db.mapper.DjRoleMapper;
import cn.dlbdata.dj.db.pojo.DjRole;
import cn.dlbdata.dj.service.IRoleService;
import cn.dlbdata.dj.serviceimpl.base.BaseService;

public class RoleService extends BaseService implements IRoleService {
	@Autowired
	private DjRoleMapper roleMapper;
	@Override
	public DjRole getRoleInfoById(Long id) {
		if(id == null) {
			return null;
		}
		return roleMapper.selectByPrimaryKey(id);
	}

}
