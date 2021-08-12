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

import me.zhengjie.modules.bead.domain.BedAssort;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.bead.repository.BedAssortRepository;
import me.zhengjie.modules.bead.service.BedAssortService;
import me.zhengjie.modules.bead.service.dto.BedAssortDto;
import me.zhengjie.modules.bead.service.dto.BedAssortQueryCriteria;
import me.zhengjie.modules.bead.service.mapstruct.BedAssortMapper;
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
* @date 2021-08-01
**/
@Service
@RequiredArgsConstructor
public class BedAssortServiceImpl implements BedAssortService {

    private final BedAssortRepository bedAssortRepository;
    private final BedAssortMapper bedAssortMapper;

    @Override
    public Map<String,Object> queryAll(BedAssortQueryCriteria criteria, Pageable pageable){
        Page<BedAssort> page = bedAssortRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bedAssortMapper::toDto));
    }

    @Override
    public List<BedAssortDto> queryAll(BedAssortQueryCriteria criteria){
        return bedAssortMapper.toDto(bedAssortRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public BedAssortDto findById(Integer id) {
        BedAssort bedAssort = bedAssortRepository.findById(id).orElseGet(BedAssort::new);
        ValidationUtil.isNull(bedAssort.getId(),"BedAssort","id",id);
        return bedAssortMapper.toDto(bedAssort);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BedAssortDto create(BedAssort resources) {
        return bedAssortMapper.toDto(bedAssortRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BedAssort resources) {
        BedAssort bedAssort = bedAssortRepository.findById(resources.getId()).orElseGet(BedAssort::new);
        ValidationUtil.isNull( bedAssort.getId(),"BedAssort","id",resources.getId());
        bedAssort.copy(resources);
        bedAssortRepository.save(bedAssort);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            bedAssortRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<BedAssortDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (BedAssortDto bedAssort : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", bedAssort.getCreateBy());
            map.put("创建时间", bedAssort.getCreateTime());
            map.put("修改人", bedAssort.getUpdateBy());
            map.put("修改时间", bedAssort.getUpdateTime());
            map.put("租户", bedAssort.getTenantId());
            map.put("状态", bedAssort.getStatus());
            map.put("描述", bedAssort.getRemarks());
            map.put("设施名称", bedAssort.getAssortName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}