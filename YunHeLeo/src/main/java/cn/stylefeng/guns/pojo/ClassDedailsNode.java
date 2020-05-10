package cn.stylefeng.guns.pojo;

import cn.stylefeng.guns.pojos.ClassDedails;

import java.util.List;

public class ClassDedailsNode extends ClassDedails {

    private List<ClassDedailsNode> children;

    public ClassDedailsNode() {
    }

    public ClassDedailsNode(List<ClassDedailsNode> children) {
        this.children = children;
    }

    public List<ClassDedailsNode> getChildren() {
        return children;
    }

    public void setChildren(List<ClassDedailsNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ClassDedailsNode{" +
                "children=" + children +
                '}';
    }
}
