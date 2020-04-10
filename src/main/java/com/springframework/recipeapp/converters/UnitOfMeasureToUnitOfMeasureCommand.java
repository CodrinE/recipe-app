package com.springframework.recipeapp.converters;

import com.springframework.recipeapp.commands.UnitOfMeasureCommand;
import com.springframework.recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(!isNull(source)){
            final UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
            uom.setId(source.getId());
            uom.setDescription(source.getDescription());
            return uom;
        }
        return null;
    }
}
