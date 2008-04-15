package org.jcf;

import java.util.Collection;

public interface JCFRow {

	/**
     * Returns the values of the field whose variable matches the requested variable.
     *
     * @param variable the variable to match.
     * @return the values of the field whose variable matches the requested variable.
     */
    public Collection<String> getValues(String variable);
}
