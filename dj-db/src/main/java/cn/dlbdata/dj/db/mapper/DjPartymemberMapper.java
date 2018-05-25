package cn.dlbdata.dj.db.mapper;

import java.util.List;
import java.util.Map;

import cn.dlbdata.dj.db.pojo.DjPartymember;
import cn.dlbdata.dj.db.vo.party.ObserveLowPartyMemberVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface DjPartymemberMapper extends Mapper<DjPartymember> {
    List<DjPartymember> getReportPartyMember(Long deptId);
    /**
     * 
     * <p>Title: queryAllPartyMembersByDeptId</p> 
     * <p>Description: 查询支部全部党员的信息以及分数</p> 
     * @param deptId
     * @return
     */
    public List<DjPartymember> queryAllPartyMembersByDeptId(Map<String,Object> map);

    List<ObserveLowPartyMemberVo> getObserveLowPartyMember(@Param("deptId") Long deptId,@Param("year") int year);
}