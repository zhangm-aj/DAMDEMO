/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:30
 **/
package life.zm.damdemo.damdemo.model;

/**
 * 文章表
 */
public class ContentDomain {
    /**
     * 文章的主键编号
     */
    private Integer cid;
    /**
     * 内容所属用户id
     * 外键
     */
    private Integer authorId;
    /**
     * 内容标题
     */
    private String title;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 内容生成时的GMT unix时间戳
     */
    private Integer created;
    /**
     * 内容更改时的GMT unix时间戳
     */
    private Integer modified;
    /**
     * 内容文字
     */
    private String content;


    /**
     * 内容状态
     */
    private String status;
    /**
     * 标签列表
     */
    private String tags;

//    /**
//     * 点击次数
//     */
//    private Integer hits;
//    /**
//     * 内容所属评论数
//     */
//    private Integer commentsNum;
//    /**
//     * 是否允许评论
//     */
//    private Integer allowComment;
//    /**
//     * 是否允许ping
//     */
//    private Integer allowPing;
//    /**
//     * 允许出现在聚合中
//     */
//    private Integer allowFeed;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


//    public Integer getHits() {
//        return hits;
//    }
//
//    public void setHits(Integer hits) {
//        this.hits = hits;
//    }
//
//    public Integer getCommentsNum() {
//        return commentsNum;
//    }
//
//    public void setCommentsNum(Integer commentsNum) {
//        this.commentsNum = commentsNum;
//    }
//
//    public Integer getAllowComment() {
//        return allowComment;
//    }
//
//    public void setAllowComment(Integer allowComment) {
//        this.allowComment = allowComment;
//    }
//
//    public Integer getAllowPing() {
//        return allowPing;
//    }
//
//    public void setAllowPing(Integer allowPing) {
//        this.allowPing = allowPing;
//    }
//
//    public Integer getAllowFeed() {
//        return allowFeed;
//    }
//
//    public void setAllowFeed(Integer allowFeed) {
//        this.allowFeed = allowFeed;
//    }
}
