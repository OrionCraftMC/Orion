package io.github.orioncraftmc.orion.version.v1_5_2.backport.controls;

import io.github.orioncraftmc.orion.api.bridge.minecraft.input.VanillaKeybindingBridge;
import io.github.orioncraftmc.orion.backport.hooks.NostalgiaKeybindingsHook;
import java.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringTranslate;

public class GuiKeyBindingList extends OrionGuiListExtended {
	public final OrionGuiControls field_148191_k;
	public final Minecraft mc;
	public final ArrayList<IGuiListEntry> listEntries;
	public int maxListLabelWidth = 0;

	public GuiKeyBindingList(OrionGuiControls ayj2, Minecraft minecraft) {
		super(minecraft, ayj2.width, ayj2.height, 63, ayj2.height - 32, 20);
		this.field_148191_k = ayj2;
		this.mc = minecraft;
		// new IGuiListEntry list
		this.listEntries = new ArrayList<>();

		KeyBinding[] keybindingsArray = new KeyBinding[minecraft.gameSettings.keyBindings.length];
		System.arraycopy(minecraft.gameSettings.keyBindings, 0, keybindingsArray, 0,
				minecraft.gameSettings.keyBindings.length);
		Arrays.sort(keybindingsArray);

		String lastCategory = null;
		for (KeyBinding keyBinding : keybindingsArray) {
			int descriptionSize;
			String currentCategory = NostalgiaKeybindingsHook.INSTANCE.getKeybindingCategory((VanillaKeybindingBridge) keyBinding);
			if (!currentCategory.equals(lastCategory)) {
				lastCategory = currentCategory;
				listEntries.add(new GuiKeyBindingList.CategoryEntry(currentCategory));
			}

			if ((descriptionSize = minecraft.fontRenderer.getStringWidth(StringTranslate.getInstance()
					.translateKey(((VanillaKeybindingBridge) keyBinding).getDescription()))) > this.maxListLabelWidth) {
				this.maxListLabelWidth = descriptionSize;
			}
			listEntries.add(new GuiKeyBindingList.KeyEntry(keyBinding));
		}
	}

	@Override
	public int getSize() {
		return this.listEntries.size();
	}

	/**
	 * Gets the IGuiListEntry object for the given index
	 */
	@Override
	public IGuiListEntry getListEntry(int integer) {
		return this.listEntries.get(integer);
	}

	@Override
	public int getScrollBarX() {
		return super.getScrollBarX() + 15;
	}

	/**
	 * Gets the width of the list
	 */
	@Override
	public int getListWidth() {
		return super.getListWidth() + 32;
	}

	public class KeyEntry implements IGuiListEntry {
		/**
		 * The keybinding specified for this KeyEntry
		 */
		public VanillaKeybindingBridge keybinding;
		/**
		 * The localized key description for this KeyEntry
		 */
		public String keyDesc;
		public GuiButton btnChangeKeyBinding;
		public GuiButton btnReset;

		public KeyEntry(KeyBinding avb2) {
			this.keybinding = (VanillaKeybindingBridge) avb2;
			//TODO: fix i18n

			this.keyDesc = StringTranslate.getInstance().translateKey(avb2.keyDescription);
			this.btnChangeKeyBinding = new GuiButton(0, 0, 0, 75, 20,
					StringTranslate.getInstance().translateKey(avb2.keyDescription));
			this.btnReset = new GuiButton(0, 0, 0, 50, 20,
					StringTranslate.getInstance().translateKey("controls.reset"));
		}

