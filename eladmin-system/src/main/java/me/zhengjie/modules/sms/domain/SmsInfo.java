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
package me.zhengjie.modules.sms.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author aaron.hu
* @date 2021-08-10
**/
@Entity
@Data
@Table(name="tb_sms_info")
public class SmsInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "sms_content")
    @ApiModelProperty(value = "短信内容")
    private String smsContent;

    @Column(name = "from_user")
    @ApiModelProperty(value = "发件人信息")
    private String fromUser;

    @Column(name = "to_user")
    @ApiModelProperty(value = "收件人信息")
    private String toUser;

    @Column(name = "sms_type")
    @ApiModelProperty(value = "短信类型")
    private String smsType;

    @Column(name = "sms_code")
    @ApiModelProperty(value = "短信code（验证码信息）")
    private String smsCode;

    @Column(name = "create_by")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_by")
    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @Column(name = "update_time")
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;

    @Column(name = "tenant_id")
    @ApiModelProperty(value = "租户")
    private String tenantId;

    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private String status;

    @Column(name = "remarks")
    @ApiModelProperty(value = "描述")
    private String remarks;

    public void copy(SmsInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}