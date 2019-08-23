package life.zm.damdemo.damdemo.dao.mapper;


import life.zm.damdemo.damdemo.model.ExampleQuickIntegrateFile;
import life.zm.damdemo.damdemo.util.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ExampleQuickIntegrateFileMapper extends MyMapper<ExampleQuickIntegrateFile> {
    /**
     * 批量插入
     *
     * @param integrateFiles
     * @return
     */
    @Insert("<script>" +
            "insert into example_quick_integrate_file (id,integrate_id,file_id,file_name,specialty,floor) values " +
            "<foreach collection=\"list\" item=\"integrateFile\" separator=\",\" >" +
            "(#{integrateFile.id}, #{integrateFile.integrateId}, #{integrateFile.fileId}, #{integrateFile.fileName}, #{integrateFile.specialty}, #{integrateFile.floor})" +
            "</foreach>" +
            "</script>")
    int insertIntegrateFiles(List<ExampleQuickIntegrateFile> integrateFiles);
}