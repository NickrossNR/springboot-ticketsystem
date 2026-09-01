package de.nicorosner.customerapi.auth;

class LoginRequestDTO {
    private String loginName;
    private String loginPasswort;

    public String getLoginName() { return loginName; }
    public void setLoginName(String loginName) { this.loginName = loginName; }

    public String getLoginPasswort() { return loginPasswort; }
    public void setLoginPasswort(String loginPasswort) { this.loginPasswort = loginPasswort; }
}
