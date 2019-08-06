package com.cwh.bbs.pojo;

public class User {

    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private Long create_time;
    private Long create_modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getCreate_modified() {
        return create_modified;
    }

    public void setCreate_modified(Long create_modified) {
        this.create_modified = create_modified;
    }
}
