package io.github.yuegod.zhishang.basecode.web.model;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * element-ui 带复选框的tree结构
 *
 * @param <T>
 * @param <K>
 */
public class CheckboxTree<T, K> {
    private Set<K> checkedKeys = new HashSet<>();
    private Set<T> data = new TreeSet<>();

    public Set<K> getCheckedKeys() {
        return checkedKeys;
    }

    public void setCheckedKeys(Set<K> checkedKeys) {
        this.checkedKeys = checkedKeys;
    }

    public Set<T> getData() {
        return data;
    }

    public void setData(Set<T> data) {
        this.data = data;
    }
}
