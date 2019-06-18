package com.qf.service.Impl;

import com.qf.mapper.TbItemMapper;
import com.qf.pojo.TbItem;
import com.qf.service.TbItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TbItemServiceImpl implements TbItemService {

    @Resource
    private TbItemMapper tbItemMapper;
    public List<TbItem> findAll() {
        return tbItemMapper.findAll();
    }

    public boolean save(TbItem tbItem) {
        int i = tbItemMapper.saveItem(tbItem);
        return i>0;
    }
}
