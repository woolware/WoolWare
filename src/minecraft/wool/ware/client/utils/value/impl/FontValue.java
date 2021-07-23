package wool.ware.client.utils.value.impl;

import wool.ware.client.utils.font.MCFontRenderer;
import wool.ware.client.utils.value.Value;


public class FontValue extends Value<MCFontRenderer> {

    public FontValue(String label, MCFontRenderer value) {
        super(label, value);
    }

    public FontValue(String label, MCFontRenderer value, Value parentValueObject, String parentValue) {
        super(label, value,parentValueObject,parentValue);
    }

    @Override
    public void setValue(MCFontRenderer value) {
        this.value = value;
    }

    @Override
    public void setValue(String value) {

    }
}
