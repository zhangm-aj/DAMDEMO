package life.zm.damdemo.damdemo.dao.mapper;


import life.zm.damdemo.damdemo.model.ExampleQuickFile;
import life.zm.damdemo.damdemo.util.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ExampleQuickFileMapper extends MyMapper<ExampleQuickFile> {

    /**
     * 批量插入
     *
     * @param files
     * @return
     */
    @Insert("<script>" +
            "insert into example_quick_file (id, name,length,translate_status,databag_status,create_time,version) values " +
            "<foreach collection=\"list\" item=\"file\" separator=\",\" >" +
            "(#{file.id}, #{file.name}, #{file.length}, #{file.translateStatus}, #{file.databagStatus}, #{file.createTime},#{file.version})" +
            "</foreach>" +
            "</script>")
    int insertFiles(List<ExampleQuickFile> files);
    @Insert("insert into example_quick_file (id,name,folder_id,project_id,is_folder,create_time) values(null,#{fileName},#{folderId},#{projectId},1,now())")
    void insertFolder(@Param("fileName") String fileName, @Param("folderId") Long folderId, @Param("projectId") int projectId);
    @Select("select name from example_quick_file where id=#{id}")
    String getFolderName(Long id);
    @Select("select * from example_quick_file where name=#{name}")
    List<ExampleQuickFile> findFileByName(String name);
}