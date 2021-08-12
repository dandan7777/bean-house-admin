package me.zhengjie.sms.service;

import me.zhengjie.sms.module.SmsBase;

/**
 * @author : Aaron
 *
 * create at:  2021/8/12  10:55
 *
 * description: 短信service
 */
public interface SmsHandler {

  public Object getToken();

  public Object Send(SmsBase smsBase);
}

