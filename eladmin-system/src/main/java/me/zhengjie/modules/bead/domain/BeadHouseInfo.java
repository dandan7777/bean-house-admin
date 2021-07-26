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
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author aaron.hu
* @date 2021-07-25
**/
@Entity
@Data
@Table(name="bead_house_info")
public class BeadHouseInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bead_house_id")
    @ApiModelProperty(value = "id")
    private Integer beadHouseId;

    @Column(name = "bead_house_name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "养老院名称")
    private String beadHouseName;

    @Column(name = "dean_id")
    @ApiModelProperty(value = "院长id")
    private Integer deanId;

    @Column(name = "dean_name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "院长名称")
    private String deanName;

    @Column(name = "bead_house_evaluate",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "养老院评价")
    private String beadHouseEvaluate;

    @Column(name = "bed_num",nullable = false)
    @NotNull
    @ApiModelProperty(value = "床位数")
    private Integer bedNum;

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

    @Column(name = "bead_phone",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "联系电话")
    private String beadPhone;

    @Column(name = "bead_house_address")
    @ApiModelProperty(value = "养老院地址")
    private String beadHouseAddress;

    public void copy(BeadHouseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}