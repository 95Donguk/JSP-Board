package edu.mzc.myboard.dto;

import edu.mzc.myboard.domain.user.Id;
import edu.mzc.myboard.domain.user.Name;
import edu.mzc.myboard.domain.user.Password;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@Getter
@Setter
public class UserDTO implements HttpSessionBindingListener {

    // private 멤버 변수 선언
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Id id;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Password password;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Name name;
    private String role;

    public void setId(String id) {
        this.id = new Id(id);
    }

    public void setPassword(String password) {
        this.password = new Password(password);
    }

    public void setName(String name) {
        this.name = new Name(name);
    }

    public String getId() {
        return id.getId();
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("---> UserVO 객체가 세션에 등록됨");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("---> UserVO 객체가 세션에서 제거됨");
    }
}
