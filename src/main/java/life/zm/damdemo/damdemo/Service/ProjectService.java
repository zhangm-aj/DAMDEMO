package life.zm.damdemo.damdemo.Service;

import com.github.pagehelper.PageInfo;
import life.zm.damdemo.damdemo.dto.cond.ContentCond;
import life.zm.damdemo.damdemo.dto.cond.ProjectCond;
import life.zm.damdemo.damdemo.model.ContentDomain;
import life.zm.damdemo.damdemo.model.ProjectDomain;

public interface ProjectService {
    ProjectDomain findProjectByPname(ProjectDomain projectDomain);

    void saveProject(ProjectDomain projectDomain);

    PageInfo<ProjectDomain> getProjectsByCond(ProjectCond projectCond, int page, int limit);

    void deleteArticleById(Integer pid);

    void updateProject(ProjectDomain projectDomain);

    ProjectDomain getProjectsByPid(Integer pid);
}
