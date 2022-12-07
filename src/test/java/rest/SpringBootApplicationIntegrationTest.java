package rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import carRental.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SpringBootApplicationIntegrationTest {
	   @Autowired
	    private WebApplicationContext webApplicationContext;
	    private MockMvc mockMvc;
	    
	  @Before
	    public void setupMockMvc() {
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    }
	  
	    @Test
	    public void test1()
	      throws Exception { 
	    	MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    	MediaType.APPLICATION_JSON.getSubtype());
	        mockMvc.perform(MockMvcRequestBuilders.get("/reservation")).
	        andExpect(MockMvcResultMatchers.status().isOk()).
	        andExpect(MockMvcResultMatchers.content().contentType(contentType)).
	        andExpect(jsonPath("$").isEmpty()); 
	    } 
	    
	    @Test
	    public void test2() throws Exception {
	    	ObjectMapper mapper = new ObjectMapper();
	    	Reservation reservation = new Reservation();
	    	reservation.brand = "Honda";
	    	reservation.model = "Civic";
	    	reservation.pickupTime = "11";
	    	reservation.pickupLocation = "ankara";
	    	reservation.dropoffTime = "12";
	    	reservation.dropoffLocation = "ankara";
	    	
	    	MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    	MediaType.APPLICATION_JSON.getSubtype());
	    	
	    	mockMvc.perform(MockMvcRequestBuilders.post("/reservation").contentType(contentType).content(mapper.writeValueAsString(reservation))).
	        andExpect(MockMvcResultMatchers.status().isOk()).
	        andExpect(MockMvcResultMatchers.content().contentType(contentType)).	    
	        andExpect(jsonPath("$").value(0)); 
	    }	
	    
	    @Test
	    public void test3() throws Exception {
	    	ObjectMapper mapper = new ObjectMapper();
	    	Reservation reservation = new Reservation();
	    	reservation.brand = "Ford";
	    	reservation.model = "Fiesta";
	    	reservation.pickupTime = "11";
	    	reservation.pickupLocation = "ankara";
	    	reservation.dropoffTime = "12";
	    	reservation.dropoffLocation = "ankara";
	    	
	    	MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    	MediaType.APPLICATION_JSON.getSubtype());
	    	
	    	mockMvc.perform(MockMvcRequestBuilders.post("/reservation").contentType(contentType).content(mapper.writeValueAsString(reservation))).
	        andExpect(MockMvcResultMatchers.status().isOk()).
	        andExpect(MockMvcResultMatchers.content().contentType(contentType)).	    
	        andExpect(jsonPath("$").value(1)); 
	    }
	    
	    @Test
	    public void test4()
	      throws Exception { 
	    	MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    	MediaType.APPLICATION_JSON.getSubtype());
	        mockMvc.perform(MockMvcRequestBuilders.get("/reservation")).
	        andExpect(MockMvcResultMatchers.status().isOk()).
	        andExpect(MockMvcResultMatchers.content().contentType(contentType)).
	        andExpect(jsonPath("$.size()").value(2)); 
	    }
	    
	    @Test
	    public void test5()
	      throws Exception { 
	    	MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    	MediaType.APPLICATION_JSON.getSubtype());
	        mockMvc.perform(MockMvcRequestBuilders.delete("/reservation/1")).
	        andExpect(MockMvcResultMatchers.status().isOk()).
	        andExpect(MockMvcResultMatchers.content().contentType(contentType)).
	        andExpect(jsonPath("$").value(1)); 
	    } 
	    
}
