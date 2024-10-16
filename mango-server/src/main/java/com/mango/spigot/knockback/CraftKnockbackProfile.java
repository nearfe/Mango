package com.mango.spigot.knockback;

import org.bukkit.ChatColor;
import com.mango.spigot.Mango;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CraftKnockbackProfile implements KnockbackProfile {

    private String name;
    private double friction = 2.0D;
    private double horizontal = 0.35D;
    private double vertical = 0.35D;
    private double verticalLimit = 0.4D;
    private double extraHorizontal = 0.425D;
    private double extraVertical = 0.085D;

    public CraftKnockbackProfile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String[] getValues() {
        return new String[]{
                ChatColor.YELLOW + "Friction" + ChatColor.WHITE + ": " + this.friction,
                ChatColor.YELLOW + "Horizontal" + ChatColor.WHITE + ": " + this.horizontal,
                ChatColor.YELLOW + "Vertical" + ChatColor.WHITE + ": " + this.vertical,
                ChatColor.YELLOW + "Vertical Limit" + ChatColor.WHITE + ": " + this.verticalLimit,
                ChatColor.YELLOW + "Extra Horizontal" + ChatColor.WHITE + ": " + this.extraHorizontal,
                ChatColor.YELLOW + "Extra Vertical" + ChatColor.WHITE + ": " + this.extraVertical,

        };
    }

    public void save() {
        final String path = "knockback.profiles." + this.name;

        Mango.INSTANCE.getConfig().set(path + ".friction", this.friction);
        Mango.INSTANCE.getConfig().set(path + ".horizontal", this.horizontal);
        Mango.INSTANCE.getConfig().set(path + ".vertical", this.vertical);
        Mango.INSTANCE.getConfig().set(path + ".vertical-limit", this.verticalLimit);
        Mango.INSTANCE.getConfig().set(path + ".extra-horizontal", this.extraHorizontal);
        Mango.INSTANCE.getConfig().set(path + ".extra-vertical", this.extraVertical);

        Mango.INSTANCE.getConfig().save();
    }

}
