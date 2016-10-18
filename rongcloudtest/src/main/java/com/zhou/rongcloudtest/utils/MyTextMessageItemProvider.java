package com.zhou.rongcloudtest.utils;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.message.TextMessage;

/**
 * 会话消息item模板
 * Created by zhou on 2016/8/26.
 */
@ProviderTag(messageContent = TextMessage.class, showSummaryWithName = false)
public class MyTextMessageItemProvider extends TextMessageItemProvider {
}
