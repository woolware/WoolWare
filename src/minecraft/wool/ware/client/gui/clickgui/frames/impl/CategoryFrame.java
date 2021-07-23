package wool.ware.client.gui.clickgui.frames.impl;

import net.minecraft.client.Minecraft;
import wool.ware.client.Wool;
import wool.ware.client.gui.clickgui.components.Component;
import wool.ware.client.gui.clickgui.components.impl.ModuleComponent;
import wool.ware.client.gui.clickgui.frames.Frame;
import wool.ware.client.module.Module;
import wool.ware.client.utils.RenderUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;

public class CategoryFrame extends Frame {
    private final Module.Category category;

    public CategoryFrame(Module.Category category, float posX, float posY, float width, float height) {
        super(StringUtils.capitalize(category.name().toLowerCase()), posX, posY, width, height);
        this.category = category;
    }

    @Override
    public void init() {
        float offsetY = getHeight();
        final ArrayList<Module> mods = Wool.INSTANCE.getModuleManager().getModulesInCategory(category);
        mods.sort(Comparator.comparingDouble(module -> -Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getLabel())));
        for (Module module : mods) {
            getComponents().add(new ModuleComponent(this, module, getPosX(), getPosY(), 2, offsetY, getWidth() - 4, 16));
            offsetY += 16;
        }
        super.init();
    }

    @Override
    public void onFrameMoved(float posX, float posY) {
        super.onFrameMoved(posX, posY);
    }

    @Override
    public void onDraw(int mouseX, int mouseY, float partialTicks) {
        super.onDraw(mouseX, mouseY, partialTicks);
        RenderUtil.drawRect(getPosX(), getPosY(), getWidth(), getHeight(), 0x324ab2DB);
        Minecraft.getMinecraft().fontRendererObj.drawString(getLabel(), getPosX() + getWidth() / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(getLabel()) / 2, getPosY() + 4, -1);
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.onMouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int mouseButton) {
        super.onMouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onKeyTyped(int key, char character) {
        super.onKeyTyped(key, character);
    }

    public Module.Category getCategory() {
        return category;
    }

}
