package com.qf.service;

import com.qf.pojo.TbItem;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TbItemService {
    public List<TbItem> findAll();
    public boolean save(TbItem tbItem);

}
