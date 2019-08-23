/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/26 15:15
 **/
package life.zm.damdemo.damdemo.dto.cond;

/**
 * 文章查询条件
 */
public class ContentCond {
    /**
     * 标签
     */
    private String tags;
    /*
    * 作者
    * */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String title;




    public String getTag() {
        return tags;
    }

    public void setTag(String tag) {
        this.tags = tag;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




}
