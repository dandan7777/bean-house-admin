package me.zhengjie.sms.service.Impl;

import me.zhengjie.sms.annotation.SmsType;
import me.zhengjie.sms.module.SmsBase;
import me.zhengjie.sms.service.SmsHandler;
import org.springframework.stereotype.Component;

/**
 * @author : Aaron
 *
 * create at:  2021/8/12  10:56
 *
 * description: 极光短信实现
 */
@Component
@SmsType(source = "1")
public class JiguangSms implements SmsHandler {

  @Override
  public Object getToken() {
    return null;
  }

  @Override
  public Object Send(SmsBase smsBase) {
    return null;
  }


}

