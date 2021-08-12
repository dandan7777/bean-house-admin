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
package me.zhengjie.modules.bead.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author aaron.hu
* @date 2021-08-01
**/
@Data
public class BeadHouseInfoDto implements Serializable {

    /** 院长id */
    private Integer deanId;

    /** 院长名称 */
    private String deanName;

    /** 床位数 */
    private String bedNum;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改人 */
    private String updateBy;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 主键 */
    private Integer id;

    /** 养老院名称 */
    private String beanName;

    /** 租户 */
    private String tenantId;

    /** 状态 */
    private String status;

    /** 描述 */
    private String remarks;

    /** 养老院接待时间 */
    private String beanTime;

    /** 养老院电话 */
    private String beanTel;

    /** 养老院位置 */
    private String beanAddress;

    /** 养老院经度 */
    private String lng;

    /** 养老院纬度 */
    private String lat;

    /** 养老院省 */
    private String beanProvince;

    /** 养老院市 */
    private String beanCity;

    /** 养老院区 */
    private String beanArea;

    /** 养老院医护数量 */
    private String nurseNum;

    /** 养老院描述 */
    private String beanDesc;

    /** 养老院logo */
    private String beanLogo;

    /** 评价 */
    private String beanHouseEvaluate;
}