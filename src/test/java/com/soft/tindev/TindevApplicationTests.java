package com.soft.tindev;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//Classes de teste do modulo Springboot, essa é a "infra" do spring, carrega os beans da aplicação durantes os testes
@RunWith(SpringRunner.class)
@SpringBootTest
class TindevApplicationTests {

	@Test
	void contextLoads() {
		Assert.assertTrue(true);		
	}

}
