package io.github.yuegod.zhishang.basecode.web.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * element-ui穿梭框数据结构
 *
 * @param <T>
 * @param <KEY>
 */
public class Transfer<T extends Comparable, KEY> {


    private Set<TransferData> data = new TreeSet<>(new Comparator<TransferData>() {
        @Override
        public int compare(TransferData tTransferData, TransferData t1) {
            return tTransferData.compareTo(t1);
        }
    });
    private Set<KEY> checkedKeys = new HashSet<>();

    public class TransferData implements Comparable<TransferData> {
        private KEY key;
        private String label;
        private boolean disabled;
        private T value;


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

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public int compareTo(TransferData transferData) {
            return getValue().compareTo(transferData.getValue());
        }
    }

    public Transfer() {
    }

    public Set<TransferData> getData() {
        return data;
    }

    public void setData(Set<TransferData> data) {
        this.data = data;
    }

    public Set<KEY> getCheckedKeys() {
        return checkedKeys;
    }

    public void setCheckedKeys(Set<KEY> checkedKeys) {
        this.checkedKeys = checkedKeys;
    }
}
