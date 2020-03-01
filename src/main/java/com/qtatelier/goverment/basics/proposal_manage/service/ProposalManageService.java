package com.qtatelier.goverment.basics.proposal_manage.service;

import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ToolRandom;
import com.qtatelier.config.ToolTime;
import com.qtatelier.goverment.basics.proposal_manage.mapper.ProposalMangerMapper;
import com.qtatelier.goverment.basics.proposal_manage.model.ProposalManger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-03-01 11:48
 */
@Service
public class ProposalManageService {

    @Autowired
    private ProposalMangerMapper proposalMangerMapper;


    /**
     * 添加提案
     * @param proposalManger
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum addPropposal(ProposalManger proposalManger){
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        proposalManger.setId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
        proposalManger.setCreateDate(ToolTime.getNowDate());
        if (!StringUtils.isNotBlank(proposalManger.getContent())){
            codeEnum = CodeEnum.ERROR_404;
        }
        if (!StringUtils.isNotBlank(proposalManger.getAddress())){
            codeEnum = CodeEnum.ERROR_404;
        }
        int isSuccess = proposalMangerMapper.insert(proposalManger);
        if (isSuccess < 1){
            codeEnum = CodeEnum.ERROR_501;
        }
        return codeEnum;
    }


}
