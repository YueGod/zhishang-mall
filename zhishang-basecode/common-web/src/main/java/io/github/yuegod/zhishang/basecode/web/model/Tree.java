package io.github.yuegod.zhishang.basecode.web.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * element-ui tree数据结构
 *
 * @param <T>
 * @param <KEY>
 */
public class Tree<T extends Comparable, KEY> implements Comparable<Tree> {
    private KEY key;
    private String label;
    private T value;
    private Set<Tree> children = new TreeSet<>(new Comparator<Tree>() {
        @Override
        public int compare(Tree tree, Tree t1) {
            return tree.compareTo(t1);
        }
    });

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Set<Tree> getChildren() {
        return children;
    }

    public void setChildren(Set<Tree> children) {
        this.children = children;
    }

    public KEY getKey() {
        return key;
    }

    public void setKey(KEY key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int compareTo(Tree tree) {
        return getValue().compareTo(tree.getValue());
    }
}
