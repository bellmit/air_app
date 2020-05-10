package cn.stylefeng.guns.pojo;

import java.util.List;

public class KnowledgeNode extends Knowledge {
    private List<KnowledgeNode> children;

    public KnowledgeNode() {
    }

    public KnowledgeNode(List<KnowledgeNode> children) {
        this.children = children;
    }

    public List<KnowledgeNode> getChildren() {
        return children;
    }

    public void setChildren(List<KnowledgeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "KnowledgeNode{" +
                "children=" + children +
                '}';
    }
}
