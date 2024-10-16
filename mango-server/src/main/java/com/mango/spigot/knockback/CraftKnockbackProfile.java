package com.mango.spigot.knockback;

import org.bukkit.ChatColor;
import com.mango.spigot.Mango;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CraftKnockbackProfile implements KnockbackProfile {

    private String name;
    private double horizontal = 0.35D;
    private double vertical = 0.35D;
    private double verticalLimit = 0.4D;
    private double extraHorizontal = 0.425D;
    private double extraVertical = 0.085D;
    private double horizontalFriction = 2.0D;
    private double verticalFriction = 2.0D;
    private double rangeReduction = 0.025D;
    private double rangeReductionStart = 1.2D;
    private double rangeRedutionLimit = 1.0D;
    private boolean comboMode = true;
    private double comboHeight = 2.3D;
    private int comboTicks = 10;
    private double comboVelocity = -0.05D;

    public CraftKnockbackProfile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String[] getValues() {
        return new String[]{
                ChatColor.YELLOW + "Horizontal" + ChatColor.WHITE + ": " + this.horizontal,
                ChatColor.YELLOW + "Vertical" + ChatColor.WHITE + ": " + this.vertical,
                ChatColor.YELLOW + "Vertical Limit" + ChatColor.WHITE + ": " + this.verticalLimit,
                ChatColor.YELLOW + "Extra Horizontal" + ChatColor.WHITE + ": " + this.extraHorizontal,
                ChatColor.YELLOW + "Extra Vertical" + ChatColor.WHITE + ": " + this.extraVertical,
                ChatColor.YELLOW + "Horizontal Friction" + ChatColor.WHITE + ": " + this.horizontalFriction,
                ChatColor.YELLOW + "Vertical Friction" + ChatColor.WHITE + ": " + this.verticalFriction,
                ChatColor.YELLOW + "Range Reduction" + ChatColor.WHITE + ": " + this.rangeReduction,
                ChatColor.YELLOW + "Min Range Reduction Distance" + ChatColor.WHITE + ": " + this.rangeReductionStart,
                ChatColor.YELLOW + "Range Reduction Limit" + ChatColor.WHITE + ": " + this.rangeReductionLimit,
                ChatColor.YELLOW + "Combo Mode" + ChatColor.WHITE + ": " + this.comboMode,
                ChatColor.YELLOW + "Combo Height" + ChatColor.WHITE + ": " + this.comboHeight,
                ChatColor.YELLOW + "Combo Ticks" + ChatColor.WHITE + ": " + this.comboTicks,
                ChatColor.YELLOW + "Combo Velocity" + ChatColor.WHITE + ": " + this.comboVelocity,


        };
    }

    public void save() {
        final String path = "knockback.profiles." + this.name;

        Mango.INSTANCE.getConfig().set(path + ".horizontal", this.horizontal);
        Mango.INSTANCE.getConfig().set(path + ".vertical", this.vertical);
        Mango.INSTANCE.getConfig().set(path + ".vertical-limit", this.verticalLimit);
        Mango.INSTANCE.getConfig().set(path + ".extra-horizontal", this.extraHorizontal);
        Mango.INSTANCE.getConfig().set(path + ".extra-vertical", this.extraVertical);
        Mango.INSTANCE.getConfig().set(path + ".horizontal-friction", this.horizontalFriction);
        Mango.INSTANCE.getConfig().set(path + ".vertical-friction", this.verticalFriction);
        Mango.INSTANCE.getConfig().set(path + ".range-reduction", this.rangeReduction);
        Mango.INSTANCE.getConfig().set(path + ".min-range-reduction-dist", this.rangeReductionStart);
        Mango.INSTANCE.getConfig().set(path + ".range-reduction-limit", this.rangeReductionLimit);
        Mango.INSTANCE.getConfig().set(path + ".combo-mode", this.comboMode);
        Mango.INSTANCE.getConfig().set(path + ".combo-height", this.comboHeight);
        Mango.INSTANCE.getConfig().set(path + ".combo-ticks", this.comboTicks);
        Mango.INSTANCE.getConfig().set(path + ".combo-velocity", this.comboVelocity);

        Mango.INSTANCE.getConfig().save();
    }

}
