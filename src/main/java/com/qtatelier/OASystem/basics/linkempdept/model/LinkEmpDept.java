package com.qtatelier.OASystem.basics.linkempdept.model;

public class LinkEmpDept {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link_emp_dept.link__id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    private String linkId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link_emp_dept.emp_id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    private String empId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column link_emp_dept.dept_id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    private String deptId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link_emp_dept.link__id
     *
     * @return the value of link_emp_dept.link__id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    public String getLinkId() {
        return linkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link_emp_dept.link__id
     *
     * @param linkId the value for link_emp_dept.link__id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link_emp_dept.emp_id
     *
     * @return the value of link_emp_dept.emp_id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link_emp_dept.emp_id
     *
     * @param empId the value for link_emp_dept.emp_id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column link_emp_dept.dept_id
     *
     * @return the value of link_emp_dept.dept_id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column link_emp_dept.dept_id
     *
     * @param deptId the value for link_emp_dept.dept_id
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}