package cn.dlbdata.dj.db.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dj_apply")
public class DjApply {
    /**
     * 记录ID
     */
    @Id
    private Long id;

    /**
     * 业务类型ID
     */
    @Column(name = "dj_type_id")
    private Long djTypeId;

    /**
     * 二级分类ID
     */
    @Column(name = "dj_sub_type_id")
    private Long djSubTypeId;
    
    /**
     * 二级分类名称
     */
    @Column(name = "sub_type_name")
    private String subTypeName;

    /**
     * 申请内容
     */
    @Column(name = "apply_info")
    private String applyInfo;

    /**
     * 申请人ID
     */
    @Column(name = "apply_id")
    private Long applyId;

    /**
     * 申请人姓名
     */
    @Column(name = "apply_name")
    private String applyName;

    /**
     * 申请说明
     */
    @Column(name = "apply_desc")
    private String applyDesc;

    /**
     * 审批人ID
     */
    @Column(name = "approver_id")
    private Long approverId;

    /**
     * 审批人姓名
     */
    @Column(name = "approver_name")
    private String approverName;

    /**
     * 部门ID
     */
    @Column(name = "dj_dept_id")
    private Long djDeptId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 业务表
     */
    @Column(name = "table_name")
    private String tableName;

    /**
     * 记录ID
     */
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "create_time")
    private Date createTime;

    private Float score;

    /**
     * 加分人ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 党员姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 审批角色ID
     */
    @Column(name = "dj_role_id")
    private Long djRoleId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审批时间
     */
    @Column(name = "approve_time")
    private Date approveTime;
    
    /**
     * 申请年份
     */
    @Column(name = "apply_year")
    private Integer applyYear;
    
    @Transient
    private String typeName;

    /**
     * 获取记录ID
     *
     * @return id - 记录ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录ID
     *
     * @param id 记录ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取业务类型ID
     *
     * @return dj_type_id - 业务类型ID
     */
    public Long getDjTypeId() {
        return djTypeId;
    }

    /**
     * 设置业务类型ID
     *
     * @param djTypeId 业务类型ID
     */
    public void setDjTypeId(Long djTypeId) {
        this.djTypeId = djTypeId;
    }

    /**
     * 获取二级分类ID
     *
     * @return dj_sub_type_id - 二级分类ID
     */
    public Long getDjSubTypeId() {
        return djSubTypeId;
    }

    /**
     * 设置二级分类ID
     *
     * @param djSubTypeId 二级分类ID
     */
    public void setDjSubTypeId(Long djSubTypeId) {
        this.djSubTypeId = djSubTypeId;
    }

    /**
     * 获取申请内容
     *
     * @return apply_info - 申请内容
     */
    public String getApplyInfo() {
        return applyInfo;
    }

    /**
     * 设置申请内容
     *
     * @param applyInfo 申请内容
     */
    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    /**
     * 获取申请人ID
     *
     * @return apply_id - 申请人ID
     */
    public Long getApplyId() {
        return applyId;
    }

    /**
     * 设置申请人ID
     *
     * @param applyId 申请人ID
     */
    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取申请人姓名
     *
     * @return apply_name - 申请人姓名
     */
    public String getApplyName() {
        return applyName;
    }

    /**
     * 设置申请人姓名
     *
     * @param applyName 申请人姓名
     */
    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    /**
     * 获取申请说明
     *
     * @return apply_desc - 申请说明
     */
    public String getApplyDesc() {
        return applyDesc;
    }

    /**
     * 设置申请说明
     *
     * @param applyDesc 申请说明
     */
    public void setApplyDesc(String applyDesc) {
        this.applyDesc = applyDesc;
    }

    /**
     * 获取审批人ID
     *
     * @return approver_id - 审批人ID
     */
    public Long getApproverId() {
        return approverId;
    }

    /**
     * 设置审批人ID
     *
     * @param approverId 审批人ID
     */
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    /**
     * 获取审批人姓名
     *
     * @return approver_name - 审批人姓名
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * 设置审批人姓名
     *
     * @param approverName 审批人姓名
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    /**
     * 获取部门ID
     *
     * @return dj_dept_id - 部门ID
     */
    public Long getDjDeptId() {
        return djDeptId;
    }

    /**
     * 设置部门ID
     *
     * @param djDeptId 部门ID
     */
    public void setDjDeptId(Long djDeptId) {
        this.djDeptId = djDeptId;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取业务表
     *
     * @return table_name - 业务表
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置业务表
     *
     * @param tableName 业务表
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取记录ID
     *
     * @return record_id - 记录ID
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * 设置记录ID
     *
     * @param recordId 记录ID
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return score
     */
    public Float getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * 获取加分人ID
     *
     * @return user_id - 加分人ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置加分人ID
     *
     * @param userId 加分人ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取党员姓名
     *
     * @return user_name - 党员姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置党员姓名
     *
     * @param userName 党员姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取审批角色ID
     *
     * @return dj_role_id - 审批角色ID
     */
    public Long getDjRoleId() {
        return djRoleId;
    }

    /**
     * 设置审批角色ID
     *
     * @param djRoleId 审批角色ID
     */
    public void setDjRoleId(Long djRoleId) {
        this.djRoleId = djRoleId;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取审批时间
     *
     * @return approve_time - 审批时间
     */
    public Date getApproveTime() {
        return approveTime;
    }

    /**
     * 设置审批时间
     *
     * @param approveTime 审批时间
     */
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public Integer getApplyYear() {
		return applyYear;
	}

	public void setApplyYear(Integer applyYear) {
		this.applyYear = applyYear;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}