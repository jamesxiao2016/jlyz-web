package cn.dlbdata.dj.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.dlbdata.dj.common.core.exception.DlbException;
import cn.dlbdata.dj.common.core.util.DatetimeUtil;
import cn.dlbdata.dj.common.core.util.DigitUtil;
import cn.dlbdata.dj.constant.ActiveSubTypeEnum;
import cn.dlbdata.dj.constant.ActiveTypeEnum;
import cn.dlbdata.dj.constant.AuditStatusEnum;
import cn.dlbdata.dj.constant.DlbConstant;
import cn.dlbdata.dj.db.mapper.*;
import cn.dlbdata.dj.db.pojo.*;
import cn.dlbdata.dj.dto.active.ReportAddScoreRequest;
import cn.dlbdata.dj.vo.UserVo;
import cn.dlbdata.dj.vo.party.PioneeringPartyMemberVo;
import cn.dlbdata.dj.vo.party.ReportPartyMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlbdata.dj.service.IPartyMemberService;
import cn.dlbdata.dj.serviceimpl.base.BaseService;
import cn.dlbdata.dj.vo.PartyVo;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PartyMemberService extends BaseService implements IPartyMemberService {
	@Autowired
	private DjPartymemberMapper partyMemberMapper;

	@Autowired
	private DjScoreMapper scoreMapper;

	@Autowired
	private DjPartymemberMapper partymemberMapper;
	
	@Autowired
	private DjActiveMapper activeMapper;

	@Autowired
	private DjThoughtsMapper thoughtsMapper;

	@Autowired
	private DjVanguardMapper vanguardMapper;

	@Autowired
	private DjPicRecordMapper picRecordMapper;

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
		
		Date startTime = DatetimeUtil.getCurrYearFirst();
		Date endTime = DatetimeUtil.getCurrYearLast();
		//获取参与活动次数
		int activeCount = activeMapper.getUserActiveCountByActiveTypeAndTime(memberId, null, startTime, endTime);
		//获取参与金领驿站活动次数
		int jlyzCount = activeMapper.getUserActiveCountByActiveTypeAndTime(memberId, ActiveSubTypeEnum.ACTIVE_SUB_E.getActiveSubId(), startTime, endTime);
		result.setJlyzActiveNum(jlyzCount);
		result.setActiveNum(activeCount);
		return result;
	}

	@Override
	public List<DjScore> getScoreListByUserId(Long userId, Integer year) {
		if (userId == null) {
			return null;
		}
		return scoreMapper.getScoreListByUserId(userId, year);
	}

	@Override
	public List<DjScore> getTypeScoreListByUserId(Long userId, Integer year) {
		if (userId == null) {
			return null;
		}
		return scoreMapper.getTypeScoreListByUserId(userId, year);
	}

	@Override
	public List<ReportPartyMemberVo> getReportPartyMember(long deptId, long subTypeId) {
		if(subTypeId != ActiveSubTypeEnum.ACTIVE_SUB_K.getActiveSubId() &&
				subTypeId != ActiveSubTypeEnum.ACTIVE_SUB_L.getActiveSubId()) {
			throw new DlbException("subTypeId不合法!");
		}
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		System.out.println(year);
		List<DjPartymember> pojoList = partyMemberMapper.getReportPartyMember(deptId);
		List<ReportPartyMemberVo> voList = new ArrayList<>();
		for (DjPartymember pojo :pojoList) {
			ReportPartyMemberVo vo = new ReportPartyMemberVo();
			vo.setId(pojo.getId());
			vo.setName(pojo.getName());
			vo.setSubTypeId(subTypeId);
			vo.setDeptId(pojo.getDeptId());
			vo.setStatus(AuditStatusEnum.UNDONE.getValue());
			voList.add(vo);
		}
		if (!voList.isEmpty()) {
			//查询有积分记录的用户ID
			List<Long> userIdList = scoreMapper.getByDeptIdAndTypeIdAndSubTypeIdAndYear(deptId,
					ActiveTypeEnum.ACTIVE_C.getActiveId(),subTypeId,year);
			for (int i = 0;i<userIdList.size();i++) {
				for (int j = 0;j<voList.size();j++) {
					if (voList.get(j).getId().equals(userIdList.get(i))) {
						voList.get(j).setStatus(AuditStatusEnum.PASS.getValue());
					}
				}
			}
		}
		return voList;
	}

	/**
	 *思想汇报直接加分
	 * @param request 请求Data
	 */
	@Transactional
	@Override
	public void reportAddScore(ReportAddScoreRequest request, int year, UserVo userVo) {
		if (request.getId() == null ||
				request.getSubTypeId() == null || request.getReportTime() == null ||
				"".equals(request.getReportTime())) {
			throw new DlbException("请求参数不完整");
		}
		DjPartymember partymember = partymemberMapper.selectByPrimaryKey(request.getId());
		if (partymember == null) {
			throw new DlbException("该党员不存在");
		}
		// TODO 校验当前登录用户做该操作的权限
		//查询是否有思想汇报记录
		List<DjScore> scoreIds = scoreMapper.getScoreIdsByTypeIdAndSubTypeIdAndYearAndPartyMemberId(
				ActiveTypeEnum.ACTIVE_C.getActiveId(),request.getSubTypeId(),year,request.getId());
		if (scoreIds.size()>=1) {
			throw new DlbException("请勿重复加分!");
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date reportDate = null;
		try {
			reportDate = formatter.parse(request.getReportTime());
		}catch (ParseException e) {
			e.printStackTrace();
		}
		//TODO 图片ID待设置
		DjThoughts djThoughts = new DjThoughts();
		djThoughts.setId(DigitUtil.generatorLongId());
		djThoughts.setThoughtsInfo(request.getContent());
		djThoughts.setThoughtsTime(reportDate);
		djThoughts.setScore(new Float(5));
		djThoughts.setDjUserId(request.getId());
		djThoughts.setCreateTime(new Date());
		djThoughts.setDjDeptId(request.getDeptId());
		djThoughts.setStatus(DlbConstant.BASEDATA_STATUS_VALID);
		thoughtsMapper.insert(djThoughts);

		//todo ：后面需改为batchInsert
		for (Long pid:request.getPicIds()) {
			DjPicRecord picRecord = new DjPicRecord();
			picRecord.setId(DigitUtil.generatorLongId());
			picRecord.setTableName("dj_thoughts");
			picRecord.setRecordId(djThoughts.getId());
            picRecord.setDjPicId(pid);
			picRecord.setStatus(DlbConstant.BASEDATA_STATUS_VALID);
			picRecord.setCreateTime(new Date());
			picRecordMapper.insert(picRecord);
		}


		DjScore djScore = new DjScore();
		djScore.setId(DigitUtil.generatorLongId());
		djScore.setDjTypeId(ActiveTypeEnum.ACTIVE_C.getActiveId());
		djScore.setDjSubTypeId(request.getSubTypeId());
		djScore.setScore(new Float(5));
		djScore.setUserId(request.getId());
		djScore.setAddTime(reportDate);
		//TODO 目前User的信息获取不到，先写成1
		djScore.setApplyUserId(1L);
		djScore.setApproverId(1L);
		djScore.setAddYear(year);
		djScore.setStatus(DlbConstant.BASEDATA_STATUS_VALID);
		djScore.setCreateTime(new Date());
		djScore.setRecordId(djThoughts.getId());
		scoreMapper.insert(djScore);
	}

	/**
	 * 先锋作用评分党员列表
	 * @param deptId
	 * @return
	 */
	@Override
	public List<PioneeringPartyMemberVo> getPioneeringPartyMembers(Long deptId) {
		//获取支部全部党员
		List<DjPartymember> pojoList = partyMemberMapper.getReportPartyMember(deptId);
		List<PioneeringPartyMemberVo> voList = new ArrayList<>();
		for (DjPartymember pm:pojoList) {
			PioneeringPartyMemberVo vo = new PioneeringPartyMemberVo();
			vo.setDeptId(pm.getDeptId());
			vo.setId(pm.getId());
			vo.setName(pm.getName());
			voList.add(vo);
		}
		for (PioneeringPartyMemberVo vo :voList) {

			//1.判断该党员没有type in(1,2,3) 的记录，则该党员为未审核，否则走2
			int count1 = vanguardMapper.countUnAuditByPtMemberIdAndType(vo.getId());
			if (count1 == 0) {
				//未审核
				vo.setAuditStatus(AuditStatusEnum.UNDONE.getValue());
			} else {
				//2.判断该党员有未审核的记录（status =0），则该党员为待审核，否则该党员为已审核
				int count2 = vanguardMapper.countByPtMemberIdStatus(vo.getId(),AuditStatusEnum.UNDONE.getValue());
				if (count2>0) {
					vo.setAuditStatus(AuditStatusEnum.WAITING.getValue());
				} else {
					vo.setAuditStatus(AuditStatusEnum.PASS.getValue());
				}
			}
		}
		return voList;
	}
}
