package com.reamot.ex.service;

import com.reamot.ex.entity.Patient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2020-10-18
 */
public interface IPatientService extends IService<Patient> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Patient>
     */
    IPage<Patient> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param patient 
     * @return int
     */
    int add(Patient patient);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param patient 
     * @return int
     */
    int updateData(Patient patient);

    /**
     * id查询数据
     *
     * @param id id
     * @return Patient
     */
    Patient findById(Long id);
}
