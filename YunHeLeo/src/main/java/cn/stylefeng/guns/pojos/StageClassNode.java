package cn.stylefeng.guns.pojos;

import cn.stylefeng.guns.pojo.KnowledgeNode;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
public class StageClassNode extends StageClass {

    private List<StageClassNode> children;

    public List<StageClassNode> getChildren() {
        return children;
    }

    public void setChildren(List<StageClassNode> children) {
        this.children = children;
    }
}
