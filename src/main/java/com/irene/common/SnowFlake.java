package com.irene.common;

/**
 * @BelongsProject: irene
 * @BelongsPackage: com.irene.common
 * @Auther: i.gy@outlook.com
 * @Date: 2022/5/7 17:16
 * @Description:
 * @since JDK 1.8
 */
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 使用64位
 * 第一位为0不使用
 * 第2到42位，毫秒时间戳，总共41位 最大值 2199023255551
 * 第43位到52位为机器码  总共10位 最大值1023
 * 第53位到64位为序列号  总共12位 最大值4095
 * 产生一个long类型
 * @author lwh
 * @date 2022/1/19
 */
public final class SnowFlake {

    private long workerId;

    public SnowFlake(long workerId) {
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException("workerId 只能 0<workerId<1023");
        }
        this.workerId = workerId;
    }

    private final static int MAX_SEQUENCE = 4095;
    private final static int MAX_WORKER_ID = 1023;
    private final static int SEQUENCE_BIT_LEN = 12;
    private final static int WORKER_ID_BIT_LEN = 10;
    private final static int WORKER_ID_SHIFT = SEQUENCE_BIT_LEN;
    private final static int Time_SHIFT = WORKER_ID_BIT_LEN + SEQUENCE_BIT_LEN;

    private long lastTime;
    private int sequence;

    public synchronized Long next() {
        //当前时间
        long currentTime = System.currentTimeMillis();
        //时间回拨的问题
        if (lastTime > currentTime) {
            throw new RuntimeException("时间回拨");
        }
        //当大于最大值时
        if (sequence >= MAX_SEQUENCE) {
            if (currentTime == lastTime) {
                //等待1毫秒
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //重新赋值
                sequence = 0;
                currentTime = System.currentTimeMillis();
            }
        }
        long woker = workerId << WORKER_ID_SHIFT;
        final long time = currentTime << Time_SHIFT;
        return time | woker | ++sequence;

    }
}
