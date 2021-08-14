package io.github.sefiraat.slimetinker.items.componentmaterials.cmelements;

import io.github.sefiraat.slimetinker.SlimeTinker;
import io.github.sefiraat.slimetinker.categories.Categories;
import io.github.sefiraat.slimetinker.items.componentmaterials.ComponentMaterial;
import io.github.sefiraat.slimetinker.items.workstations.smeltery.DummySmelteryAlloy;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.sefiraat.slimetinker.utils.enums.ThemeItemType;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import net.guizhanss.minecraft.slimetinker.utils.LangUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class CMAlloy {

    private ComponentMaterial parent;
    private SlimefunItemStack itemStack;
    private SlimefunItem item;
    private final Map<String, Integer> alloyMap = new HashMap<>();
    private final List<SlimefunItemStack> recipe;

    public CMAlloy(List<SlimefunItemStack> recipe) {
        this.recipe = recipe;
    }

    public void setupAlloy(ComponentMaterial parent) {
        this.parent = parent;
        String titName = LangUtils.getMaterialName(parent.getId());
        this.itemStack =
                ThemeUtils.themedItemStack(
                        parent.getId() + "_ALLOY",
                        parent.getLiquidTexture(),
                        ThemeItemType.MOLTEN_METAL,
                        "熔融" + titName,
                        ThemeUtils.PASSIVE + "熔融" + titName
                );
        List<SlimefunItemStack> alloyRecipe = parent.getAlloyRecipe();
        Validate.notNull(alloyRecipe, "Alloy recipe is null. SefiDumb™");
        this.item = new SlimefunItem(Categories.ALLOYS, itemStack, DummySmelteryAlloy.TYPE, alloyRecipe.toArray(new ItemStack[9]));
        item.register(SlimeTinker.inst());
        for (SlimefunItemStack i : parent.getAlloyRecipe()) {
            alloyMap.put(i.getItemId().replace("_LIQUID",""), i.getAmount());
        }
    }

}