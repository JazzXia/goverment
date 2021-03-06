package com.qtatelier.goverment.basics.optlog.model;

import java.io.Serializable;

public class OptLog implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.log_id
     *
     * @mbg.generated
     */
    private String logId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.account_id
     *
     * @mbg.generated
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.operation
     *
     * @mbg.generated
     */
    private String operation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.oper_time
     *
     * @mbg.generated
     */
    private String operTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.method
     *
     * @mbg.generated
     */
    private String method;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.ip
     *
     * @mbg.generated
     */
    private String ip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.module_name
     *
     * @mbg.generated
     */
    private String moduleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column opt_log.opt_result
     *
     * @mbg.generated
     */
    private String optResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table opt_log
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.log_id
     *
     * @return the value of opt_log.log_id
     *
     * @mbg.generated
     */
    public String getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.log_id
     *
     * @param logId the value for opt_log.log_id
     *
     * @mbg.generated
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.account_id
     *
     * @return the value of opt_log.account_id
     *
     * @mbg.generated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.account_id
     *
     * @param accountId the value for opt_log.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.operation
     *
     * @return the value of opt_log.operation
     *
     * @mbg.generated
     */
    public String getOperation() {
        return operation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.operation
     *
     * @param operation the value for opt_log.operation
     *
     * @mbg.generated
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.remark
     *
     * @return the value of opt_log.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.remark
     *
     * @param remark the value for opt_log.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.oper_time
     *
     * @return the value of opt_log.oper_time
     *
     * @mbg.generated
     */
    public String getOperTime() {
        return operTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.oper_time
     *
     * @param operTime the value for opt_log.oper_time
     *
     * @mbg.generated
     */
    public void setOperTime(String operTime) {
        this.operTime = operTime == null ? null : operTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.method
     *
     * @return the value of opt_log.method
     *
     * @mbg.generated
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.method
     *
     * @param method the value for opt_log.method
     *
     * @mbg.generated
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.ip
     *
     * @return the value of opt_log.ip
     *
     * @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.ip
     *
     * @param ip the value for opt_log.ip
     *
     * @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.module_name
     *
     * @return the value of opt_log.module_name
     *
     * @mbg.generated
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.module_name
     *
     * @param moduleName the value for opt_log.module_name
     *
     * @mbg.generated
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column opt_log.opt_result
     *
     * @return the value of opt_log.opt_result
     *
     * @mbg.generated
     */
    public String getOptResult() {
        return optResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column opt_log.opt_result
     *
     * @param optResult the value for opt_log.opt_result
     *
     * @mbg.generated
     */
    public void setOptResult(String optResult) {
        this.optResult = optResult == null ? null : optResult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table opt_log
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", accountId=").append(accountId);
        sb.append(", operation=").append(operation);
        sb.append(", remark=").append(remark);
        sb.append(", operTime=").append(operTime);
        sb.append(", method=").append(method);
        sb.append(", ip=").append(ip);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", optResult=").append(optResult);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}