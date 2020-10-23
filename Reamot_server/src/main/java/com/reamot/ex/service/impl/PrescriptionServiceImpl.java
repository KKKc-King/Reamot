package com.reamot.ex.service.impl;

import com.reamot.ex.entity.Prescription;
import com.reamot.ex.mapper.PrescriptionMapper;
import com.reamot.ex.service.IPrescriptionService;
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
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements IPrescriptionService {

    @Override
    public  IPage<Prescription> findListByPage(Integer page, Integer pageCount){
        IPage<Prescription> wherePage = new Page<>(page, pageCount);
        Prescription where = new Prescription();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Prescription prescription){
        return baseMapper.insert(prescription);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Prescription prescription){
        return baseMapper.updateById(prescription);
    }

    @Override
    public Prescription findById(Long id){
        return  baseMapper.selectById(id);
    }
}
