package cn.stylefeng.guns.pojo;

import cn.stylefeng.guns.pojos.AddressXz;

import java.util.List;

public class AddressNode extends AddressXz {

    private List<AddressNode> children;

    public AddressNode() {
    }

    public AddressNode(List<AddressNode> children) {
        this.children = children;
    }

    public List<AddressNode> getChildren() {
        return children;
    }

    public void setChildren(List<AddressNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AddressNode{" +
                "children=" + children +
                '}';
    }
}
