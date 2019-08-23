package life.zm.damdemo.damdemo.model;

public class ProjectDomain {
    private Integer pid;
    private String pname;
    private Integer created;

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getPid() {
        return pid;
    }


    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

}
