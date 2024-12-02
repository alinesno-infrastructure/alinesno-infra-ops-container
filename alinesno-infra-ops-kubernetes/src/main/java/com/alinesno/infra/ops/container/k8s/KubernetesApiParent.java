package com.alinesno.infra.ops.container.k8s;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Kubernetes API父类
 * 提供一些通用的方法和工具函数
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public abstract class KubernetesApiParent {

    /**
     * 时间格式化器
     */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final long MEGABYTE = 1024L * 1024L;
    private static final long GIGABYTE = 1024L * 1024L * 1024L;

    /**
     * 将字节转换为可读的字符串
     * @param bytes 字节数
     * @return 可读的字符串表示
     */
    public static String convert(double bytes) {
        if (bytes < MEGABYTE) {
            return bytes + " B";
        } else if (bytes < GIGABYTE) {
            double megabytes = bytes / MEGABYTE;
            return String.format("%.2f MiB", megabytes);
        } else {
            double gigabytes = bytes / GIGABYTE;
            return String.format("%.2f GiB", gigabytes);
        }
    }

    /**
     * 格式化内存大小
     * @param bytes 字节数
     * @return 格式化后的字符串
     */
    public static String formatMemory(double bytes) {
        double memory = bytes;
        String unit = "B";

        if (memory > 1024) {
            memory /= 1024;
            unit = "KiB";
        }

        if (memory > 1024) {
            memory /= 1024;
            unit = "MiB";
        }

        if (memory > 1024) {
            memory /= 1024;
            unit = "GiB";
        }

        return String.format("%.2f %s", memory, unit);
    }

    /**
     * 计算时间距离当前时间的相对时间
     * @param date 时间对象
     * @return 相对时间字符串
     */
    public static String fromAgo(Date date) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        Duration duration = Duration.between(localDateTime, now);

        long seconds = Math.abs(duration.getSeconds());
        long minutes = Math.abs(duration.toMinutes());
        long hours = Math.abs(duration.toHours());
        long days = Math.abs(duration.toDays());
        long months = Math.abs(duration.toDays() / 30);
        long years = Math.abs(duration.toDays() / 365);

        if (seconds < 60) {
            return seconds + " second";
        } else if (minutes < 60) {
            return minutes + " minute";
        } else if (hours < 24) {
            return hours + " hour";
        } else if (days < 30) {
            return days + " day";
        } else if (months < 12) {
            return months + " month";
        } else {
            return years + " year";
        }
    }
}
