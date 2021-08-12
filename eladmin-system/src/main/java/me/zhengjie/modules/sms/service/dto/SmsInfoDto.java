/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.sms.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author aaron.hu
* @date 2021-08-10
**/
@Data
public class SmsInfoDto implements Serializable {

    /** 主键 */
    private Long id;

    /** 短信内容 */
    private String smsContent;

    /** 发件人信息 */
    private String fromUser;

    /** 收件人信息 */
    private String toUser;

    /** 短信类型 */
    private String smsType;

    /** 短信code（验证码信息） */
    private String smsCode;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改人 */
    private String updateBy;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 租户 */
    private String tenantId;

    /** 状态 */
    private String status;

    /** 描述 */
    private String remarks;
}