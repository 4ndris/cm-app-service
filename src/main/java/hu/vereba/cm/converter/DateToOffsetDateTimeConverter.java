package hu.vereba.cm.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@ReadingConverter
public class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {
    @Override
    public OffsetDateTime convert(Date source) {
        return OffsetDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
    }
}