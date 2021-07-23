package wool.ware.client.module.impl.visuals;

import org.lwjgl.input.Keyboard;

import wool.ware.client.gui.clickgui.ClickGUI;
import wool.ware.client.gui.materialui.MaterialUI;
import wool.ware.client.module.Module;
import wool.ware.client.utils.value.impl.BooleanValue;

public class ClickGui extends Module {
    private ClickGUI clickGUI = null;
    private MaterialUI materialUI = null;
    private BooleanValue material = new BooleanValue("MaterialUI", false);
    public ClickGui() {
        super("ClickGUI", Category.VISUALS, -1);
        setHidden(true);
        setKeybind(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        if (getMc().theWorld != null) {
            if (material.isEnabled()) {
                if (materialUI == null) {
                    materialUI = new MaterialUI();
                    materialUI.initializedUI();
                }
                getMc().displayGuiScreen(materialUI);
            } else {
                if (clickGUI == null) {
                    clickGUI = new ClickGUI();
                    clickGUI.init();
                }
                getMc().displayGuiScreen(clickGUI);
            }
        }
        toggle();
    }
}
