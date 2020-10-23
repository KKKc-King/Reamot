package com.reamot.ex.service.impl;

import com.reamot.ex.entity.Pharmacist;
import com.reamot.ex.mapper.PharmacistMapper;
import com.reamot.ex.service.IPharmacistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maqh
 * @since 2020-10-18
 */
@Service
public class PharmacistServiceImpl extends ServiceImpl<PharmacistMapper, Pharmacist> implements IPharmacistService {

    @Override
    public  IPage<Pharmacist> findListByPage(Integer page, Integer pageCount){
        IPage<Pharmacist> wherePage = new Page<>(page, pageCount);
        Pharmacist where = new Pharmacist();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Pharmacist pharmacist){
        return baseMapper.insert(pharmacist);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Pharmacist pharmacist){
        return baseMapper.updateById(pharmacist);
    }

    @Override
    public Pharmacist findById(Long id){
        return  baseMapper.selectById(id);
    }
}
