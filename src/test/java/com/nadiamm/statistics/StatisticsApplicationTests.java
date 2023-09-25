package com.nadiamm.statistics;



import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest
public class StatisticsApplicationTests {

	@Test
	public void whenReadResourceFile_thenReadSuccessful()
			throws IOException, JSONException {


		String actualContent = StatsService.readDataFile("test.json");
		String expectedContent ="{\"test\":  \"test\"}";

		assertEquals(actualContent.replace("\t", "") .replace("\n",
				""),expectedContent.replace("\t", "") .replace("\n", ""));
	}

}
