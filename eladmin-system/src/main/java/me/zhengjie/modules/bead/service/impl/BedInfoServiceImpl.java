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

import me.zhengjie.modules.bead.domain.BedInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.bead.repository.BedInfoRepository;
import me.zhengjie.modules.bead.service.BedInfoService;
import me.zhengjie.modules.bead.service.dto.BedInfoDto;
import me.zhengjie.modules.bead.service.dto.BedInfoQueryCriteria;
import me.zhengjie.modules.bead.service.mapstruct.BedInfoMapper;
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
public class BedInfoServiceImpl implements BedInfoService {

    private final BedInfoRepository bedInfoRepository;
    private final BedInfoMapper bedInfoMapper;

    @Override
    public Map<String,Object> queryAll(BedInfoQueryCriteria criteria, Pageable pageable){
        Page<BedInfo> page = bedInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(bedInfoMapper::toDto));
    }

    @Override
    public List<BedInfoDto> queryAll(BedInfoQueryCriteria criteria){
        return bedInfoMapper.toDto(bedInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public BedInfoDto findById(Integer id) {
        BedInfo bedInfo = bedInfoRepository.findById(id).orElseGet(BedInfo::new);
        ValidationUtil.isNull(bedInfo.getId(),"BedInfo","id",id);
        return bedInfoMapper.toDto(bedInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BedInfoDto create(BedInfo resources) {
        return bedInfoMapper.toDto(bedInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BedInfo resources) {
        BedInfo bedInfo = bedInfoRepository.findById(resources.getId()).orElseGet(BedInfo::new);
        ValidationUtil.isNull( bedInfo.getId(),"BedInfo","id",resources.getId());
        bedInfo.copy(resources);
        bedInfoRepository.save(bedInfo);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            bedInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<BedInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (BedInfoDto bedInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", bedInfo.getCreateBy());
            map.put("创建时间", bedInfo.getCreateTime());
            map.put("修改人", bedInfo.getUpdateBy());
            map.put("修改时间", bedInfo.getUpdateTime());
            map.put("租户", bedInfo.getTenantId());
            map.put("状态", bedInfo.getStatus());
            map.put("描述", bedInfo.getRemarks());
            map.put("床位编号", bedInfo.getBedNum());
            map.put("所在楼层", bedInfo.getFloor());
            map.put("所在房间号", bedInfo.getRoom());
            map.put("所属养老院", bedInfo.getBeadHouseInfo().getBeanName());
            map.put("床位价钱", bedInfo.getBedPrice());
            map.put("床位折扣价", bedInfo.getBedDiscountPrice());
            map.put("床位折扣", bedInfo.getBedDiscount());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
