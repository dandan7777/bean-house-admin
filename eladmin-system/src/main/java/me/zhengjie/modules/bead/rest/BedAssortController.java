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
import me.zhengjie.modules.bead.domain.BedAssort;
import me.zhengjie.modules.bead.service.BedAssortService;
import me.zhengjie.modules.bead.service.dto.BedAssortQueryCriteria;
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
@Api(tags = "床位配套管理")
@RequestMapping("/api/bedAssort")
public class BedAssortController {

    private final BedAssortService bedAssortService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('bedAssort:list')")
    public void download(HttpServletResponse response, BedAssortQueryCriteria criteria) throws IOException {
        bedAssortService.download(bedAssortService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询床位配套")
    @ApiOperation("查询床位配套")
    @PreAuthorize("@el.check('bedAssort:list')")
    public ResponseEntity<Object> query(BedAssortQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(bedAssortService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增床位配套")
    @ApiOperation("新增床位配套")
    @PreAuthorize("@el.check('bedAssort:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody BedAssort resources){
        return new ResponseEntity<>(bedAssortService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改床位配套")
    @ApiOperation("修改床位配套")
    @PreAuthorize("@el.check('bedAssort:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody BedAssort resources){
        bedAssortService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除床位配套")
    @ApiOperation("删除床位配套")
    @PreAuthorize("@el.check('bedAssort:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        bedAssortService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}