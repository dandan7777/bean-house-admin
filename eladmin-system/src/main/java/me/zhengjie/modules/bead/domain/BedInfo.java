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
import java.sql.Timestamp;
import java.io.Serializable;
import me.zhengjie.base.BaseEntity;

/**
* @website https://el-admin.vip
* @description /
* @author aaron.hu
* @date 2021-08-01
**/
@Entity
@Data
@Table(name="bed_info")
public class BedInfo extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Integer id;

    @Column(name = "tenant_id")
    @ApiModelProperty(value = "租户")
    private String tenantId;

    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private String status;

    @Column(name = "remarks")
    @ApiModelProperty(value = "描述")
    private String remarks;

    @Column(name = "bed_num")
    @ApiModelProperty(value = "床位编号")
    private String bedNum;

    @Column(name = "floor")
    @ApiModelProperty(value = "所在楼层")
    private String floor;

    @Column(name = "room")
    @ApiModelProperty(value = "所在房间号")
    private String room;

//    @Column(name = "bead_house_id")
//    @ApiModelProperty(value = "所属养老院")
//    private Integer beadHouseId;

    /**
     * bed中的bead_house_id字段参考bead_house_info表中的id字段
     */
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "bead_house_id")
    private BeadHouseInfo beadHouseInfo;

    @Column(name = "bed_price")
    @ApiModelProperty(value = "床位价钱")
    private String bedPrice;

    @Column(name = "bed_discount_price")
    @ApiModelProperty(value = "床位折扣价")
    private String bedDiscountPrice;

    @Column(name = "bed_discount")
    @ApiModelProperty(value = "床位折扣")
    private String bedDiscount;

    public void copy(BedInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
