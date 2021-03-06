package cn.dlbdata.dj.db.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dj_pic_record")
public class DjPicRecord {
    /**
     * 记录ID
     */
    @Id
    private Long id;

    /**
     * 记录表名称
     */
    @Column(name = "table_name")
    private String tableName;

    /**
     * 记录ID
     */
    @Column(name = "record_id")
    private Long recordId;

    /**
     * 图片ID
     */
    @Column(name = "dj_pic_id")
    private Long djPicId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取记录表名称
     *
     * @return table_name - 记录表名称
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置记录表名称
     *
     * @param tableName 记录表名称
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
     * 获取图片ID
     *
     * @return dj_pic_id - 图片ID
     */
    public Long getDjPicId() {
        return djPicId;
    }

    /**
     * 设置图片ID
     *
     * @param djPicId 图片ID
     */
    public void setDjPicId(Long djPicId) {
        this.djPicId = djPicId;
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
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}