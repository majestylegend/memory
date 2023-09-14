package ru.majesty.memory.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@UtilityClass
public class ChatUtil {

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String colorize(String message, Object... args) {
        return colorize(String.format(message, args));
    }

    public String prefixed(String prefix, String message) {
        return colorize("&7[&b%s&7]&r %s", prefix, message);
    }

    public String prefixed(String prefix, String message, Object... args) {
        return prefixed(prefix, String.format(message, args));
    }
}
