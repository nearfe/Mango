package com.mango.spigot.command;

import com.mango.spigot.Mango;
import com.mango.spigot.knockback.CraftKnockbackProfile;
import com.mango.spigot.knockback.KnockbackProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mango.spigot.util.CC;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class KnockbackCommand extends Command {

    public KnockbackCommand() {
        super("knockback");
        this.setAliases(Collections.singletonList("kb"));
        this.setUsage(CC.translate(StringUtils.join(new String[]{
                "",
                "&e&lMango Spigot &7- &fKnockback Commands",
                "&7&oPermission: mango.knockback",
                "",
                " &7▪ &f/knockback list",
                " &7▪ &f/knockback create &7<name>",
                " &7▪ &f/knockback delete &7<name>",
                " &7▪ &f/knockback load &7<name>",
                " &7▪ &f/knockback edit &7<name> <field> <value>",
                "",
        }, "\n")));
    }


    private boolean isBoolean(String s) {
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "Unknown command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(usageMessage);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "list": {
                List<String> messages = new ArrayList<>();

                for (KnockbackProfile profile : Mango.INSTANCE.getConfig().getKbProfiles()) {
                    boolean current = Mango.INSTANCE.getConfig().getCurrentKb().getName().equals(profile.getName());

                    messages.add(ChatColor.YELLOW + profile.getName() + (current ? ChatColor.GREEN + " [Active]" : ""));

                    for (String value : profile.getValues()) {
                        messages.add(ChatColor.GREEN + " ▪ " + value);
                    }
                }

                sender.sendMessage("");
                sender.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Knockback Profiles:");
                sender.sendMessage("");
                sender.sendMessage(StringUtils.join(messages, "\n"));
                sender.sendMessage("");
            }
            break;
            case "create": {
                if (args.length > 1) {
                    String name = args[1];

                    for (KnockbackProfile profile : Mango.INSTANCE.getConfig().getKbProfiles()) {
                        if (profile.getName().equalsIgnoreCase(name)) {
                            sender.sendMessage(ChatColor.RED + "A knockback profile with that name already exists.");
                            return true;
                        }
                    }

                    CraftKnockbackProfile profile = new CraftKnockbackProfile(name);

                    profile.save();

                    Mango.INSTANCE.getConfig().getKbProfiles().add(profile);

                    sender.sendMessage(ChatColor.YELLOW + "You created a new profile " + ChatColor.WHITE + name + ChatColor.YELLOW + ".");
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /kb create <name>");
                }
            }
            break;
            case "delete": {
                if (args.length > 1) {
                    final String name = args[1];

                    if (Mango.INSTANCE.getConfig().getCurrentKb().getName().equalsIgnoreCase(name)) {
                        sender.sendMessage(ChatColor.RED + "You cannot delete the profile that is being used.");
                        return true;
                    } else {
                        if (Mango.INSTANCE.getConfig().getKbProfiles().removeIf(profile -> profile.getName().equalsIgnoreCase(name))) {
                            Mango.INSTANCE.getConfig().set("knockback.profiles." + name, null);
                            sender.sendMessage(ChatColor.RED + "You deleted the profile " + ChatColor.WHITE + name + ChatColor.RED + ".");
                        } else {
                            sender.sendMessage(ChatColor.RED + "A profile with that name could not be found.");
                        }

                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /kb delete <name>");
                }
            }
            break;
            case "load": {
                if (args.length > 1) {
                    KnockbackProfile profile = Mango.INSTANCE.getConfig().getKbProfileByName(args[1]);

                    if (profile == null) {
                        sender.sendMessage(ChatColor.RED + "A profile with that name could not be found.");
                        return true;
                    }

                    Mango.INSTANCE.getConfig().setCurrentKb(profile);
                    Mango.INSTANCE.getConfig().set("knockback.current", profile.getName());
                    Mango.INSTANCE.getConfig().save();

                    sender.sendMessage(ChatColor.YELLOW + "You loaded the profile " + ChatColor.WHITE + profile.getName() + ChatColor.YELLOW + ".");
                    return true;
                }
            }
            case "edit":
                if (args.length == 4) {
                    String module = args[1].toLowerCase();
                    String profileName = args[2];

                    KnockbackProfile profile = Mango.INSTANCE.getConfig().getKbProfileByName(profileName);

                    if (profile == null) {
                        sender.sendMessage(ChatColor.RED + "A profile with that name could not be found.");
                        return true;
                    }

                    if (module.equals("combomode") && isBoolean(args[3])) {
                        boolean comboMode = Boolean.parseBoolean(args[3]);
                        profile.setComboMode(comboMode);
                        profile.save();
                        sender.sendMessage(ChatColor.YELLOW + "You have updated " + ChatColor.WHITE + profile.getName() + ChatColor.YELLOW + "'s comboMode to " + ChatColor.WHITE + comboMode + ChatColor.YELLOW + ".");
                    }

                    else if (NumberUtils.isNumber(args[3])) {
                        double value = Double.parseDouble(args[3]);

                        switch (module) {
                            case "friction":
                                profile.setFriction(value);
                                break;
                            case "horizontal":
                                profile.setHorizontal(value);
                                break;
                            case "vertical":
                                profile.setVertical(value);
                                break;
                            case "extrahorizontal":
                                profile.setExtraHorizontal(value);
                                break;
                            case "extravertical":
                                profile.setExtraVertical(value);
                                break;
                            case "limit":
                                profile.setVerticalLimit(value);
                                break;
                            case "comboheight":
                                profile.setComboHeight(value);
                                break;
                            case "comboticks":
                                profile.setComboTicks(Integer.parseInt(args[3]));
                                break;
                            case "combovelocity":
                                profile.setComboVelocity(value);
                                break;
                            default:
                                sender.sendMessage(ChatColor.RED + "Unknown module.");
                                return true;
                        }

                        profile.save();

                        sender.sendMessage(ChatColor.YELLOW + "You have updated " + ChatColor.WHITE + profile.getName() + ChatColor.YELLOW + "'s " + module + " to " + ChatColor.WHITE + value + ChatColor.YELLOW + ".");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Invalid value. Please provide a boolean for comboMode or a number for other modules.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /kb edit <module> <profile> <value>");
                }
                break;

            default: {
                sender.sendMessage(usageMessage);
            }
        }

        return true;
    }

}
