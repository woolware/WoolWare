package wool.ware.client.gui.materialui.component.impl.subcomponents;

import java.awt.*;

import wool.ware.client.gui.materialui.component.Component;
import wool.ware.client.utils.MouseUtil;
import wool.ware.client.utils.RenderUtil;
import wool.ware.client.utils.font.Fonts;
import wool.ware.client.utils.value.impl.BooleanValue;

public class BooleanComponent extends Component {
    private BooleanValue booleanValue;
    public BooleanComponent(BooleanValue booleanValue, float posX, float posY, float offsetX, float offsetY, float width, float height) {
        super(booleanValue.getLabel(), posX, posY, offsetX, offsetY, width, height);
        this.booleanValue = booleanValue;
    }

    @Override
    public void componentMoved(float movedX, float movedY) {
        super.componentMoved(movedX, movedY);
    }

    @Override
    public void onDrawScreen(int mouseX, int mouseY, float partialTicks) {
        super.onDrawScreen(mouseX, mouseY, partialTicks);
        Fonts.clickGuiFont.drawStringWithShadow(getLabel(), getPosX(), getPosY() + 3, new Color(229, 229, 223, 255).getRGB());
        RenderUtil.drawOutlinedRoundedRect(getPosX() + getWidth() - 15,getPosY(),10,10,3,1,new Color(0xff689FFF).getRGB());
        if (booleanValue.isEnabled()) {
            RenderUtil.drawRoundedRect(getPosX() + getWidth() - 15,getPosY(),10,10,3,new Color(0xff689FFF).getRGB());
            RenderUtil.drawCheckMark(getPosX() + getWidth() - 8,getPosY() - 1,8, new Color(229, 229, 223, 255).getRGB());
        }
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int button) {
        super.onMouseClicked(mouseX, mouseY, button);
        if (button == 0 && MouseUtil.mouseWithinBounds(mouseX,mouseY,getPosX() + getWidth() - 15,getPosY(),10,10)) {
            booleanValue.setEnabled(!booleanValue.isEnabled());
        }
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int button) {
        super.onMouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void onKeyTyped(char character, int keyCode) {
        super.onKeyTyped(character, keyCode);
    }

    public BooleanValue getBooleanValue() {
        return booleanValue;
    }
}
