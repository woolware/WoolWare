package wool.ware.client.utils.value.impl;


import java.awt.*;

import wool.ware.client.utils.value.Value;

/**
 * made by Xen for OhareWare
 *
 * @since 7/21/2019
 **/
public class ColorValue extends Value<Integer> {

    public ColorValue(String label, int value) {
        super(label, value);
    }

    public ColorValue(String label, int value, Value parentValueObject, String parentValue) {
        super(label, value, parentValueObject, parentValue);
    }

    @Override
    public void setValue(String value) {

    }
    public Color getColor() {
        return new Color(getValue());
    }
}