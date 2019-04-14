import java.util.*;
import java.io.*;

/***
 * The node for the tree
 * 
 * @param <T>
 */
class Node<T> {
    String nodeLabel = ""; // attribute Name
    String linkLabel = ""; // atribute values of parent
    private T data = null;
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;

    /**
     * initialize the node
     * 
     * @param data
     */
    public Node(T data) {
        this.data = data;
        this.nodeLabel = (String) data;
        if (this.parent != null)
            this.linkLabel = (String) this.parent.getData();
    }// end consstrutor

    /**
     * add child to the node
     * 
     * @param child
     */
    void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }// end function

    /**
     * add children
     * 
     * @param children
     */
    void addChildren(List<Node<T>> children) {
        children.forEach(child -> child.setParent(this));
        this.children.addAll(children);
    }// end function

    /**
     * Set data of the node
     * 
     * @param data
     */
    void setData(T data) {
        this.data = data;
    }// end function

    /**
     * set parent
     * 
     * @param parent
     */
    void setParent(Node<T> parent) {
        this.parent = parent;
    }// end function

    /**
     * get parent node
     * 
     * @return parent node
     */
    Node<T> getParent() {
        return parent;
    }// end function

    /**
     * get children
     * 
     * @return list children nodes
     */
    List<Node<T>> getChildren() {
        return children;
    }// end function

    /**
     * get the data of the node
     */
    T getData() {
        return data;
    }// end function
}// end class
