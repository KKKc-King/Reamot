package com.reamot.ex.service.impl;

import com.reamot.ex.entity.History;
import com.reamot.ex.mapper.HistoryMapper;
import com.reamot.ex.service.IHistoryService;
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
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements IHistoryService {

    @Override
    public  IPage<History> findListByPage(Integer page, Integer pageCount){
        IPage<History> wherePage = new Page<>(page, pageCount);
        History where = new History();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(History history){
        return baseMapper.insert(history);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(History history){
        return baseMapper.updateById(history);
    }

    @Override
    public History findById(Long id){
        return  baseMapper.selectById(id);
    }
}
