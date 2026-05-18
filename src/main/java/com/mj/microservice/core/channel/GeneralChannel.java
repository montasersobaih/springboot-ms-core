package com.mj.microservice.core.channel;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface GeneralChannel {

    MessageChannel messageChannel();

    SubscribableChannel subscribableChannel();

    SubscribableChannel subscribableChannelError();
}