		@Override
		public void drawEntry(int integer1, int integer2, int integer3, int integer4, int integer5, int integer6, int integer7, boolean boolean8) {
			boolean boolean10 = GuiKeyBindingList.this.field_148191_k.buttonId == this.keybinding;
			GuiKeyBindingList.this.mc.fontRenderer.drawString(this.keyDesc,
					integer2 + 90 - GuiKeyBindingList.this.maxListLabelWidth,
					integer3 + integer5 / 2 - GuiKeyBindingList.this.mc.fontRenderer.FONT_HEIGHT / 2, 0xFFFFFF);
			this.btnReset.xPosition = integer2 + 190;
			this.btnReset.yPosition = integer3;
			this.btnReset.enabled = this.keybinding.getKeyCode() != this.keybinding.getDefaultKeyCode();
			this.btnReset.drawButton(GuiKeyBindingList.this.mc, integer6, integer7);
			this.btnChangeKeyBinding.xPosition = integer2 + 105;
			this.btnChangeKeyBinding.yPosition = integer3;
			this.btnChangeKeyBinding.displayString = GameSettings.getKeyDisplayString(this.keybinding.getKeyCode());
			boolean boolean11 = false;
			if (this.keybinding.getKeyCode() != 0) {
				for (KeyBinding avb15 : mc.gameSettings.keyBindings) {
					if (avb15.keyDescription.equals(
							this.keybinding.getDescription()) || avb15.keyCode != this.keybinding.getKeyCode())
						continue;
					boolean11 = true;
					break;
				}
			}
			if (boolean10) {
				this.btnChangeKeyBinding.displayString = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.btnChangeKeyBinding.displayString + EnumChatFormatting.WHITE + " <";
			} else if (boolean11) {
				this.btnChangeKeyBinding.displayString = EnumChatFormatting.RED + this.btnChangeKeyBinding.displayString;
			}
			this.btnChangeKeyBinding.drawButton(GuiKeyBindingList.this.mc, integer6, integer7);
		}

		/**
		 * Returns true if the mouse has been pressed on this control.
		 */
		@Override
		public boolean mousePressed(int integer1, int integer2, int integer3, int integer4, int integer5, int integer6) {
			if (this.btnChangeKeyBinding.mousePressed(GuiKeyBindingList.this.mc, integer2, integer3)) {
				field_148191_k.buttonId = this.keybinding;
				return true;
			}
			if (this.btnReset.mousePressed(GuiKeyBindingList.this.mc, integer2, integer3)) {
				List<KeyBinding> keyBindings = Arrays.stream(mc.gameSettings.keyBindings).toList();
				GuiKeyBindingList.this.mc.gameSettings.setKeyBinding(
						keyBindings.indexOf(((KeyBinding) this.keybinding)),
						this.keybinding.getDefaultKeyCode());
				KeyBinding.resetKeyBindingArrayAndHash();
			}
			return false;
		}

		/**
		 * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
		 */
		@Override
		public void mouseReleased(int index, int x, int y, int mouseEvent, int relativeX, int relativeY) {
			this.btnChangeKeyBinding.mouseReleased(x, y);
			this.btnReset.mouseReleased(x, y);
		}

		@Override
		public void setSelected(int integer1, int integer2, int integer3) {
		}
	}

	public class CategoryEntry implements OrionGuiListExtended.IGuiListEntry {
		public final String labelText;
		public final int labelWidth;

		public CategoryEntry(String string) {
			this.labelText = StringTranslate.getInstance().translateKey(string);
			this.labelWidth = GuiKeyBindingList.this.mc.fontRenderer.getStringWidth(this.labelText);
		}

		@Override
		public void drawEntry(int integer1, int integer2, int integer3, int integer4, int integer5, int integer6, int integer7, boolean boolean8) {
			GuiKeyBindingList.this.mc.fontRenderer.drawString(this.labelText,
					GuiKeyBindingList.this.mc.currentScreen.width / 2 - this.labelWidth / 2,
					integer3 + integer5 - GuiKeyBindingList.this.mc.fontRenderer.FONT_HEIGHT - 1, 0xFFFFFF);
		}

		/**
		 * Returns true if the mouse has been pressed on this control.
		 */
		@Override
		public boolean mousePressed(int integer1, int integer2, int integer3, int integer4, int integer5, int integer6) {
			return false;
		}

		/**
		 * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
		 */
		@Override
		public void mouseReleased(int index, int x, int y, int mouseEvent, int relativeX, int relativeY) {
		}

		@Override
		public void setSelected(int integer1, int integer2, int integer3) {
		}
	}
}
