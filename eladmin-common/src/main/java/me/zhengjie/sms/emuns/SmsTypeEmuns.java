package me.zhengjie.sms.emuns;

/**
 * @author : Aaron
 *
 * create at:  2021/8/12  10:53
 *
 * description: 短信枚举
 */
public enum SmsTypeEmuns {
  JIGUANG("1","极光");
  private String source;
  private String desc;


  SmsTypeEmuns(String source, String desc) {
    this.source = source;
    this.desc = desc;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
