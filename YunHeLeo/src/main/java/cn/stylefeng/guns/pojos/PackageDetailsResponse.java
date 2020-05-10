package cn.stylefeng.guns.pojos;

import lombok.Data;

public class PackageDetailsResponse {

    private String cprowGuid;
    private String cpuRowGuid;

    private Double totalPrice;
    private String[] tStageId;

    /**
     * 课包名称
     */
    private String packageName;

    /**
     * 阶段名
     */
    private String stageName;

    private String tPackageGuid;

    /**
     * 待激活时长
     */
    private Integer tActivateDate;

    /**
     * 是否已到期
     */
    //private Boolean isDue;

    /**
     * 可学习时长
     */
    private Integer tStudyDate;

    private Double tPrice;

    /**
     * 课包状态 0未发布 1已发布 2已下架
     */
    private Integer tStatus;

    private Integer classCount;

    private boolean canChoose;

    public String getCprowGuid() {
        return cprowGuid;
    }

    public void setCprowGuid(String cprowGuid) {
        this.cprowGuid = cprowGuid;
    }

    public String getCpuRowGuid() {
        return cpuRowGuid;
    }

    public void setCpuRowGuid(String cpuRowGuid) {
        this.cpuRowGuid = cpuRowGuid;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String[] gettStageId() {
        return tStageId;
    }

    public void settStageId(String[] tStageId) {
        this.tStageId = tStageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String gettPackageGuid() {
        return tPackageGuid;
    }

    public void settPackageGuid(String tPackageGuid) {
        this.tPackageGuid = tPackageGuid;
    }

    public Integer gettActivateDate() {
        return tActivateDate;
    }

    public void settActivateDate(Integer tActivateDate) {
        this.tActivateDate = tActivateDate;
    }

    public Integer gettStudyDate() {
        return tStudyDate;
    }

    public void settStudyDate(Integer tStudyDate) {
        this.tStudyDate = tStudyDate;
    }

    public Double gettPrice() {
        return tPrice;
    }

    public void settPrice(Double tPrice) {
        this.tPrice = tPrice;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public boolean isCanChoose() {
        return canChoose;
    }

    public void setCanChoose(boolean canChoose) {
        this.canChoose = canChoose;
    }
}
