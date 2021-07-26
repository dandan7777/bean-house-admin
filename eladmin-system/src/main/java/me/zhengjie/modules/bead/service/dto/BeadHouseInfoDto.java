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
* @date 2021-07-25
**/
@Data
public class BeadHouseInfoDto implements Serializable {

    /** id */
    private Integer beadHouseId;

    /** 养老院名称 */
    private String beadHouseName;

    /** 院长id */
    private Integer deanId;

    /** 院长名称 */
    private String deanName;

    /** 养老院评价 */
    private String beadHouseEvaluate;

    /** 床位数 */
    private Integer bedNum;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改人 */
    private String updateBy;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 联系电话 */
    private String beadPhone;

    /** 养老院地址 */
    private String beadHouseAddress;
}