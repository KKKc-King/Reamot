package com.reamot.ex.service.impl;

import com.reamot.ex.entity.Patient;
import com.reamot.ex.mapper.PatientMapper;
import com.reamot.ex.service.IPatientService;
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
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService {

    @Override
    public  IPage<Patient> findListByPage(Integer page, Integer pageCount){
        IPage<Patient> wherePage = new Page<>(page, pageCount);
        Patient where = new Patient();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Patient patient){
        return baseMapper.insert(patient);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Patient patient){
        return baseMapper.updateById(patient);
    }

    @Override
    public Patient findById(Long id){
        return  baseMapper.selectById(id);
    }
}
