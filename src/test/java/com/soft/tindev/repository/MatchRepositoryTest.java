package com.soft.tindev.repository;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.soft.tindev.modelo.Match;


@RunWith(SpringRunner.class)
@DataJpaTest
public class MatchRepositoryTest {

	
	@Autowired
	private MatchRepository matchRepository;
	
	@Test
	public void testBuscarPeloMatch() {
		/*Long likedIdTest = 2L;
		Match match = matchRepository.findByliked_id(likedIdTest);
		Assert.assertNotNull(match);
		Assert.assertEquals(likedIdTest, match); */
		
		/*Long idDoMatch = (long) 1;
		Match matchId = matchRepository.findByid(idDoMatch);
		Assert.assertNotNull(matchId);
		Assert.assertEquals(idDoMatch, matchId);	*/	
	}

}
