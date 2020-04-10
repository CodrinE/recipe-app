package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.UnitOfMeasureCommand;
import com.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = 1L;

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObjectConvert(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        UnitOfMeasureCommand uomc = converter.convert(uom);

        assert uomc != null;
        assertEquals(ID, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getDescription());

    }
}