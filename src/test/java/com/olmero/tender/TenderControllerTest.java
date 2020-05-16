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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class TenderControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void findTenderById() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/tender/{id}", 1L))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.workDescription").exists())
				.andDo(document("tender/get-tender-by-id", pathParameters(parameterWithName("id").description("Tender Unique Identifier")),
						responseFields(
								fieldWithPath("id").description("Tender Unique Identifier"),
								fieldWithPath("workDescription").description("Work Description of Tender"))));
	}

	@Test
	public void findOfferById() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/tender/offer/{id}", 1L))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").exists())
				.andDo(document("tender/offer/get-offer-by-id", pathParameters(parameterWithName("id").description("Offer Unique Identifier")),
						responseFields(
								fieldWithPath("id").description("Offer Unique Identifier"),
								fieldWithPath("price").description("Offer Price"),
								fieldWithPath("status").description("Offer Status"))));
	}


	@Test
	public void getTenderOffers() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/tender/{id}/offers", 1L))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andDo(document("tender/offer/get-tender-offers-by-id", pathParameters(parameterWithName("id").description("Offer Unique Identifier")),
						responseFields(
								fieldWithPath("[].id").description("Offer Unique Identifier"),
								fieldWithPath("[].price").description("Offer Price"),
								fieldWithPath("[].status").description("Offer Status"))));
	}

	@Test
	public void getOffersSubmittedByBidder() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/tender/bidder/{id}/offers", 1L))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andDo(document("tender/offer/get-tender-offers-submitted-by-bidder", pathParameters(parameterWithName("id").description("Bidder Unique Identifier")),
						responseFields(
								fieldWithPath("[].id").description("Offer Unique Identifier"),
								fieldWithPath("[].price").description("Offer Price"),
								fieldWithPath("[].status").description("Offer Status"))));
	}

	@Test
	public void getOffersSubmittedByBidderForTender() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/tender/bidder/{id}/offers?tenderId=2", 1L))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andDo(document("tender/offer/get-specific-tender-offers-submitted-by-bidder",
						pathParameters(parameterWithName("id").description("Bidder Unique Identifier")),
						requestParameters(parameterWithName("tenderId").description("Tender Unique Identifier")),
						responseFields(
								fieldWithPath("[].id").description("Offer Unique Identifier"),
								fieldWithPath("[].status").description("Offer Status"),
								fieldWithPath("[].price").description("Offer Price"))));
	}

	@Test
	public void getTendersForIssuer() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/tender/issuer/{id}", 1L))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andDo(document("tender/get-tenders-for-issuer", pathParameters(parameterWithName("id").description("Issuer Unique Identifier")),
						responseFields(
								fieldWithPath("[].id").description("Tender Unique Identifier"),
								fieldWithPath("[].workDescription").description("Work Description of Tender"))));
	}

	@Test
	public void createTender() throws Exception {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("workDescription", "Work desc test");
		requestParams.put("issuerId", 1L);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(RestDocumentationRequestBuilders.post("/tender").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(requestParams)))
				.andExpect(status().isCreated())
				.andDo(document("tender/create-tender", requestFields(
						fieldWithPath("workDescription").description("Work Description of Tender"),
						fieldWithPath("issuerId").description("Issuer Unique Identifier")
				)));
	}

	@Test
	public void createOffer() throws Exception {
		Map<String, Object> requestParams = new HashMap<>();
		requestParams.put("bidderId", 1L);
		requestParams.put("tenderId", 1L);
		requestParams.put("price", 50000L);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(RestDocumentationRequestBuilders.post("/tender/offer").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(requestParams)))
				.andExpect(status().isCreated())
				.andDo(document("tender/offer/create-offer", requestFields(
						fieldWithPath("bidderId").description("Bidder Unique Identifier"),
						fieldWithPath("tenderId").description("Tender Unique Identifier"),
						fieldWithPath("price").description("Offer Price")
				)));
	}

	@Test
	public void submitOffer() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.put("/tender/{id}/offer/{offerId}", 1L, 1L))
				.andExpect(status().isOk())
				.andDo(document("tender/offer/submit-offer", pathParameters(
						parameterWithName("id").description("Tender Unique Identifier"),
						parameterWithName("offerId").description("Offer Unique Identifier")
				)));
	}

}
