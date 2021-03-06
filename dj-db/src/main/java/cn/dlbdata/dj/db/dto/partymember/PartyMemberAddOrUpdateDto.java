package cn.dlbdata.dj.db.dto.partymember;


public class PartyMemberAddOrUpdateDto {
	private Long id;
    private String name;//党员姓名
    private Long deptId;//部门Id
    private String userName;//账户名
    private Integer sexCode;//性别
    private String phone;
    private String email;
    private String idcard;
    private String birthDate;
    private String educationCode;
    private String partyPostCode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    public String getPartyPostCode() {
        return partyPostCode;
    }

    public void setPartyPostCode(String partyPostCode) {
        this.partyPostCode = partyPostCode;
    }

	public Integer getSexCode() {
		return sexCode;
	}

	public void setSexCode(Integer sexCode) {
		this.sexCode = sexCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
