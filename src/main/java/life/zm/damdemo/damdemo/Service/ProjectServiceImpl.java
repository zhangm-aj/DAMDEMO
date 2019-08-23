package life.zm.damdemo.damdemo.Service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.zm.damdemo.damdemo.constant.ErrorConstant;
import life.zm.damdemo.damdemo.dao.mapper.ProjectDao;

import life.zm.damdemo.damdemo.dto.cond.ProjectCond;
import life.zm.damdemo.damdemo.exception.BusinessException;

import life.zm.damdemo.damdemo.model.ProjectDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ProjectDomain findProjectByPname(ProjectDomain projectDomain) {

        return projectDao.findProjectByPname(projectDomain);
    }

    @Override
    public void saveProject(ProjectDomain projectDomain) {
        projectDao.saveProject(projectDomain);
    }

    @Override
    @Cacheable(value = "projectsCaches", key = "'projectsByCond_' + #p1 + 'type_' + #p0.type")
    public PageInfo<ProjectDomain> getProjectsByCond(ProjectCond projectCond, int pageNum, int pageSize) {
        if (null == projectCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum,pageSize);
        List<ProjectDomain> contents = projectDao.getProjectsByCond(projectCond);
        PageInfo<ProjectDomain> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    public void deleteArticleById(Integer pid) {
        projectDao.deleteArticleById(pid);
    }

    @Override
    public void updateProject(ProjectDomain projectDomain) {
        projectDao.updateProject(projectDomain);
    }

    @Override
    public ProjectDomain getProjectsByPid(Integer pid) {
        return projectDao.getProjectsByPid(pid);
    }

}
