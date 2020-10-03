package io.github.yuegod.zhishang.basecode.api.base;


import java.io.Serializable;
import java.util.Objects;


public abstract class BaseModel<ID> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ID id;
    private boolean create;

    public boolean isCreate() {
        return null == id ? true : false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel<?> baseModel = (BaseModel<?>) o;
        return Objects.equals(id, baseModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
