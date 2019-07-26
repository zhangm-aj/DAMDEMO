/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/24 16:59
 **/
package life.zm.damdemo.damdemo.dto;


import life.zm.damdemo.damdemo.model.MetaDomain;

/**
 * 标签、分类列表
 */
public class MetaDto extends MetaDomain {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
