package com.reamot.ex.service;

import com.reamot.ex.entity.Medicine;
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
public interface IMedicineService extends IService<Medicine> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Medicine>
     */
    IPage<Medicine> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param medicine 
     * @return int
     */
    int add(Medicine medicine);

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
     * @param medicine 
     * @return int
     */
    int updateData(Medicine medicine);

    /**
     * id查询数据
     *
     * @param id id
     * @return Medicine
     */
    Medicine findById(Long id);
}
