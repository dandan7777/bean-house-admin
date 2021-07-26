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
package me.zhengjie.modules.bead.service.impl;

import me.zhengjie.modules.bead.domain.BeadHouseInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.bead.repository.BeadHouseInfoRepository;
import me.zhengjie.modules.bead.service.BeadHouseInfoService;
import me.zhengjie.modules.bead.service.dto.BeadHouseInfoDto;
import me.zhengjie.modules.bead.service.dto.BeadHouseInfoQueryCriteria;
import me.zhengjie.modules.bead.service.mapstruct.BeadHouseInfoMapper;
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
* @date 2021-07-25
**/
@Service
@RequiredArgsConstructor
public class BeadHouseInfoServiceImpl implements BeadHouseInfoService {

    private final BeadHouseInfoRepository beadHouseInfoRepository;
    private final BeadHouseInfoMapper beadHouseInfoMapper;

    @Override
    public Map<String,Object> queryAll(BeadHouseInfoQueryCriteria criteria, Pageable pageable){
        Page<BeadHouseInfo> page = beadHouseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(beadHouseInfoMapper::toDto));
    }

    @Override
    public List<BeadHouseInfoDto> queryAll(BeadHouseInfoQueryCriteria criteria){
        return beadHouseInfoMapper.toDto(beadHouseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public BeadHouseInfoDto findById(Integer beadHouseId) {
        BeadHouseInfo beadHouseInfo = beadHouseInfoRepository.findById(beadHouseId).orElseGet(BeadHouseInfo::new);
        ValidationUtil.isNull(beadHouseInfo.getBeadHouseId(),"BeadHouseInfo","beadHouseId",beadHouseId);
        return beadHouseInfoMapper.toDto(beadHouseInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BeadHouseInfoDto create(BeadHouseInfo resources) {
        return beadHouseInfoMapper.toDto(beadHouseInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BeadHouseInfo resources) {
        BeadHouseInfo beadHouseInfo = beadHouseInfoRepository.findById(resources.getBeadHouseId()).orElseGet(BeadHouseInfo::new);
        ValidationUtil.isNull( beadHouseInfo.getBeadHouseId(),"BeadHouseInfo","id",resources.getBeadHouseId());
        beadHouseInfo.copy(resources);
        beadHouseInfoRepository.save(beadHouseInfo);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer beadHouseId : ids) {
            beadHouseInfoRepository.deleteById(beadHouseId);
        }
    }

    @Override
    public void download(List<BeadHouseInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (BeadHouseInfoDto beadHouseInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("养老院名称", beadHouseInfo.getBeadHouseName());
            map.put("院长id", beadHouseInfo.getDeanId());
            map.put("院长名称", beadHouseInfo.getDeanName());
            map.put("养老院评价", beadHouseInfo.getBeadHouseEvaluate());
            map.put("床位数", beadHouseInfo.getBedNum());
            map.put("创建人", beadHouseInfo.getCreateBy());
            map.put("创建时间", beadHouseInfo.getCreateTime());
            map.put("修改人", beadHouseInfo.getUpdateBy());
            map.put("修改时间", beadHouseInfo.getUpdateTime());
            map.put("联系电话", beadHouseInfo.getBeadPhone());
            map.put("养老院地址", beadHouseInfo.getBeadHouseAddress());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}