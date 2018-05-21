package cn.dlbdata.dj.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlbdata.dj.db.mapper.DjPartymemberMapper;
import cn.dlbdata.dj.db.pojo.DjPartymember;
import cn.dlbdata.dj.service.IPartyMemberService;
import cn.dlbdata.dj.serviceimpl.base.BaseService;
import cn.dlbdata.dj.vo.PartyVo;

@Service
public class PartyMemberService extends BaseService implements IPartyMemberService {
	@Autowired
	private DjPartymemberMapper partyMemberMapper;

	@Override
	public DjPartymember getInfoById(Long id) {
		if (id == null) {
			return null;
		}
		return partyMemberMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DjPartymember> getPartyMemberListByDeptId(Long deptId) {
		if (deptId == null) {
			return null;
		}
		DjPartymember member = new DjPartymember();
		member.setDeptId(deptId);
		return partyMemberMapper.select(member);
	}

	@Override
	public PartyVo getScoreAndNumByMemberId(Long memberId) {
		PartyVo result = new PartyVo();
		result.setMemberId(memberId);
		return result;
	}

}