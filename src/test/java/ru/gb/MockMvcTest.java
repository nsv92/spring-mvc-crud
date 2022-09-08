package ru.gb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.entities.Car;
import ru.gb.services.CarService;
import ru.gb.services.ColorService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class MockMvcTest {

    private static String string = "Simple Spring CRUD app with MVC ";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarService carService;

    @Autowired
    private ColorService colorService;

    @Test
    public void indexTest() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(string)));
    }

    @Test
    public void colorsTest() throws Exception {
        this.mockMvc.perform(get("/color/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("all-colors"));
    }

    @Test
    public void carsFormTest() throws Exception {
        this.mockMvc.perform(get("/car/new")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("car-form"));
    }

    @Test
    public void testCarServiceFindById() {
        Car car = carService.findById(1L);
        if (car != null) {
            assert carService.findById(1L) instanceof Car;
            assert car.getId().equals(1L);
            assert car.getBrand().equals("vw");
            assert car.getModel().equals("beatle");
            assert car.getColor().toString().equals("green");
        }
        assert car != null;
    }

    @Test
    public void testColorServiceFindByIdThrowsEx() {
        Long wrongId = 25L;
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {colorService.findById(wrongId);});

        Assertions.assertTrue(exception.getMessage().contains(String.valueOf(wrongId)));

    }
}
