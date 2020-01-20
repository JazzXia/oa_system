package com.qtatelier.OASystem.basics.optlog.service;

import com.qtatelier.OASystem.basics.optlog.mapper.OptLogMapper;
import com.qtatelier.OASystem.basics.optlog.model.OptLog;
import com.qtatelier.OASystem.thread.OptLogException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 插入
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-14 11:27
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
@Service
public class OptLogService {

    @Autowired
    private OptLogMapper optLogMapper;

    @Transactional(rollbackFor = Exception.class)
    public int saveLog( OptLog optLog){
        return optLogMapper.insert(optLog);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteInfoByLike(String operTime){
        return optLogMapper.deleteInfoByLike( operTime );
    }



    public List<OptLog> selectByAccoutId(OptLog optLog){
        if(StringUtils.isNotBlank( optLog.getAccountId() )){
           return optLogMapper.selectByAccoutId( optLog );
        }
        throw new OptLogException( "当前用户未登录,无法查看" );
    }

}
