package cn.dlbdata.dj.db.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dj_partymember")
public class DjPartymember {
    /**
     * 记录ID
     */
    @Id
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    @Column(name = "sex_code")
    private Integer sexCode;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 所在支部
     */
    @Column(name = "dept_id")
    private Long deptId;
    
    @Transient
    private String deptName;

    /**
     * 转入支部
     */
    @Column(name = "from_dept_id")
    private Long fromDeptId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 职位ID
     */
    @Column(name = "post_id")
    private Integer postId;

    /**
     * 状态
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 生日
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * 学历
     */
    @Column(name = "education_code")
    private String educationCode;

    /**
     * 行政职务
     */
    @Column(name = "adm_post")
    private String admPost;

    /**
     * 党内职务
     */
    @Column(name = "party_post_code")
    private String partyPostCode;

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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return sex_code - 性别
     */
    public Integer getSexCode() {
        return sexCode;
    }

    /**
     * 设置性别
     *
     * @param sexCode 性别
     */
    public void setSexCode(Integer sexCode) {
        this.sexCode = sexCode;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取身份证号码
     *
     * @return idcard - 身份证号码
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证号码
     *
     * @param idcard 身份证号码
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * 获取所在支部
     *
     * @return dept_id - 所在支部
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置所在支部
     *
     * @param deptId 所在支部
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取转入支部
     *
     * @return from_dept_id - 转入支部
     */
    public Long getFromDeptId() {
        return fromDeptId;
    }

    /**
     * 设置转入支部
     *
     * @param fromDeptId 转入支部
     */
    public void setFromDeptId(Long fromDeptId) {
        this.fromDeptId = fromDeptId;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取职位ID
     *
     * @return post_id - 职位ID
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * 设置职位ID
     *
     * @param postId 职位ID
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
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
     * 获取生日
     *
     * @return birth_date - 生日
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 设置生日
     *
     * @param birthDate 生日
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取学历
     *
     * @return education_code - 学历
     */
    public String getEducationCode() {
        return educationCode;
    }

    /**
     * 设置学历
     *
     * @param educationCode 学历
     */
    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    /**
     * 获取行政职务
     *
     * @return adm_post - 行政职务
     */
    public String getAdmPost() {
        return admPost;
    }

    /**
     * 设置行政职务
     *
     * @param admPost 行政职务
     */
    public void setAdmPost(String admPost) {
        this.admPost = admPost;
    }

    /**
     * 获取党内职务
     *
     * @return party_post_code - 党内职务
     */
    public String getPartyPostCode() {
        return partyPostCode;
    }

    /**
     * 设置党内职务
     *
     * @param partyPostCode 党内职务
     */
    public void setPartyPostCode(String partyPostCode) {
        this.partyPostCode = partyPostCode;
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
    
}