package com.olmero.tender;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class IssuerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void createIssuer() throws Exception {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("issuerName", "Issuer Name Test");
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(RestDocumentationRequestBuilders.post("/issuer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestParams)))
				.andExpect(status().isCreated())
				.andDo(document("issuer/create-issuer", requestFields(
						fieldWithPath("issuerName").description("Issuer Name")
				)));
	}

}
