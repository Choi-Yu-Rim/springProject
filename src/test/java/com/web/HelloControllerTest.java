package com.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import www.SpringBoot.com.web.HelloController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* @RunWith(SpringRunner.class)
* - 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
* - 여기서는 SpringRunner라는 스프링 실행자를 사용
* - 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할
*
* @WebMvcTest(controllers = HelloController.class)
* - 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
* - 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
* - 단, @Service, @Component, @Repository 등은 사용 할 수 없음
* - 여기서는 컨트롤러만 사용하기 때문에 선언
* */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    /* private MockMvc mvc;
     * - web API를 테스트 할 때 사용
     * - 스프링 MVC 테스트의 시작점
     * - 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있습니다.
     */
    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello주소로 HTTP GET 요청을 함.
        // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 할 수 있음
        mvc.perform(get("/hello"))
                //mvc.perform의 결과를 검증
                //HTTP Header의 Status를 검증함
                //우리가 흔히 알고 있는 200, 404,500 등의 상태를 검증함
                //여기선 ok 즉, 200 인지 아닌지를 검증
                .andExpect(status().isOk())
                //mvc.perform의 결과를 검증
                //응답 본문의 내용을 검증
                //Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
                .andExpect(content().string(hello));
    }
}
