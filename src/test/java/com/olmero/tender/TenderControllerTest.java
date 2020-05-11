package com.olmero.tender;

import com.olmero.tender.entity.Tender;
import com.olmero.tender.service.TenderService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TenderControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	private TenderService tenderService;

	@Test
	public void findTenderById() throws Exception {
		Tender tender = new Tender();
		when(tenderService.findById(1L)).thenReturn(tender);
		mockMvc.perform(MockMvcRequestBuilders.get("/tender/1"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.workDescription").exists());
	}
}
