/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:50
 **/
package life.zm.damdemo.damdemo.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import life.zm.damdemo.damdemo.constant.ErrorConstant;
import life.zm.damdemo.damdemo.constant.WebConst;
import life.zm.damdemo.damdemo.dao.mapper.ContentDao;
import life.zm.damdemo.damdemo.dto.cond.ContentCond;
import life.zm.damdemo.damdemo.exception.BusinessException;

import life.zm.damdemo.damdemo.model.ContentDomain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;



    @Transactional
    @Override
    @CacheEvict(value = {"articleCache", "articleCaches"}, allEntries = true, beforeInvocation = true)
    public void addArticle(ContentDomain contentDomain) {
        if (null == contentDomain)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);

        if (StringUtils.isBlank(contentDomain.getTitle()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_CAN_NOT_EMPTY);

        if (contentDomain.getTitle().length() > WebConst.MAX_TITLE_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_IS_TOO_LONG);

        if (StringUtils.isBlank(contentDomain.getContent()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_CAN_NOT_EMPTY);

        if (contentDomain.getContent().length() > WebConst.MAX_CONTENT_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_IS_TOO_LONG);

        // 添加文章
        contentDao.addArticle(contentDomain);

    }

    @Override
    @Cacheable(value = "articleCache", key = "'articleById_' + #p0")
    public ContentDomain getArticleById(Integer cid) {
        if (null == cid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return contentDao.getArticleById(cid);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache", "articleCaches"}, allEntries = true, beforeInvocation = true)
    public void updateArticleById(ContentDomain contentDomain) {
        // 标签和分类
        //String tags = contentDomain.getTags();

        // 更新文章
        contentDao.updateArticleById(contentDomain);
       // int cid = contentDomain.getCid();
//        relationShipDao.deleteRelationShipByCid(cid);
//        metaService.addMetas(cid,tags,Types.TAG.getType());


    }

    @Override
    @Cacheable(value = "articleCaches", key = "'articlesByCond_' + #p1 + 'type_' + #p0.type")
    public PageInfo<ContentDomain> getArticlesByCond(ContentCond contentCond, int pageNum, int pageSize) {
        if (null == contentCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum,pageSize);
        List<ContentDomain> contents = contentDao.getArticleByCond(contentCond);
        PageInfo<ContentDomain> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "articleCaches", key = "'articlesByCond_' + #p1 + 'type_' + #p0.type")
    public PageInfo<ContentDomain> getArticlesByConds(ContentCond contentCond, int pageNum, int pageSize) {
        if (null == contentCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum,pageSize);
        List<ContentDomain> contents = contentDao.getArticleByConds(contentCond);
        PageInfo<ContentDomain> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache","articleCaches"},allEntries = true, beforeInvocation = true)
    public void deleteArticleById(Integer cid) {
        if (null == cid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        // 删除文章
        contentDao.deleteArticleById(cid);

        // 同时要删除该 文章下的所有评论
//        List<CommentDomain> comments = commentDao.getCommentByCId(cid);
//        if (null != comments && comments.size() > 0) {
//            comments.forEach(comment -> {
//                commentDao.deleteComment(comment.getCoid());
//            });


//        // 删除标签和分类关联
//        List<RelationShipDomain> relationShips = relationShipDao.getRelationShipByCid(cid);
//        if (null != relationShips && relationShips.size() > 0) {
//            relationShipDao.deleteRelationShipByCid(cid);
//        }

}

    @Override
    @CacheEvict(value = {"articleCache","articleCaches"}, allEntries = true, beforeInvocation = true)
    public void updateContentByCid(ContentDomain content) {
        if (null != content && null != content.getCid()) {
            contentDao.updateArticleById(content);
        }
    }

//    @Override
//    @Cacheable(value = "articleCache", key = "'articleByCategory_' + #p0")
//    public List<ContentDomain> getArticleByCategory(String category) {
//        if (null == category)
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        return contentDao.getArticleByCategory(category);
//    }

//    @Override
//    @Cacheable(value = "articleCache", key = "'articleByTags_'+ #p0")
//    public List<ContentDomain> getArticleByTags(MetaDomain tags) {
//        if (null == tags)
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
////        List<RelationShipDomain> relationShip = relationShipDao.getRelationShipByMid(tags.getMid());
////        if (null != relationShip && relationShip.size() > 0) {
////            return contentDao.getArticleByTags(relationShip);
////        }
//        return null;
//    }
}
