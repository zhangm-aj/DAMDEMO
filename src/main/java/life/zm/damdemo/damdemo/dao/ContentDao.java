/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:50
 **/
package life.zm.damdemo.damdemo.dao;


import life.zm.damdemo.damdemo.dto.cond.ContentCond;
import life.zm.damdemo.damdemo.model.ContentDomain;
import life.zm.damdemo.damdemo.model.RelationShipDomain;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 文章相关Dao接口
 */
@Mapper
public interface ContentDao {

    /**
     * 添加文章
     * @param contentDomain
     */
    @Insert("insert into t_contents (title,content,status,authorId,tags,created) values(#{title},#{content},#{status},#{authorId},#{tags}, UNIX_TIMESTAMP(NOW()))")
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
    @Update("update t_contents set title = #{title},content = #{content},status = #{status},tags = #{tags},modified = UNIX_TIMESTAMP(NOW()) where cid = #{cid}")
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
    @Select("select count(*) from t_contents")
    Long getArticleCount();

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
