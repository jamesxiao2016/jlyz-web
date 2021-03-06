package cn.dlbdata.dj.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlbdata.dj.db.mapper.DjBuildingMapper;
import cn.dlbdata.dj.db.mapper.DjDeptMapper;
import cn.dlbdata.dj.db.mapper.DjSectionMapper;
import cn.dlbdata.dj.db.mapper.TStageMapper;
import cn.dlbdata.dj.db.mapper.TStatisticMapper;
import cn.dlbdata.dj.db.pojo.DjBuilding;
import cn.dlbdata.dj.db.pojo.DjSection;
import cn.dlbdata.dj.db.pojo.TStage;
import cn.dlbdata.dj.db.pojo.TStatistic;
import cn.dlbdata.dj.db.vo.jlyz.BuildingVo;
import cn.dlbdata.dj.db.vo.jlyz.MemberStatVo;
import cn.dlbdata.dj.db.vo.jlyz.PartyBranchVo;
import cn.dlbdata.dj.db.vo.jlyz.SectionResVo;
import cn.dlbdata.dj.db.vo.jlyz.SectionVo;
import cn.dlbdata.dj.db.vo.jlyz.StageVo;
import cn.dlbdata.dj.service.IJlyzService;
import cn.dlbdata.dj.serviceimpl.base.BaseServiceImpl;

@Service
public class JlyzServiceImpl extends BaseServiceImpl implements IJlyzService {
	@Autowired
	private DjSectionMapper sectionMapper;
	@Autowired
	private DjBuildingMapper buildingMapper;
	@Autowired
	private DjDeptMapper deptMapper;
	@Autowired
	private TStatisticMapper statisticMapper;
	@Autowired
	private TStageMapper stageMapper;

	@Override
	public SectionVo querySection(Long id) {
		SectionVo result = new SectionVo();
		if (id == null) {
			return result;
		}

		// 查询片区信息
		DjSection section = sectionMapper.selectByPrimaryKey(id);
		if (section == null) {
			return result;
		}
		// 查询片区负责人信息
		result.setId(section.getId());
		result.setName(section.getName());
		result.setSj_id(section.getPrincipalId());
		result.setSj_name(section.getPrincipalName());
		result.setQy_number(section.getEnterpriseCount());//企业数量
		Integer dy_number = sectionMapper.selectSectionPartymemberCount(id);
		if(dy_number != null) {
			result.setDy_number(dy_number);//党员数量
		}
		Integer dzb_number = sectionMapper.selectSectionDeptCount(id);
		if(dzb_number != null) {
			result.setDzb_number(dzb_number);//党支部数量
		}
		Integer ld_number = sectionMapper.selectSectionBuildingCount(id);
		if(ld_number != null) {
			result.setLd_number(ld_number);//大楼数量
		}
		result.setCamera(section.getCamera());
		result.setMesh(section.getMesh());
		// 查询楼宇列表
		List<BuildingVo> list = buildingMapper.getListBuildingBySectionId(id);
		if(list != null && list.size() > 0) {
			result.setBuildings(list);
			result.setLd_number(list.size());
		}
		
		return result;
	}

	@Override
	public List<PartyBranchVo> queryPartyBranches(String buildingCode) {
		List<PartyBranchVo> result = new ArrayList<>();
		if (StringUtils.isEmpty(buildingCode)) {
			return null;
		}
		// 查询楼宇的党支部信息
		result = deptMapper.getBuildingAndDept(buildingCode);
		return result;
	}

	@Override
	public BuildingVo queryBuilding(String buildingCode, String rootUrl) {
		BuildingVo result = new BuildingVo();
		if (StringUtils.isEmpty(buildingCode)) {
			logger.error("楼宇编号为空");
			return result;
		}

		DjBuilding condition = new DjBuilding();
		condition.setCode(buildingCode);
		DjBuilding building = buildingMapper.selectOne(condition);
		if (building == null) {
			logger.error("获取数据为空->" + buildingCode);
			return result;
		}
		result.setDy_number(building.getPeopleNum());
		result.setFloors_total(building.getFloorNum());
		result.setId(building.getId() + "");
		result.setName(building.getName());
		result.setOffset(building.getOffset());
		StageVo stageVo = new StageVo();
		TStage stage = getStageByBuildingCode(buildingCode);
		if (stage != null) {
			stageVo.setDzb_prefix(stage.getPartyBranchPrefix());
			stageVo.setFloor(stage.getFloor());
			stageVo.setId(stage.getId() + "");
			stageVo.setImage(rootUrl + "/img/" + stage.getImage());
			stageVo.setName(stage.getName());
			stageVo.setSummary(stage.getSummary());
			result.setStage(stageVo);
		}

		return result;
	}

	/**
	 * 根据楼宇编号获取stage信息
	 * 
	 * @param buildingCode
	 * @return
	 */
	private TStage getStageByBuildingCode(String buildingCode) {
		TStage condition = new TStage();
		condition.setBuildingId(buildingCode);
		List<TStage> list = stageMapper.select(condition);
		if (list == null || list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

	@Override
	public MemberStatVo queryMemberStatistic(String buildingCode) {
		MemberStatVo result = new MemberStatVo();
		if (StringUtils.isEmpty(buildingCode)) {
			return result;
		}
		TStatistic condition = new TStatistic();
		condition.setBuildingId(buildingCode);
		List<TStatistic> list = statisticMapper.select(condition);
		if(list == null || list.isEmpty()) {
			return result;
		}
		condition = list.get(0);
		
		BeanUtils.copyProperties(condition, result);
		
		return result;
	}

	/* (non-Javadoc)
	 * <p>Title: queryAllSections</p>
	 * <p>Description: 获取所有片区</p> 
	 * @return  
	 * @see cn.dlbdata.dj.service.IJlyzService#queryAllSections()
	 */
	@Override
	public List<SectionResVo> queryAllSections() {
		return sectionMapper.queryAllSections();
	}

}
