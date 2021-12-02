package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.util.CustomMailSender;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class PickHotel2ApplicationTests {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Test
	void test1() {
		assertNotNull(sqlSessionTemplate);
	}
	
//	@Autowired
//	CustomMailSender customMailSender;
//	
//	@Test
//	void sendMail() throws MessagingException, IOException {
//		customMailSender.sendMail("인증번호를 확인하세요!!","arc552@naver.com","788899");
//	}
	

}
