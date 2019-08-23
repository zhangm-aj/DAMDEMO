/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:50
 **/
package life.zm.damdemo.damdemo.dao.mapper;


import life.zm.damdemo.damdemo.dto.cond.ContentCond;
import life.zm.damdemo.damdemo.model.ContentDomain;
import life.zm.damdemo.damdemo.model.RelationShipDomain;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 文章相关Dao接口
 */
@Repository
@Mapper
public interface ContentDao {

    /**
     * 添加文章
     * @param contentDomain
     */
    @Insert("insert into t_contents (title,content,status,authorId,tags,created,username) values(#{title},#{content},#{status},#{authorId},#{tags}, UNIX_TIMESTAMP(NOW()),#{username})")
    void addArticle(ContentDomain contentDomain);

    /**
     * 根据编号获取文章
     * @param cid
     * @return
     */
    @Select("select * from t_contents where cid = #{cid}")
    ContentDomain getArticleById(Integer cid);

    /**
     * 更新文章
     * @param contentDomain
     */
    @Update("update t_contents set title = #{title},content = #{content},status = #{status},tags = #{tags},modified = UNIX_TIMESTAMP(NOW()),username=#{username},authorId=#{authorId} where cid = #{cid}")
    void updateArticleById(ContentDomain contentDomain);

    /**
     * 根据条件获取文章列表
     * @param contentCond
     * @return
     */
    @Select("select * from t_contents order by created desc")
    List<ContentDomain> getArticleByCond(ContentCond contentCond);

    /**
     * 删除文章
     * @param cid
     */
    @Delete("delete from t_contents where cid = #{cid}")
    void deleteArticleById(Integer cid);

    /**
     * 获取文章总数
     * @return
     */
//    @Select("select count(*) from t_contents")
//    Long getArticleCount();
    //多条件模糊查询
    @Select("select * from t_contents where (title like CONCAT('%',#{title},'%')or #{title}='') and (username like CONCAT('%',#{username},'%')or #{username}='' )and (tags like CONCAT('%',#{tags},'%') or #{tags} = '') order by created desc")
    List<ContentDomain> getArticleByConds(ContentCond contentCond);

    /**
     * 通过分类名获取文章
     * @param category
     * @return
     */
   // List<ContentDomain> getArticleByCategory(@Param("category") String category);

    /**
     * 通过标签获取文章
     * @param cid
     * @return
     */

   // List<ContentDomain> getArticleByTags(List<RelationShipDomain> cid);
}
