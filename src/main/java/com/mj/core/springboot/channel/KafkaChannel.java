package com.mj.core.springboot.channel;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaChannel {

    MessageChannel messageChannel();

    SubscribableChannel subscribableChannel();

    SubscribableChannel subscribableChannelError();
}
