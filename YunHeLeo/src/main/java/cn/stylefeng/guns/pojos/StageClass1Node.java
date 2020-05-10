package cn.stylefeng.guns.pojos;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
public class StageClass1Node extends StageClass1 {

    private List<StageClass1Node> children;

    public List<StageClass1Node> getChildren() {
        return children;
    }

    public void setChildren(List<StageClass1Node> children) {
        this.children = children;
    }
}
