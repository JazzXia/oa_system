package com.qtatelier.OASystem.basics.empinfo.model;

import java.io.Serializable;

public class EmpInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_info.emp_id
     *
     * @mbg.generated
     */
    private String empId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp_info.emp_no
     *
     * @mbg.generated
     */
    private String empNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table emp_info
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_info.emp_id
     *
     * @return the value of emp_info.emp_id
     *
     * @mbg.generated
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_info.emp_id
     *
     * @param empId the value for emp_info.emp_id
     *
     * @mbg.generated
     */
    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp_info.emp_no
     *
     * @return the value of emp_info.emp_no
     *
     * @mbg.generated
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp_info.emp_no
     *
     * @param empNo the value for emp_info.emp_no
     *
     * @mbg.generated
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table emp_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empId=").append(empId);
        sb.append(", empNo=").append(empNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}