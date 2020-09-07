package com.example.demo.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    private Long id;

    @NotNull(message="昵称必须填")
    @Size(min=1, max=20, message="昵称1~20个字")
    private String nickname;

    @NotNull(message="手机号必须填")
    @Pattern(regexp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$", message="请输入11位手机号")
    private String mobile;

    @NotNull(message="密码必须填")
    @Size(min=6, max=16, message="密码6~16位")
    private String password;

    private String sex;

    private String role;

    public User(Long id, String nickname, String mobile, String password, String sex, String role) {
        this.id = id;
        this.nickname = nickname;
        this.mobile = mobile;
        this.password = password;
        this.sex = sex;
        this.role = role;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}