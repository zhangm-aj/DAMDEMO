package life.zm.damdemo.damdemo.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="file")
public class File {
    /**
     * 主键，文件/文件夹id
     */
    @Id
    private  Long file_id;
    /**
     * 文件名
     */

    private String fileName;
    /**
     * 文件大小
     */
    private Long length;
    /**
     * 转换状态
     */

    private String translateStatus;

    /**
     * 离线包状态
     */

    private String databagStatus;
    /**
     * 版本
     */
    private double version;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 文件夹id
     */

    private long folderId;
    /**
     * 项目id
     */

    private int projectId;

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getTranslateStatus() {
        return translateStatus;
    }

    public void setTranslateStatus(String translateStatus) {
        this.translateStatus = translateStatus;
    }

    public String getDatabagStatus() {
        return databagStatus;
    }

    public void setDatabagStatus(String databagStatus) {
        this.databagStatus = databagStatus;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "file_id=" + file_id +
                ", fileName='" + fileName + '\'' +
                ", length=" + length +
                ", translateStatus='" + translateStatus + '\'' +
                ", databagStatus='" + databagStatus + '\'' +
                ", version=" + version +
                ", createTime=" + createTime +
                ", folderId=" + folderId +
                ", projectId=" + projectId +
                '}';
    }
}
