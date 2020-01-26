package com.qtatelier.OASystem.basics.revisit_log.service;

import com.qtatelier.OASystem.basics.link_customer_revisit.mapper.LinkCustomerRevisitMapper;
import com.qtatelier.OASystem.basics.link_customer_revisit.model.LinkCustomerRevisit;
import com.qtatelier.OASystem.basics.revisit_log.mapper.RevisitLogMapper;
import com.qtatelier.OASystem.basics.revisit_log.model.RevisitLog;
import com.qtatelier.OASystem.request.ReqRevisit;
import com.qtatelier.OASystem.request.RevisitLogReq;
import com.qtatelier.OASystem.response.RevisitLogRes;
import com.qtatelier.OASystem.thread.RevisitLogException;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ToolRandom;
import com.qtatelier.config.ToolTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private LinkCustomerRevisitMapper linkCustomerRevisitMapper;


    public List<RevisitLogRes> findInfo(RevisitLogReq revisitLogReq) {
        //if (StringUtils.isNotBlank(userId)){
        return revisitLogMapper.findRevistLog(revisitLogReq);
        //}
        //throw new RevisitLogException("用户未登录或无权限");
    }


    @Transactional(rollbackFor = Exception.class)
    public CodeEnum addRevisitLog(ReqRevisit reqRevisit) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (reqRevisit == null) {
            throw new RevisitLogException("当前用户为空");
        }
        RevisitLog revisitLog = new RevisitLog();
        String revisitId = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
        revisitLog.setRevisitId(revisitId);
        revisitLog.setRevisitDate(reqRevisit.getRevisitDate());
        revisitLog.setRevisitLog(reqRevisit.getRevisitLog());
        revisitLog.setRevisitTel(reqRevisit.getRevisitTel());
        int isSuccess = revisitLogMapper.insertSelective(revisitLog);
        if (isSuccess < 1) {
            throw new RevisitLogException("添加回访记录失败!");
        }
        LinkCustomerRevisit linkCustomerRevisit = new LinkCustomerRevisit();
        linkCustomerRevisit.setCustomerId(reqRevisit.getCustomerId());
        linkCustomerRevisit.setRevisitId(revisitId);
        linkCustomerRevisit.setLinkId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
        isSuccess = linkCustomerRevisitMapper.insertSelective(linkCustomerRevisit);
        if (isSuccess < 1) {
            throw new RevisitLogException("添加关联记录失败!");
        }
        return codeEnum;
    }
}
