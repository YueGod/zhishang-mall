package io.github.yuegod.zhishang.basecode.service;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 屈子威
 */
@Getter
@Setter
@ToString
public abstract class BaseEntity<ID,T extends BaseEntity<ID,T>> extends Model<T> implements Serializable {

    @TableId(type = IdType.AUTO)
    private ID id;

}
