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
package me.zhengjie.modules.bead.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.bead.domain.BeadHouseInfo;
import me.zhengjie.modules.bead.service.BeadHouseInfoService;
import me.zhengjie.modules.bead.service.dto.BeadHouseInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author aaron.hu
* @date 2021-08-01
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "养老院管理管理")
@RequestMapping("/api/beadHouseInfo")
public class BeadHouseInfoController {

    private final BeadHouseInfoService beadHouseInfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('beadHouseInfo:list')")
    public void download(HttpServletResponse response, BeadHouseInfoQueryCriteria criteria) throws IOException {
        beadHouseInfoService.download(beadHouseInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询养老院管理")
    @ApiOperation("查询养老院管理")
    @PreAuthorize("@el.check('beadHouseInfo:list','bedInfo:list')")
    public ResponseEntity<Object> query(BeadHouseInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(beadHouseInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增养老院管理")
    @ApiOperation("新增养老院管理")
    @PreAuthorize("@el.check('beadHouseInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody BeadHouseInfo resources){
        return new ResponseEntity<>(beadHouseInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改养老院管理")
    @ApiOperation("修改养老院管理")
    @PreAuthorize("@el.check('beadHouseInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody BeadHouseInfo resources){
        beadHouseInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除养老院管理")
    @ApiOperation("删除养老院管理")
    @PreAuthorize("@el.check('beadHouseInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        beadHouseInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
