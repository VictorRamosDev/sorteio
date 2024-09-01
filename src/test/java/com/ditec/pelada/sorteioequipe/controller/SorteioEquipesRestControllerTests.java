package com.ditec.pelada.sorteioequipe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SorteioEquipesRestControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private SorteioEquipesRestController sorteioEquipesRestController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(sorteioEquipesRestController).build();
    }

    @Test
    public void drawTeamsShouldReturnDrawTeams() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/sorteio-equipes"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
