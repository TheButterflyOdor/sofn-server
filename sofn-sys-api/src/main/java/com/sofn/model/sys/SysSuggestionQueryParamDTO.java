package com.sofn.model.sys;

/**
 * 追溯系统优化建议查询参数DTO
 */
public final class SysSuggestionQueryParamDTO extends PagingAdapterDTO {
    private String regionCode; // 行政区域编码
    private String suggestionType; // 建议类别
    private String feedbackTimeBeginning; // 反馈起始时间
    private String feedbackTimeEnding; // 反馈截止时间

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getSuggestionType() {
        return suggestionType;
    }

    public void setSuggestionType(String suggestionType) {
        this.suggestionType = suggestionType;
    }

    public String getFeedbackTimeBeginning() {
        return feedbackTimeBeginning;
    }

    public void setFeedbackTimeBeginning(String feedbackTimeBeginning) {
        this.feedbackTimeBeginning = feedbackTimeBeginning;
    }

    public String getFeedbackTimeEnding() {
        return feedbackTimeEnding;
    }

    public void setFeedbackTimeEnding(String feedbackTimeEnding) {
        this.feedbackTimeEnding = feedbackTimeEnding;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysSuggestionQueryParamDTO{");
        sb.append("regionCode='").append(regionCode).append('\'');
        sb.append(", suggestionType='").append(suggestionType).append('\'');
        sb.append(", feedbackTimeBeginning='").append(feedbackTimeBeginning).append('\'');
        sb.append(", feedbackTimeEnding='").append(feedbackTimeEnding).append('\'');
        sb.append(", pageSize=").append(getPageSize());
        sb.append(", pageNum=").append(getPageNum());
        sb.append(", start=").append(getStart());
        sb.append(", length=").append(getLength());
        sb.append('}');
        return sb.toString();
    }
}
