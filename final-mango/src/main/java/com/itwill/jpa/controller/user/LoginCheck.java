package com.itwill.jpa.controller.user;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface LoginCheck {
    String redirectUrl() default "/user_login_form"; // 로그인 페이지 URL을 기본값으로 설정

}
