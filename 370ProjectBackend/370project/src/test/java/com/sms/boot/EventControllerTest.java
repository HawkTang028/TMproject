package com.sms.boot;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={Application.class})
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    
    @BeforeEach
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
	@Test
	@Order(1)  
	// add a new event
	  public void addEvent() throws Exception {
		  String json="{\"userId\":1,\"eventTitle\":\"TitileTest1\",\"note\":\"NoteTest1\",\"priority\":3,\"startTime\":\"2020-03-05 00:00:00\",\"endTime\":\"2020-03-06 00:00:00\"}";
	      mvc.perform(MockMvcRequestBuilders.post("/addnewevent")
	      			.contentType("application/json")
	      			.accept(MediaType.APPLICATION_JSON)
	                  .content(json.getBytes())
	          )
	         .andExpect(MockMvcResultMatchers.status().isOk())
	         .andDo(MockMvcResultHandlers.print());

  }
    
	@Test
	@Order(2)  
	// should get the event just added
    public void getEvent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getevent/1")
                    .contentType("text/html")
                    .accept(MediaType.APPLICATION_JSON)
            )
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.eventId").value(1))
           .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
           .andExpect(MockMvcResultMatchers.jsonPath("$.eventTitle").value("TitileTest1"))
           .andExpect(MockMvcResultMatchers.jsonPath("$.note").value("NoteTest1"))
           .andExpect(MockMvcResultMatchers.jsonPath("$.priority").value(3))
           .andExpect(MockMvcResultMatchers.jsonPath("$.startTime").value("2020-03-05 00:00:00"))
           .andExpect(MockMvcResultMatchers.jsonPath("$.endTime").value("2020-03-06 00:00:00"))
           .andDo(MockMvcResultHandlers.print());

    }
	
	@Test
	@Order(3)  
    public void updateEvent() throws Exception {
		String json="{\"eventId\":1,\"eventTitle\":\"UpdateTitileTest1\",\"note\":\"UpdateNoteTest1\",\"priority\":1,\"startTime\":\"2020-03-06 20:00:00\",\"endTime\":\"2020-03-07 21:00:00\"}";
        mvc.perform(MockMvcRequestBuilders.put("/updateEvent")
    			.contentType("application/json")
    			.accept(MediaType.APPLICATION_JSON)
                .content(json.getBytes())
            )
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andDo(MockMvcResultHandlers.print());
        
        // use getEvent() to check
        mvc.perform(MockMvcRequestBuilders.get("/getevent/1")
                .contentType("text/html")
                .accept(MediaType.APPLICATION_JSON)
        )
       .andExpect(MockMvcResultMatchers.status().isOk())
       .andExpect(MockMvcResultMatchers.jsonPath("$.eventId").value(1))
       .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
       .andExpect(MockMvcResultMatchers.jsonPath("$.eventTitle").value("UpdateTitileTest1"))
       .andExpect(MockMvcResultMatchers.jsonPath("$.note").value("UpdateNoteTest1"))
       .andExpect(MockMvcResultMatchers.jsonPath("$.priority").value(1))
       .andExpect(MockMvcResultMatchers.jsonPath("$.startTime").value("2020-03-06 20:00:00"))
       .andExpect(MockMvcResultMatchers.jsonPath("$.endTime").value("2020-03-07 21:00:00"))
       .andExpect(MockMvcResultMatchers.jsonPath("$.isDel").value(0))
       .andDo(MockMvcResultHandlers.print());
        
    }
	
	@Test
	@Order(4)  
	// after deleteEvent() there is one row data with is_del = 1 in database event table
	public void deleteEvent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/deleteEvent/1")
                .contentType("text/html")
                .accept(MediaType.APPLICATION_JSON)
        )
       .andExpect(MockMvcResultMatchers.status().isOk())
       .andDo(MockMvcResultHandlers.print());
		
	}
	

}
