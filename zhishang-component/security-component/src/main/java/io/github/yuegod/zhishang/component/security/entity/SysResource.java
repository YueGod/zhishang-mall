package io.github.yuegod.zhishang.component.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.yuegod.zhishang.basecode.service.BaseEntity;
import lombok.Data;

/**
 * @author 屈子威
 * @date 2020/8/31 6:25
 * @description
 */
@Data
@TableName("t_resoource")
public class SysResource extends BaseEntity<Integer, SysResource> {
    private String resource;
}
