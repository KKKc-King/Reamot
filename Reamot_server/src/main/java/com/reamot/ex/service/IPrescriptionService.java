package com.reamot.ex.service;

import com.reamot.ex.entity.Prescription;
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
public interface IPrescriptionService extends IService<Prescription> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Prescription>
     */
    IPage<Prescription> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param prescription 
     * @return int
     */
    int add(Prescription prescription);

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
     * @param prescription 
     * @return int
     */
    int updateData(Prescription prescription);

    /**
     * id查询数据
     *
     * @param id id
     * @return Prescription
     */
    Prescription findById(Long id);
}
