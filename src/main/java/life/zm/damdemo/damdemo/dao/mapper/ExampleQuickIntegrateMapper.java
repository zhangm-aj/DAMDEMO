package life.zm.damdemo.damdemo.dao.mapper;


import life.zm.damdemo.damdemo.model.ExampleQuickIntegrate;
import life.zm.damdemo.damdemo.util.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ExampleQuickIntegrateMapper extends MyMapper<ExampleQuickIntegrate> {

    /**
     * 批量插入
     *
     * @param integrates
     * @return
     */
    @Insert("<script>" +
            "insert into example_quick_integrate (id, name,file_num,integrate_status,databag_status,create_time) values " +
            "<foreach collection=\"list\" item=\"integrate\" separator=\",\" >" +
            "(#{integrate.id}, #{integrate.name}, #{integrate.fileNum}, #{integrate.integrateStatus}, #{integrate.databagStatus}, #{integrate.createTime})" +
            "</foreach>" +
            "</script>")
    int insertIntegrates(List<ExampleQuickIntegrate> integrates);
}