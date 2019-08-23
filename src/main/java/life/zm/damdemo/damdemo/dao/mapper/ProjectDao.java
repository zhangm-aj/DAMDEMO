package life.zm.damdemo.damdemo.dao.mapper;


import life.zm.damdemo.damdemo.dto.cond.ProjectCond;

import life.zm.damdemo.damdemo.model.ProjectDomain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectDao {
    @Select("select * from t_project where pname = #{pname}")
    ProjectDomain findProjectByPname(ProjectDomain projectDomain);
    @Insert("insert into t_project (pname,created) values (#{pname},UNIX_TIMESTAMP(NOW()))")
    void saveProject(ProjectDomain projectDomain);
    @Select("select * from t_project order by created desc")
    List<ProjectDomain> getProjectsByCond(ProjectCond projectCond);
    @Delete("delete from t_project where pid = #{pid}")
    void deleteArticleById(Integer pid);
    @Update("update t_project set pname = #{pname} where pid = #{pid}")
    void updateProject(ProjectDomain projectDomain);
    @Select("select * from t_project where pid = #{pid}")
    ProjectDomain getProjectsByPid(Integer pid);
}
