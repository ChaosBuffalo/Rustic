package rustic.common.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCondenserAdvanced extends Container {

private TileEntityCondenserAdvanced te;
	
	public ContainerCondenserAdvanced(IInventory playerInventory, TileEntityCondenserAdvanced te) {
		this.te = te;
		addOwnSlots();
		addPlayerSlots(playerInventory);
	}

	public TileEntityCondenserAdvanced getTile() {
		return te;
	}

	private void addPlayerSlots(IInventory playerInventory) {
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				int x = 8 + col * 18;
				int y = row * 18 + 84;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
			}
		}

		for (int row = 0; row < 9; ++row) {
			int x = 8 + row * 18;
			int y = 142;
			this.addSlotToContainer(new Slot(playerInventory, row, x, y));
		}
	}

	private void addOwnSlots() {
		IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		addSlotToContainer(new SlotItemHandler(itemHandler, 0, 27, 59));
		addSlotToContainer(new SlotItemHandler(itemHandler, 1, 27, 35));
		addSlotToContainer(new SlotItemHandler(itemHandler, 2, 27, 11));
		addSlotToContainer(new SlotItemHandler(itemHandler, 3, 66, 7));
		addSlotToContainer(new SlotItemHandler(itemHandler, 4, 66, 62) {
			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return super.isItemValid(stack) && TileEntityFurnace.isItemFuel(stack);
			}
		});
		addSlotToContainer(new SlotItemHandler(itemHandler, 5, 105, 7) {
			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return super.isItemValid(stack) && stack.getItem().equals(Items.GLASS_BOTTLE);
			}
		});
		addSlotToContainer(new SlotItemHandler(itemHandler, 6, 105, 35) {
			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return false;
			}
		});
	}
	
	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
