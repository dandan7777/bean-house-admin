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
package me.zhengjie.modules.order.service.impl;

import me.zhengjie.modules.order.domain.TbOrderInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.order.repository.TbOrderInfoRepository;
import me.zhengjie.modules.order.service.TbOrderInfoService;
import me.zhengjie.modules.order.service.dto.TbOrderInfoDto;
import me.zhengjie.modules.order.service.dto.TbOrderInfoQueryCriteria;
import me.zhengjie.modules.order.service.mapstruct.TbOrderInfoMapper;
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
public class TbOrderInfoServiceImpl implements TbOrderInfoService {

    private final TbOrderInfoRepository tbOrderInfoRepository;
    private final TbOrderInfoMapper tbOrderInfoMapper;

    @Override
    public Map<String,Object> queryAll(TbOrderInfoQueryCriteria criteria, Pageable pageable){
        Page<TbOrderInfo> page = tbOrderInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tbOrderInfoMapper::toDto));
    }

    @Override
    public List<TbOrderInfoDto> queryAll(TbOrderInfoQueryCriteria criteria){
        return tbOrderInfoMapper.toDto(tbOrderInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public TbOrderInfoDto findById(Integer id) {
        TbOrderInfo tbOrderInfo = tbOrderInfoRepository.findById(id).orElseGet(TbOrderInfo::new);
        ValidationUtil.isNull(tbOrderInfo.getId(),"TbOrderInfo","id",id);
        return tbOrderInfoMapper.toDto(tbOrderInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TbOrderInfoDto create(TbOrderInfo resources) {
        return tbOrderInfoMapper.toDto(tbOrderInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TbOrderInfo resources) {
        TbOrderInfo tbOrderInfo = tbOrderInfoRepository.findById(resources.getId()).orElseGet(TbOrderInfo::new);
        ValidationUtil.isNull( tbOrderInfo.getId(),"TbOrderInfo","id",resources.getId());
        tbOrderInfo.copy(resources);
        tbOrderInfoRepository.save(tbOrderInfo);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            tbOrderInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TbOrderInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TbOrderInfoDto tbOrderInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("订单号", tbOrderInfo.getOrderId());
            map.put("订单类型", tbOrderInfo.getOrderType());
            map.put("支付方式", tbOrderInfo.getOrderPayType());
            map.put("支付金额", tbOrderInfo.getOrderPrice());
            map.put("支付状态", tbOrderInfo.getOrderStatus());
            map.put("购买人id", tbOrderInfo.getPayUserId());
            map.put("购买人姓名", tbOrderInfo.getPayUserName());
            map.put("购买人联系方式", tbOrderInfo.getPayPhone());
            map.put("创建人", tbOrderInfo.getCreateBy());
            map.put("创建时间", tbOrderInfo.getCreateTime());
            map.put("修改人", tbOrderInfo.getUpdateBy());
            map.put("修改时间", tbOrderInfo.getUpdateTime());
            map.put("租户", tbOrderInfo.getTenantId());
            map.put("状态", tbOrderInfo.getStatus());
            map.put("描述", tbOrderInfo.getRemarks());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}