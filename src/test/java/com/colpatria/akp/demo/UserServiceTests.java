package com.colpatria.akp.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.colpatria.akp.demo.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

	@Autowired
    private UserService _userService;
	
	@Test
	void contextLoads() {
		assertEquals(1, 1);
	}

}
