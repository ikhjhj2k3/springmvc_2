package hello.springmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller

public class RequestParamController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/json-v1")
    public void  requestBodyJsonV1(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        ServletInputStream servletInputStream = request.getInputStream();
        String body = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);

        UserInfo userInfo = objectMapper.readValue(body, UserInfo.class);


        log.error("body = {}", body);
        log.error("name = {}, age={}", userInfo.getName(), userInfo.getAge());

        response.getWriter().write("called");
    }

    @PostMapping("/json-v2")
    @ResponseBody
    public String  requestBodyJsonV2(
                                   @RequestBody String body
                                   ) throws IOException {


        UserInfo userInfo = objectMapper.readValue(body, UserInfo.class);


        log.error("body = {}", body);
        log.error("name = {}, age={}", userInfo.getName(), userInfo.getAge());
        return "requestBodyJsonV2";

    }

    @PostMapping("/json-v3")
    @ResponseBody
    public String  requestBodyJsonV3(
            @RequestBody UserInfo userInfo
    ) throws IOException {


        //UserInfo userInfo = objectMapper.readValue(body, UserInfo.class);


        log.error("body = {}", userInfo.toString());
        log.error("name = {}, age={}", userInfo.getName(), userInfo.getAge());
        return "requestBodyJsonV2";

    }


    @RequestMapping("")
    public void requestParamV1(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(value = "username", required = false,
                                       defaultValue = "default") String username_param
                               ) throws IOException {
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        //log.error("username={}, age={}", username, age);
        log.error("username={}, age={}", username_param, age);

        response.getWriter().write("ok");
    }


    @RequestMapping("/model-att-v1")
    public  void modelattv1(
            HttpServletRequest request,
            HttpServletResponse response,
            @ModelAttribute UserInfo userInfo) throws IOException {
        log.error("username={}, age={}",
                userInfo.getName(),
                userInfo.getAge());
          response.getWriter().write("ok");
        //return "ok";
    }

    @PostMapping("/request-body-v1")
    public  void requestBodyStrinng(HttpServletRequest request,
                                    InputStream inputStream,
                                    @RequestBody String messageBody_Param,
                                    HttpServletResponse response) throws IOException {
        //ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream,
                                                    StandardCharsets.UTF_8);
        log.error("messageBody:{}", messageBody);
        log.error("messageBody_param:{}", messageBody_Param);

        response.getWriter().write("ok");
    }




}
