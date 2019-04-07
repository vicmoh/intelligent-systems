import java.util.*;
import java.io.*;

class Node<T> {
  private T data = null;
  private List<Node<T>> children = new ArrayList<>();
  private Node<T> parent = null;
  public Node(T data) {
    this.data = data;
  }
  Node<T> addChild(Node<T> child) {
    child.setParent(this);
    this.children.add(child);
    return child;
  }
  void addChildren(List<Node<T>> children) {
    children.forEach(each -> each.setParent(this));
    this.children.addAll(children);
  }
  List<Node<T>> getChildren() {
    return children;
  }
  T getData() {
    return data;
  }
  void setData(T data) {
    this.data = data;
  }
  void setParent(Node<T> parent) {
    this.parent = parent;
  }
  Node<T> getParent() {
    return parent;
  }
}
/*class Node {
  String attributeName;
  String label;
  String parentLabel;
  int majority;
  Node parent;

  Node(int m){
    this.majority = m;
  }
  Node(Attribute at){
    this.label = "0";
    this.parentLabel = "0";
    this.attributeName = at.name;
  }
  Node(String l){
    this.label = l;
  }

}*/
