package com.reamot.ex.service.impl;

import com.reamot.ex.entity.Medicine;
import com.reamot.ex.mapper.MedicineMapper;
import com.reamot.ex.service.IMedicineService;
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
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine> implements IMedicineService {

    @Override
    public  IPage<Medicine> findListByPage(Integer page, Integer pageCount){
        IPage<Medicine> wherePage = new Page<>(page, pageCount);
        Medicine where = new Medicine();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Medicine medicine){
        return baseMapper.insert(medicine);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Medicine medicine){
        return baseMapper.updateById(medicine);
    }

    @Override
    public Medicine findById(Long id){
        return  baseMapper.selectById(id);
    }
}
