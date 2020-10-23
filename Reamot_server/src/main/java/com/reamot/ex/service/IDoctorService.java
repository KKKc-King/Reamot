package com.reamot.ex.service;

import com.reamot.ex.entity.Doctor;
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
public interface IDoctorService extends IService<Doctor> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Doctor>
     */
    IPage<Doctor> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param doctor 
     * @return int
     */
    int add(Doctor doctor);

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
     * @param doctor 
     * @return int
     */
    int updateData(Doctor doctor);

    /**
     * id查询数据
     *
     * @param id id
     * @return Doctor
     */
    Doctor findById(Long id);
}
