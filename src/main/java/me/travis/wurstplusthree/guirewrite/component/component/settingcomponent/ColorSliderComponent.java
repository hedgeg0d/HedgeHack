package me.travis.wurstplusthree.guirewrite.component.component.settingcomponent;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.wurstplusthree.WurstplusThree;
import me.travis.wurstplusthree.guirewrite.WurstplusGuiNew;
import me.travis.wurstplusthree.guirewrite.component.Component;
import me.travis.wurstplusthree.guirewrite.component.component.HackButton;
import me.travis.wurstplusthree.hack.client.GuiRewrite;
import me.travis.wurstplusthree.setting.type.IntSetting;
import me.travis.wurstplusthree.util.ColorUtil;
import me.travis.wurstplusthree.util.RenderUtil2D;
import net.minecraft.client.gui.Gui;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Madmegsox1
 * @since 29/04/2021
 */

public class ColorSliderComponent extends Component {
    private boolean hovered;
    private HackButton parent;
    private int offset;
    private int x;
    private int y;
    private String cName;
    private int cValue;
    private boolean dragging = false;

    private double renderWidth;

    public ColorSliderComponent(HackButton button, int offset, String cName, int cValue) {
        this.parent = button;
        this.offset = offset;
        this.cName = cName;
        this.cValue = cValue;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(parent.parent.getX() + WurstplusGuiNew.MODULE_WIDTH_OFFSET, parent.parent.getY() + offset + WurstplusGuiNew.MODULE_SPACING, parent.parent.getX() + parent.parent.getWidth() - WurstplusGuiNew.MODULE_WIDTH_OFFSET, parent.parent.getY() + offset + WurstplusGuiNew.HEIGHT + WurstplusGuiNew.MODULE_SPACING, this.hovered ? WurstplusGuiNew.GUI_HOVERED_TRANSPARENCY : WurstplusGuiNew.GUI_TRANSPARENCY);
        RenderUtil2D.drawGradientRect(parent.parent.getX() + WurstplusGuiNew.MODULE_WIDTH_OFFSET, parent.parent.getY() + offset + WurstplusGuiNew.MODULE_SPACING, parent.parent.getX() + (int) renderWidth, parent.parent.getY() + offset + WurstplusGuiNew.HEIGHT + WurstplusGuiNew.MODULE_SPACING,
                (GuiRewrite.INSTANCE.rainbow.getValue() ? ColorUtil.releasedDynamicRainbow(0, GuiRewrite.INSTANCE.buttonColor.getColor().getAlpha()).hashCode() : GuiRewrite.INSTANCE.buttonColor.getColor().hashCode()),
                (GuiRewrite.INSTANCE.rainbow.getValue() ? ColorUtil.releasedDynamicRainbow(GuiRewrite.INSTANCE.rainbowDelay.getValue(), GuiRewrite.INSTANCE.buttonColor.getColor().getAlpha()).hashCode() : GuiRewrite.INSTANCE.buttonColor.getColor().hashCode()));

        RenderUtil2D.drawVerticalLine(parent.parent.getX() + WurstplusGuiNew.MODULE_WIDTH_OFFSET + 1, parent.parent.getY() + offset, WurstplusGuiNew.HEIGHT + 2, GuiRewrite.INSTANCE.lineColor.getColor().hashCode());
        WurstplusThree.GUI_FONT_MANAGER.drawStringWithShadow(this.cName + " " + ChatFormatting.GRAY + this.cValue, parent.parent.getX() + WurstplusGuiNew.COLOR_FONT_INDENT, parent.parent.getY() + offset + 3 + WurstplusGuiNew.MODULE_SPACING, GuiRewrite.INSTANCE.fontColor.getColor().hashCode());
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isMouseOnButton(mouseX, mouseY);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();

        int widthTest = WurstplusGuiNew.WIDTH - (WurstplusGuiNew.MODULE_WIDTH_OFFSET * 2);
        double diff = Math.min(widthTest, Math.max(0, mouseX - this.x));
        int min = 0;
        int max = 255;

        renderWidth = (widthTest) * (float) (cValue - min) / (max - min) + WurstplusGuiNew.MODULE_WIDTH_OFFSET;

        if (dragging) {
            if (diff == 0) {
                cValue = 0;
            } else {
                int newValue = (int) roundToPlace(((diff / widthTest) * (max - min) + min), 2);
                cValue  = newValue;
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        dragging = false;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        //WurstplusThree.LOGGER.info("D " + isMouseOnButtonD(mouseX, mouseY) + " I " + isMouseOnButtonI(mouseX, mouseY));
        if (isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.isOpen) {
            dragging = true;
        }
        if (isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.isOpen) {
            dragging = true;
        }
    }

    public boolean isMouseOnButtonD(int x, int y) {
        return x > this.x + WurstplusGuiNew.MODULE_WIDTH_OFFSET && x < this.x + (parent.parent.getWidth() / 2 + 1) - WurstplusGuiNew.MODULE_WIDTH_OFFSET && y > this.y && y < this.y + WurstplusGuiNew.HEIGHT;
    }

    public boolean isMouseOnButtonI(int x, int y) {
        return x > this.x + parent.parent.getWidth() / 2 + WurstplusGuiNew.MODULE_WIDTH_OFFSET && x < this.x + parent.parent.getWidth() - WurstplusGuiNew.MODULE_WIDTH_OFFSET && y > this.y && y < this.y + WurstplusGuiNew.HEIGHT;
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.parent.parent.getX() + WurstplusGuiNew.MODULE_WIDTH_OFFSET && x < this.parent.parent.getX() + WurstplusGuiNew.WIDTH - WurstplusGuiNew.MODULE_WIDTH_OFFSET && y > this.parent.parent.getY() + offset + WurstplusGuiNew.MODULE_SPACING && y < this.parent.parent.getY() + offset + WurstplusGuiNew.HEIGHT + WurstplusGuiNew.MODULE_SPACING;
    }

    private static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
