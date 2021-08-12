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
package me.zhengjie.modules.order.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.order.domain.TbOrderInfo;
import me.zhengjie.modules.order.service.TbOrderInfoService;
import me.zhengjie.modules.order.service.dto.TbOrderInfoQueryCriteria;
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
* @date 2021-08-10
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "order管理")
@RequestMapping("/api/tbOrderInfo")
public class TbOrderInfoController {

    private final TbOrderInfoService tbOrderInfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tbOrderInfo:list')")
    public void download(HttpServletResponse response, TbOrderInfoQueryCriteria criteria) throws IOException {
        tbOrderInfoService.download(tbOrderInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询order")
    @ApiOperation("查询order")
    @PreAuthorize("@el.check('tbOrderInfo:list')")
    public ResponseEntity<Object> query(TbOrderInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tbOrderInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增order")
    @ApiOperation("新增order")
    @PreAuthorize("@el.check('tbOrderInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TbOrderInfo resources){
        return new ResponseEntity<>(tbOrderInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改order")
    @ApiOperation("修改order")
    @PreAuthorize("@el.check('tbOrderInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TbOrderInfo resources){
        tbOrderInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除order")
    @ApiOperation("删除order")
    @PreAuthorize("@el.check('tbOrderInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        tbOrderInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}