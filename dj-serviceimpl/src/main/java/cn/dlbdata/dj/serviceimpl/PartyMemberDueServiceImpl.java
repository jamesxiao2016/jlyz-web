package cn.dlbdata.dj.serviceimpl;

import cn.dlbdata.dj.common.core.util.DatetimeUtil;
import cn.dlbdata.dj.common.core.util.DigitUtil;
import cn.dlbdata.dj.common.core.util.StringUtil;
import cn.dlbdata.dj.common.core.util.poi.ExcelReplaceDataVO;
import cn.dlbdata.dj.constant.ActiveSubTypeEnum;
import cn.dlbdata.dj.constant.ActiveTypeEnum;
import cn.dlbdata.dj.constant.DlbConstant;
import cn.dlbdata.dj.db.mapper.DjPartymemberDuesMapper;
import cn.dlbdata.dj.db.mapper.DjPartymemberMapper;
import cn.dlbdata.dj.db.mapper.DjScoreMapper;
import cn.dlbdata.dj.db.pojo.DjPartymember;
import cn.dlbdata.dj.db.pojo.DjPartymemberDues;
import cn.dlbdata.dj.db.pojo.DjScore;
import cn.dlbdata.dj.service.PartyMemberDueService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class PartyMemberDueServiceImpl implements PartyMemberDueService {
    @Autowired
    private DjPartymemberDuesMapper duesMapper;
    @Autowired
    private DjPartymemberMapper partymemberMapper;
    @Autowired
    private DjScoreMapper scoreMapper;

    @Override
    public Map<String, List<ExcelReplaceDataVO>> saveDues(List<List<Object>> data) {
        Map<String,  List<ExcelReplaceDataVO>> map = new HashMap<>();
        List<ExcelReplaceDataVO> errorInfo = new ArrayList<>();
        List<String> orderCodeList = new ArrayList<>();
        Set<String> orderCodeSet = new HashSet<>();
        for (int i = 2;i<data.size();i++) {
            orderCodeList.add(data.get(i).get(0).toString());
            orderCodeSet.add(data.get(i).get(0).toString());
        }
        Collection rs = CollectionUtils.disjunction(orderCodeList,orderCodeSet);
        List<String> repeatList = new ArrayList<String>(rs);
        List<DjPartymemberDues> duesList = new ArrayList<>();
        List<DjScore> scoreList = new ArrayList<>();
        boolean hasError = false;
        for (int i = 2;i<data.size();i++) {//第一行第二行是表格头部，不需要取，直接取第三行
            DjPartymemberDues due = new DjPartymemberDues();
            due.setId(DigitUtil.generatorLongId());
            due.setCreateTime(new Date());

            DjScore score = new DjScore();
            score.setId(DigitUtil.generatorLongId());
            score.setDjSubTypeId(ActiveSubTypeEnum.ACTIVE_SUB_I.getActiveSubId());
            score.setDjTypeId(ActiveTypeEnum.ACTIVE_G.getActiveId());
            score.setScore(1.25F);
            score.setAddTime(new Date());
            score.setAddYear(Calendar.getInstance().get(Calendar.YEAR));//TODO 需要修改
            score.setStatus(1);
            score.setRecrodDesc("党员按月足额缴纳党费");
            score.setApproverName("系统自动");

            List<Object> list = data.get(i);
            ExcelReplaceDataVO repVo= new ExcelReplaceDataVO();
            repVo.setRow(i);
            repVo.setColumn(18);
            StringBuilder sb = new StringBuilder();
            //订单编号
            String orderCode = list.get(0).toString();
            if (orderCode == null || "".equals(orderCode)) {
                sb.append("请填写缴费单号!,");
                hasError = true;
            } else {
                if (repeatList.contains(orderCode)) {
                    sb.append("清单中缴费单号重复!,");
                    hasError = true;
                } else {
                    boolean exists = duesMapper.existWithOrderCode(orderCode);
                    if (exists) {
                        sb.append("缴费单号已存在系统中!,");
                        hasError = true;
                    } else {
                        due.setOrderCode(orderCode);
                    }
                }
            }

            //缴费时间
            String payTimeStr = list.get(10).toString();
            Date payTime = DatetimeUtil.getLongDateByStr(payTimeStr);
            if (payTime == null) {
                sb.append("请填写正确的缴费时间!,");
                hasError = true;
            } else {
                due.setPaymentTime(payTime);
            }

            //缴费金额
            String moneyStr = list.get(3).toString();
            Float money = DigitUtil.parseToFloat(moneyStr,null);
            if (money == null) {
                sb.append("请填写正确的缴费金额!,");
                hasError = true;
            } else {
                due.setRealMoney(money);
                due.setDuesMoney(money);
            }
            DjPartymember partymember = null;
            //身份证号
            String idCardStr = list.get(12).toString();
            if (idCardStr == null || "".equals(idCardStr)) {
                sb.append("请填写身份证号!,");
                hasError = true;
            } else {
                partymember = partymemberMapper.getByIdCard(idCardStr);
                if (partymember == null) {
                    sb.append("系统中无此身份证号对应的党员!,");
                    hasError = true;
                } else {
                    due.setDjPartymemberId(partymember.getId());
                    score.setUserId(partymember.getId());
                    score.setUserName(partymember.getName());
                }
            }
            String name = list.get(13).toString();
            if (name == null) {
                sb.append("请填写姓名!,");
                hasError = true;
            } else {
                if (partymember != null) {
                    name = StringUtil.deleteWhitespace(name);
                    if (!partymember.getName().equals(name)) {
                        sb.append("党员身份证错误!,");
                        hasError = true;
                    }
                }
            }
            due.setStatus(DlbConstant.BASEDATA_STATUS_VALID);
            String payStatusStr = list.get(8).toString();
            if (payStatusStr == null || "".equals(payStatusStr)) {
                sb.append("请填写缴费状态!,");
                hasError = true;
            } else if (payStatusStr.trim().equals("已缴费")) { //没缴费的不保存
                duesList.add(due);
                scoreList.add(score);
            } else {
                //doNothing
            }
            repVo.setValue(sb.toString());
            errorInfo.add(repVo);
        }
        if (hasError) {//文件中有错误就不做保存操作
            map.put("error",errorInfo);
            return map;
        } else {
            duesMapper.batchInsert(duesList);
            scoreMapper.batchInsert(scoreList);
        }
        return null;
    }
}
