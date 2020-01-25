package com.qtatelier.OASystem.basics.revisit_log.service;

import com.qtatelier.OASystem.basics.revisit_log.mapper.RevisitLogMapper;
import com.qtatelier.OASystem.request.RevisitLogReq;
import com.qtatelier.OASystem.response.RevisitLogRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-25 21:00
 */
@Service
public class RevisitLogService {
    @Autowired
    private RevisitLogMapper revisitLogMapper;



    public List<RevisitLogRes> findInfo(RevisitLogReq revisitLogReq){
        //if (StringUtils.isNotBlank(userId)){
            return revisitLogMapper.findRevisitLog(revisitLogReq);
        //}
        //throw new RevisitLogException("用户未登录或无权限");
    }

}
