package ca.yorku.cse2311.tab2pdf.parser;

import java.text.ParseException;

/**
 * Created by Marco on 1/12/2015.
 */
public interface IParser<T> {

    public T parse(String token) throws ParseException;

    public boolean canParse(String token);
}
