package com.school.stu.common.errormsg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * ErrorResponse
 */
public class ErrorResponse  implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("errorCode")
    private Integer errorCode;

    @JsonProperty("errorReason")
    private String errorReason;

    @JsonProperty("errorSource")
    private String errorSource;

    public ErrorResponse errorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Get errorCode
     * @return errorCode
     */

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorResponse errorReason(String errorReason) {
        this.errorReason = errorReason;
        return this;
    }

    /**
     * Get errorReason
     * @return errorReason
     */
    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public ErrorResponse errorSource(String errorSource) {
        this.errorSource = errorSource;
        return this;
    }

    /**
     * Get errorSource
     * @return errorSource
     */
    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse errorResponse = (ErrorResponse) o;
        return Objects.equals(this.errorCode, errorResponse.errorCode) &&
                Objects.equals(this.errorReason, errorResponse.errorReason) &&
                Objects.equals(this.errorSource, errorResponse.errorSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorReason, errorSource);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponse {\n");

        sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
        sb.append("    errorReason: ").append(toIndentedString(errorReason)).append("\n");
        sb.append("    errorSource: ").append(toIndentedString(errorSource)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

