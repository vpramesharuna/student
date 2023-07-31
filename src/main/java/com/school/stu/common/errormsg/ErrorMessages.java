package com.school.stu.common.errormsg;

public enum ErrorMessages {

    ERROR_PLEASE_PROVIDE_STUDENT_REGISTER_DATA("Please provide Student " +
                                                          "Information, " +
                                                          "this cannot be null"),
    ERROR_PLEASE_PROVIDE_ALL_REQUISITE_STUDENT_INFO("Please provide All needed " +
                                                                        "Customer Data For Onboarding " +
                                                                        "and Registration");

    private String errorValue;

    ErrorMessages(String errorValue) {
        this.errorValue = errorValue;
    }

    public String getErrorValue() {
        return this.errorValue;
    }
}
