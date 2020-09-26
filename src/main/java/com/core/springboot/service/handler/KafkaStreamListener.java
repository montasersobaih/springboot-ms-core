package com.core.springboot.service.handler;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 2/9/19
 */

public interface KafkaStreamListener<T> {

    void listener(T t);
}