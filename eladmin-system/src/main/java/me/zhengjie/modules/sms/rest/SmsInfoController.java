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
package me.zhengjie.modules.sms.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.sms.domain.SmsInfo;
import me.zhengjie.modules.sms.service.SmsInfoService;
import me.zhengjie.modules.sms.service.dto.SmsInfoQueryCriteria;
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
@Api(tags = "sms管理")
@RequestMapping("/api/smsInfo")
public class SmsInfoController {

    private final SmsInfoService smsInfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('smsInfo:list')")
    public void download(HttpServletResponse response, SmsInfoQueryCriteria criteria) throws IOException {
        smsInfoService.download(smsInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询sms")
    @ApiOperation("查询sms")
    @PreAuthorize("@el.check('smsInfo:list')")
    public ResponseEntity<Object> query(SmsInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(smsInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增sms")
    @ApiOperation("新增sms")
    @PreAuthorize("@el.check('smsInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody SmsInfo resources){
        return new ResponseEntity<>(smsInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改sms")
    @ApiOperation("修改sms")
    @PreAuthorize("@el.check('smsInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody SmsInfo resources){
        smsInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除sms")
    @ApiOperation("删除sms")
    @PreAuthorize("@el.check('smsInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        smsInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}