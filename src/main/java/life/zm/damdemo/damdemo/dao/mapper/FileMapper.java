package life.zm.damdemo.damdemo.dao.mapper;


import life.zm.damdemo.damdemo.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper {
    @Select("select * from file where folderId = #{folderId} and projectId=#{projectId} order by createTime")
    List<File> getAllFile(@Param("folderId") int folderId, @Param("projectId") int projectId);
    @Insert("insert into file (fileId,fileName,folderId,projectId) values(null,#{fileName},#{folderId},#{projectId})")
    void insertFolder(@Param("fileName") String fileName, @Param("folderId") int folderId, @Param("projectId") int projectId);
    @Update("update file set fileName=#{fileName} where fileId=#{fileId}")
     void updateFolderName(@Param("fileName") String fileName, @Param("fileId") int fileId);

}
