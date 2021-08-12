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
import me.zhengjie.modules.bead.domain.BeadHouseInfo;

/**
* @website https://el-admin.vip
* @description /
* @author aaron.hu
* @date 2021-08-01
**/
@Data
public class BedInfoDto implements Serializable {

    /** 主键 */
    private Integer id;

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

    /** 床位编号 */
    private String bedNum;

    /** 所在楼层 */
    private String floor;

    /** 所在房间号 */
    private String room;

    /** 所属养老院 */
    private BeadHouseInfo beadHouseInfo;

    /** 床位价钱 */
    private String bedPrice;

    /** 床位折扣价 */
    private String bedDiscountPrice;

    /** 床位折扣 */
    private String bedDiscount;
}
