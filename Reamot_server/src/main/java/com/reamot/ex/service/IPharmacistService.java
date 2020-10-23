package com.reamot.ex.service;

import com.reamot.ex.entity.Pharmacist;
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
public interface IPharmacistService extends IService<Pharmacist> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Pharmacist>
     */
    IPage<Pharmacist> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param pharmacist 
     * @return int
     */
    int add(Pharmacist pharmacist);

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
     * @param pharmacist 
     * @return int
     */
    int updateData(Pharmacist pharmacist);

    /**
     * id查询数据
     *
     * @param id id
     * @return Pharmacist
     */
    Pharmacist findById(Long id);
}
