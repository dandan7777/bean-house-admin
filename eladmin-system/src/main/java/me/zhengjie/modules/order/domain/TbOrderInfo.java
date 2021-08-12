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
package me.zhengjie.modules.order.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
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
@Table(name="tb_order_info")
public class TbOrderInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Integer id;

    @Column(name = "order_id")
    @ApiModelProperty(value = "订单号")
    private String orderId;

    @Column(name = "order_type")
    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    @Column(name = "order_pay_type")
    @ApiModelProperty(value = "支付方式")
    private Integer orderPayType;

    @Column(name = "order_price")
    @ApiModelProperty(value = "支付金额")
    private String orderPrice;

    @Column(name = "order_status")
    @ApiModelProperty(value = "支付状态")
    private String orderStatus;

    @Column(name = "pay_user_id")
    @ApiModelProperty(value = "购买人id")
    private Integer payUserId;

    @Column(name = "pay_user_name")
    @ApiModelProperty(value = "购买人姓名")
    private String payUserName;

    @Column(name = "pay_phone")
    @ApiModelProperty(value = "购买人联系方式")
    private String payPhone;

    @Column(name = "create_by")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @Column(name = "create_time")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_by")
    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @Column(name = "update_time")
    @UpdateTimestamp
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

    public void copy(TbOrderInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}