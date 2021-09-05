package moe.sylvi.bitexchange.inventory;

import moe.sylvi.bitexchange.BitRegistries;
import moe.sylvi.bitexchange.bit.BitHelper;
import moe.sylvi.bitexchange.bit.info.ItemBitInfo;
import moe.sylvi.bitexchange.bit.storage.BitStorage;
import moe.sylvi.bitexchange.bit.storage.BitStorages;
import moe.sylvi.bitexchange.transfer.InventoryItemContext;
import moe.sylvi.bitexchange.transfer.FullInventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface BitConsumerInventory extends ImplementedInventory {
    World getWorld();

    int getStorageSlot();
    int getInputSlot();

    default BitStorage getStorage() {
        ItemStack stack = getStack(getStorageSlot());
        if (!stack.isEmpty()) {
            InventoryStorage inventoryStorage = FullInventoryStorage.of(this);
            InventoryItemContext context = new InventoryItemContext(inventoryStorage, getStorageSlot(), getWorld());
            return context.find(BitStorages.ITEM);
        }
        return null;
    }

    default ItemStack createStack(ItemStack stack, int count) {
        if (!stack.isEmpty()) {
            InventoryStorage inventoryStorage = FullInventoryStorage.of(this);
            InventoryItemContext context = new InventoryItemContext(inventoryStorage, getStorageSlot(), getWorld());
            BitStorage storage = context.find(BitStorages.ITEM);

            ItemBitInfo info = BitRegistries.ITEM.get(stack.getItem());

            if (storage != null && info != null) {
                double toExtract = info.getValue() * Math.max(0, Math.min(stack.getItem().getMaxCount(), count));
                double extracted;
                int itemsExtracted = count;
                try (Transaction transaction = Transaction.openOuter()) {
                    extracted = BitHelper.fixBitRounding(storage.extract(toExtract, transaction), info.getValue());
                    if (info.getValue() != 0) {
                        itemsExtracted = (int) Math.floor(extracted / info.getValue());
                        extracted = itemsExtracted * info.getValue();
                    }
                }
                if (itemsExtracted > 0) {
                    try (Transaction transaction = Transaction.openOuter()) {
                        storage.extract(extracted, transaction);
                        transaction.commit();
                    }
                    ItemStack newStack = stack.copy();
                    newStack.setCount(itemsExtracted);
                    return newStack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    default void createStacks(ItemStack stack, int count, int slotIndex, int slotCount) {
        if (!stack.isEmpty()) {
            InventoryStorage inventoryStorage = FullInventoryStorage.of(this);
            InventoryStorage resultStorage = FullInventoryStorage.ofSlice(this, slotIndex, slotCount);
            InventoryItemContext context = new InventoryItemContext(resultStorage, inventoryStorage.getSlots().get(getStorageSlot()), getWorld());
            BitStorage storage = context.find(BitStorages.ITEM);

            ItemBitInfo info = BitRegistries.ITEM.get(stack.getItem());
            ItemVariant resource = ItemVariant.of(stack);

            if (storage != null && info != null) {
                double toExtract = info.getValue() * count;
                int itemsExtracted = count;
                try (Transaction transaction = Transaction.openOuter()) {
                    double extracted = BitHelper.fixBitRounding(storage.extract(toExtract, transaction), info.getValue());
                    if (info.getValue() != 0) {
                        itemsExtracted = (int) Math.floor(extracted / info.getValue());
                    }
                }
                if (itemsExtracted > 0) {
                    try (Transaction transaction = Transaction.openOuter()) {
                        long itemsInserted = context.insert(resource, itemsExtracted, transaction);
                        storage.extract(itemsInserted * info.getValue(), transaction);
                        transaction.commit();
                    }
                }
            }
        }
    }

    default void consumeInput() {
        ItemStack storageStack = getStack(getStorageSlot());
        if (storageStack.isEmpty()) {
            return;
        }
        InventoryStorage inventoryStorage = FullInventoryStorage.of(this);
        InventoryItemContext inputContext = new InventoryItemContext(inventoryStorage, getInputSlot(), getWorld());
        BitStorage storage = getStorage();
        if (storage != null) {
            try (Transaction transaction = Transaction.openOuter()) {
                double converted;
                try (Transaction initialTransaction = transaction.openNested()) {
                    converted = BitHelper.convertToBits(storage.getMaxBits() - storage.getBits(), inputContext, initialTransaction);
                }
                double inserted = storage.insert(converted, transaction);
                BitHelper.convertToBits(inserted, inputContext, transaction);
                transaction.commit();
            }
        }
    }
}