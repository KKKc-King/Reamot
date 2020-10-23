package com.reamot.ex.service.impl;

import com.reamot.ex.entity.Doctor;
import com.reamot.ex.mapper.DoctorMapper;
import com.reamot.ex.service.IDoctorService;
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
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements IDoctorService {

    @Override
    public  IPage<Doctor> findListByPage(Integer page, Integer pageCount){
        IPage<Doctor> wherePage = new Page<>(page, pageCount);
        Doctor where = new Doctor();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Doctor doctor){
        return baseMapper.insert(doctor);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Doctor doctor){
        return baseMapper.updateById(doctor);
    }

    @Override
    public Doctor findById(Long id){
        return  baseMapper.selectById(id);
    }
}
