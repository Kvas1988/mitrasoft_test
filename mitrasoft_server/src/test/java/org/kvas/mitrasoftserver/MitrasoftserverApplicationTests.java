package org.kvas.mitrasoftserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql("/data.sql")
class MitrasoftserverApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetMessages() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/rest/message"))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON.toString(), response.getContentType());
        assertThat(response.getContentAsString().contains("Lorem"));
        assertThat(response.getContentAsString().contains("Ipsum"));
    }

    @Test
    void testAddMessage() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
                .perform(post("/rest/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"hello, world\"}")
                )
                .andReturn()
                .getResponse();

        assertEquals(200, responsePost.getStatus());

        MockHttpServletResponse response = mockMvc
                .perform(get("/rest/message"))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON.toString(), response.getContentType());
        assertThat(response.getContentAsString().contains("hello, world"));
    }
}
