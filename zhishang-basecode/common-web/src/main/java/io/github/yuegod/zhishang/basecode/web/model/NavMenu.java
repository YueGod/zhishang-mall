package io.github.yuegod.zhishang.basecode.web.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * element-ui 导航栏数据库结构
 *
 * @param <T>
 */
public class NavMenu<T extends Comparable> implements Comparable<NavMenu> {

    private T value;
    private Set<NavMenu> children = new TreeSet<>(new Comparator<NavMenu>() {
        @Override
        public int compare(NavMenu navMenu, NavMenu t1) {
            return navMenu.compareTo(t1);
        }
    });

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Set<NavMenu> getChildren() {
        return children;
    }

    public void setChildren(Set<NavMenu> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NavMenu<?> that = (NavMenu<?>) o;

        if (getValue() != null ? !getValue().equals(that.getValue()) : that.getValue() != null) return false;
        return getChildren() != null ? getChildren().equals(that.getChildren()) : that.getChildren() == null;
    }

    @Override
    public int hashCode() {
        int result = getValue() != null ? getValue().hashCode() : 0;
        result = 31 * result + (getChildren() != null ? getChildren().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(NavMenu navMenu) {
        return getValue().compareTo(navMenu.getValue());
    }
}
