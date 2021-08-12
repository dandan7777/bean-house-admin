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
package me.zhengjie.modules.sms.service.impl;

import me.zhengjie.modules.sms.domain.SmsInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.sms.repository.SmsInfoRepository;
import me.zhengjie.modules.sms.service.SmsInfoService;
import me.zhengjie.modules.sms.service.dto.SmsInfoDto;
import me.zhengjie.modules.sms.service.dto.SmsInfoQueryCriteria;
import me.zhengjie.modules.sms.service.mapstruct.SmsInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author aaron.hu
* @date 2021-08-10
**/
@Service
@RequiredArgsConstructor
public class SmsInfoServiceImpl implements SmsInfoService {

    private final SmsInfoRepository smsInfoRepository;
    private final SmsInfoMapper smsInfoMapper;

    @Override
    public Map<String,Object> queryAll(SmsInfoQueryCriteria criteria, Pageable pageable){
        Page<SmsInfo> page = smsInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(smsInfoMapper::toDto));
    }

    @Override
    public List<SmsInfoDto> queryAll(SmsInfoQueryCriteria criteria){
        return smsInfoMapper.toDto(smsInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public SmsInfoDto findById(Long id) {
        SmsInfo smsInfo = smsInfoRepository.findById(id).orElseGet(SmsInfo::new);
        ValidationUtil.isNull(smsInfo.getId(),"SmsInfo","id",id);
        return smsInfoMapper.toDto(smsInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SmsInfoDto create(SmsInfo resources) {
        return smsInfoMapper.toDto(smsInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SmsInfo resources) {
        SmsInfo smsInfo = smsInfoRepository.findById(resources.getId()).orElseGet(SmsInfo::new);
        ValidationUtil.isNull( smsInfo.getId(),"SmsInfo","id",resources.getId());
        smsInfo.copy(resources);
        smsInfoRepository.save(smsInfo);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            smsInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<SmsInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SmsInfoDto smsInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("短信内容", smsInfo.getSmsContent());
            map.put("发件人信息", smsInfo.getFromUser());
            map.put("收件人信息", smsInfo.getToUser());
            map.put("短信类型", smsInfo.getSmsType());
            map.put("短信code（验证码信息）", smsInfo.getSmsCode());
            map.put("创建人", smsInfo.getCreateBy());
            map.put("创建时间", smsInfo.getCreateTime());
            map.put("修改人", smsInfo.getUpdateBy());
            map.put("修改时间", smsInfo.getUpdateTime());
            map.put("租户", smsInfo.getTenantId());
            map.put("状态", smsInfo.getStatus());
            map.put("描述", smsInfo.getRemarks());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}