package ca.yorku.cse2311.tab2pdf.parser;

/**
 * AbstractParser
 *
 * @author Marco
 * @since 2015-01-13
 */
public abstract class AbstractParser<T> implements IParser<T> {

    @Override
    public boolean canParse(String token) {
        return getPattern().matcher(token).find();
    }

}
