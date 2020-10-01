package io.github.yuegod.zhishang.basecode.api.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author 屈子威
 * @Date 2020/8/28 16:59
 * @description 线程池工具类
 */
public class ThreadPoolUtils {

    /**
     * 采用丢弃策略
     *
     * @param nThread
     * @return
     */
    public static ExecutorService newFixedThreadPoolForDiscardPolicy(int nThread) {
        return new ThreadPoolExecutor(nThread, nThread * 2,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.DiscardPolicy());
    }
}
