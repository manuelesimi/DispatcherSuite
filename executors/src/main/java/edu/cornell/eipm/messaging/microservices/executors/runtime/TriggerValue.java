package edu.cornell.eipm.messaging.microservices.executors.runtime;

import java.util.Map;

/**
 * Handle trigger values by evaluating the actual values of the placeholders (if any).
 *
 * @author Manuele Simi
 */
public class TriggerValue {

    final private String formalValue;

    public TriggerValue(String value) {
        this.formalValue = value;
    }

    /**
     * Looks for malicious parameters
     * @param
     * @return
     */
    private String validate(String replacement) {
        //if (replacement.contains(" "))
        //    throw new IllegalArgumentException("Parameters with space(s) are not accepted");
        if (replacement.toLowerCase().contains("bash"))
            throw new IllegalArgumentException("Looks like the parameter is trying to execute a script");
        return replacement;
    }

    /**
     * Gets the actual formalValue of the parameter, given the input list of values
     * @param values list of values for the placeholders (if any)
     * @return the actual formalValue
     */
    public String getActualValue(final Map<String, String> values) {
        final String[] actualValue = new String[]{formalValue};
        values.forEach((k, v) -> actualValue[0] = actualValue[0].replace("${"+ k +"}", validate(v)));
        return actualValue[0];
    }

}
