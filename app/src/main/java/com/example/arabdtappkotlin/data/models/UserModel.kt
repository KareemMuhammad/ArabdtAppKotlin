package com.example.arabdtappkotlin.data.models

import com.google.gson.annotations.SerializedName

data class LoginDataModel(
    @SerializedName("sid") val token: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("employee_name") val fullName: String = "",
    @SerializedName("employee") val employee: String = "",
    @SerializedName("role_profile") val role: UserRole = UserRole.EMPLOYEE,
    @SerializedName("user_data") val userData: UserDataModel? = null,
) {
    enum class UserRole {
        @SerializedName("employee")
        EMPLOYEE,

        @SerializedName("team_lead")
        TEAM_LEAD,

        @SerializedName("admin")
        ADMIN,

        @SerializedName("scrum_master")
        SCRUM_MASTER,

        @SerializedName("service_delivery_manager")
        SERVICE_DELIVERY_MANAGER,

        @SerializedName("project_manager")
        PROJECT_MANAGER,

        @SerializedName("hr_manager")
        HR_MANAGER
    }

}

data class UserDataModel(
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("mobile") val mobile: String? = null,
    @SerializedName("profile_image") val profileImage: String? = null,
    @SerializedName("role_profile") val roleProfile: String? = null,
    @SerializedName("birth_date") val birthDate: String? = null,
    @SerializedName("new_password") val newPassword: String? = null,
    @SerializedName("user_type") val userType: String? = null,
    @SerializedName("last_active") val lastActive: String? = null,
    @SerializedName("last_login") val lastLogin: String? = null,
    @SerializedName("roles") val roles: List<String>? = null,
    @SerializedName("annual_leave_type") val annualLeaveType: String? = null
)

//data class EmployeeProfileModel(
//    @SerializedName("name") val name: String? = null,
//    @SerializedName("owner") val owner: String? = null,
//    @SerializedName("creation") val creation: String? = null,
//    @SerializedName("modified") val modified: String? = null,
//    @SerializedName("modified_by") val modifiedBy: String? = null,
//    @SerializedName("employee") val employee: String? = null,
//    @SerializedName("naming_series") val namingSeries: String? = null,
//    @SerializedName("first_name") val firstName: String? = null,
//    @SerializedName("middle_name") val middleName: String? = null,
//    @SerializedName("last_name") val lastName: String? = null,
//    @SerializedName("employee_name") val employeeName: String? = null,
//    @SerializedName("employment_type") val employmentType: String? = null,
//    @SerializedName("timesheet_exception") val timesheetException: Int? = null,
//    @SerializedName("disable_violations_notifications") val disableViolationsNotifications: Int? = null,
//    @SerializedName("company") val company: String? = null,
//    @SerializedName("status") val status: String? = null,
//    @SerializedName("gender") val gender: String? = null,
//    @SerializedName("date_of_birth") val dateOfBirth: String? = null,
//    @SerializedName("date_of_joining") val dateOfJoining: String? = null,
//    @SerializedName("employee_number") val employeeNumber: String? = null,
//    @SerializedName("wfh_balance") val wfhBalance: Int? = null,
//    @SerializedName("disable_leaves") val disableLeaves: Int? = null,
//    @SerializedName("allow_travel") val allowTravel: Int? = null,
//    @SerializedName("person_to_be_contacted") val personToBeContacted: String? = null,
//    @SerializedName("relation") val relation: String? = null,
//    @SerializedName("emergency_phone_number") val emergencyPhoneNumber: String? = null,
//    @SerializedName("user_id") val userId: String? = null,
//    @SerializedName("create_user_permission") val createUserPermission: Int? = null,
//)
