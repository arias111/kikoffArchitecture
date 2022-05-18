package com.itis.kikoff;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itis.kikoff.models.dto.EmailPasswordDto;
import com.itis.kikoff.models.dto.SignUpDto;
import com.itis.kikoff.models.dto.TokenDto;
import com.itis.kikoff.repositories.UserRepository;
import com.itis.kikoff.services.SignUpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties =
        {"spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect"})
@AutoConfigureMockMvc

public class KikoffApplicationAuthV2Test {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeAll
    public static void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    private static EmailPasswordDto instanceOfSignInDto() {
        SignUpDto signUpDto = instanceOfSingUpDto();

        return EmailPasswordDto.builder()
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .build();
    }

    private static SignUpDto instanceOfSingUpDto() {
        return SignUpDto.builder()
                .email("test@mail.ru")
                .firstName("TestName")
                .lastName("TestLastName")
                .patronymic("TestPatronymic")
                .birthday(LocalDateTime.now())
                .password("Password")
                .build();
    }


        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private SignUpService signUpService;

        @Autowired
        private UserRepository repository;

        @Test
        @DisplayName("Registering new User")
        @Transactional
        @Rollback
        void signUpNewUserTest() throws Exception {
            SignUpDto dto = instanceOfSingUpDto();
            String signUpDto = objectMapper.writeValueAsString(dto);
            MvcResult response = mockMvc.perform(post("/signUp")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(signUpDto))
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();

            TokenDto tokenDto = objectMapper.readValue(response.getResponse().getContentAsString(), TokenDto.class);

            Assertions.assertNotNull(tokenDto);
            Assertions.assertNotNull(tokenDto.getToken());
            Assertions.assertTrue(repository.findByEmail(dto.getEmail()).isPresent());
        }

        @Test
        @Transactional
        @Rollback
        @DisplayName("Login exiting User")
        void loginExitingUserTest() throws Exception {
            SignUpDto signUpDto = instanceOfSingUpDto();

            signUpService.signUp(signUpDto);

            Assertions.assertTrue(repository.findByEmail(signUpDto.getEmail()).isPresent());

            EmailPasswordDto signInDto = instanceOfSignInDto();
            String dto = objectMapper.writeValueAsString(signInDto);
            MvcResult response = mockMvc.perform(post("/signIn")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(dto))
                    .andReturn();

            TokenDto tokenDto = objectMapper.readValue(response.getResponse().getContentAsString(), TokenDto.class);

            Assertions.assertNotNull(tokenDto);
            Assertions.assertNotNull(tokenDto.getToken());
    }

}
