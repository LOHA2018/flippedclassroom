package com.loha.flippedclassroom.pojo.PO;

/**
 * @Author: birden
 * @Description: 策略
 * @Date: 2018/12/26 13:09
 */
public class StrategyPO {
    private Long id;
    private String strategyName;
    private Long strategyId;
    private Integer strategySerial;
    private Long courseId;

    public StrategyPO(String strategyName, Long strategyId){
        this.id=0L;
        this.strategyName=strategyName;
        this.strategyId=strategyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getStrategySerial() {
        return strategySerial;
    }

    public void setStrategySerial(Integer strategySerial) {
        this.strategySerial = strategySerial;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
