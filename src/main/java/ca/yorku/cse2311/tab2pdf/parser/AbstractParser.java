package ca.yorku.cse2311.tab2pdf.parser;

import ca.yorku.cse2311.tab2pdf.model.IMusicalNotation;

/**
 * AbstractParser
 *
 * @author Marco
 * @since 2015-01-13
 */
public abstract class AbstractParser<T extends IMusicalNotation> implements IParser<T> {

    @Override
    public boolean canParse(String token) {
        return getPattern().matcher(token).find();
    }

}
