package io.github.yuegod.zhishang.component.security.configuration;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;


/**
 * @Author 屈子威
 * @Date 2020/8/31 18:33
 * @description 自定义权限表达式
 */
public class CustomSecurityExpressionRootHandler extends DefaultMethodSecurityExpressionHandler {
    public CustomSecurityExpressionRootHandler(){
        super();
    }
    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation){
        CustomSecurityExpressionRoot root = new CustomSecurityExpressionRoot(authentication);
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(getPermissionEvaluator());
        return root;
    }
}

class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    public CustomSecurityExpressionRoot(Authentication a) {
        super(a);
    }

    public boolean testDecision(String test){
        return true;
    }

    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    public Object getFilterObject() {
        return filterObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    void setThis(Object target) {
        this.target = target;
    }

    public Object getThis() {
        return target;
    }
    public boolean hasPermission(Object permission) {
        try {
            return super.hasPermission(null, null, permission);
        } catch (AccessDeniedException e) {
            return false;
        }
    }

    public boolean checkPermission(Object permission) {
        return super.hasPermission(null, null, permission);
    }

    @Override
    public boolean hasPermission(Object targetId, String targetType, Object permission) {
        try {
            return super.hasPermission(targetId, targetType, permission);
        } catch (AccessDeniedException e) {
            return false;
        }
    }

    public boolean checkPermission(Object targetId, String targetType, Object permission) {
        return super.hasPermission(targetId, targetType, permission);
    }

    @Override
    public boolean hasPermission(Object target, Object permission) {
        try {
            return super.hasPermission(target, permission);
        } catch (AccessDeniedException e) {
            return false;
        }
    }

    public boolean checkPermission(Object target, Object permission) {
        return super.hasPermission(target, permission);
    }
}
