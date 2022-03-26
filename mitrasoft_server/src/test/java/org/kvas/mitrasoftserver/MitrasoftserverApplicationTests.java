package org.kvas.mitrasoftserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MitrasoftserverApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testDummy() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/rest/message"))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
    }

}
