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
package me.zhengjie.modules.bead.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import me.zhengjie.base.BaseEntity;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author aaron.hu
* @date 2021-08-01
**/
@Entity
@Data
@Table(name="bead_house_info")
public class BeadHouseInfo extends BaseEntity implements Serializable {

    @Column(name = "dean_id")
    @ApiModelProperty(value = "院长id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deanId;

    @Column(name = "dean_name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "院长名称")
    private String deanName;

    @Column(name = "bed_num",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "床位数")
    private String bedNum;

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bean_name")
    @ApiModelProperty(value = "养老院名称")
    private String beanName;

    @Column(name = "tenant_id")
    @ApiModelProperty(value = "租户")
    private String tenantId;

    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private String status;

    @Column(name = "remarks")
    @ApiModelProperty(value = "描述")
    private String remarks;

    @Column(name = "bean_time")
    @ApiModelProperty(value = "养老院接待时间")
    private String beanTime;

    @Column(name = "bean_tel")
    @ApiModelProperty(value = "养老院电话")
    private String beanTel;

    @Column(name = "bean_address")
    @ApiModelProperty(value = "养老院位置")
    private String beanAddress;

    @Column(name = "lng")
    @ApiModelProperty(value = "养老院经度")
    private String lng;

    @Column(name = "lat")
    @ApiModelProperty(value = "养老院纬度")
    private String lat;

    @Column(name = "bean_province")
    @ApiModelProperty(value = "养老院省")
    private String beanProvince;

    @Column(name = "bean_city")
    @ApiModelProperty(value = "养老院市")
    private String beanCity;

    @Column(name = "bean_area")
    @ApiModelProperty(value = "养老院区")
    private String beanArea;

    @Column(name = "nurse_num")
    @ApiModelProperty(value = "养老院医护数量")
    private String nurseNum;

    @Column(name = "bean_desc")
    @ApiModelProperty(value = "养老院描述")
    private String beanDesc;

    @Column(name = "bean_logo")
    @ApiModelProperty(value = "养老院logo")
    private String beanLogo;

    @Column(name = "bean_house_evaluate")
    @ApiModelProperty(value = "评价")
    private String beanHouseEvaluate;

    public void copy(BeadHouseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
