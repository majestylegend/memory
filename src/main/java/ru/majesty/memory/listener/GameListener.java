package ru.majesty.memory.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import ru.majesty.memory.Memory;
import ru.majesty.memory.game.Card;
import ru.majesty.memory.game.Game;
import ru.majesty.memory.user.User;
import ru.majesty.memory.util.ChatUtil;
import ru.majesty.memory.util.ItemBuilder;

import static ru.majesty.memory.util.Constants.COVER;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class GameListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            if (event.getClickedInventory() == null) {
                return;
            }

            Game game = Memory.getGameManager().getGame(player);
            if (game != null) {
                event.setCancelled(true);

                if (event.getView() == null ||
                        !event.getView().getTitle().contains(player.getName())) {
                    return;
                }

                // Проверка валидности предмета
                if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.GLASS_PANE)
                    return;

                // Проверяем является ли клик игровым
                if (event.getAction() == InventoryAction.PICKUP_ALL || event.getAction() == InventoryAction.PICKUP_HALF) {

                    // Проверяем кто должен ходить
                    if (!game.getTurn().getUniqueId().equals(player.getUniqueId())) {
                        player.sendMessage(ChatUtil.prefixed("Игра", "&cСейчас не Ваша очередь делать ход!"));
                        return;
                    }

                    Card card = game.getCardBySlot(event.getSlot());
                    User user = game.getUser(player);

                    // Проверяем является ли карта предыдущей
                    if (user.hasCard()) {
                        if (user.getLastCard().getSlot() == event.getSlot()) {
                            return;
                        }
                    }

                    game.setClickAble(false);
                    game.changeItem(event.getSlot(), card.getItem());

                    if (user.hasCard()) {
                        if (user.getLastCard().equals(card)) {
                            game.broadcast(ChatUtil.prefixed("Игра", "&e%s &aобнаружил пару!", player.getName()));
                            user.addScore();

                            if (game.checkWin()) {
                                return;
                            }
                        } else {
                            game.broadcast(ChatUtil.prefixed("Игра", "&e%s &fcне нашёл пару.", player.getName()));

                            game.setTurn(player);
                        }

                        Bukkit.getScheduler().scheduleSyncDelayedTask(Memory.getInstance(), () -> {

                            if (user.getLastCard().equals(card)) {
                                game.changeItem(user.getLastCard().getSlot(), new ItemBuilder().type(Material.AIR).getItem());
                                game.changeItem(event.getSlot(), new ItemBuilder().type(Material.AIR).getItem());

                                game.updateTitle();
                                game.broadcast(ChatUtil.prefixed("Игра", "&e%s &fможет взять другую карту.", player.getName()));
                            } else {
                                game.changeItem(event.getSlot(), COVER);
                                game.changeItem(user.getLastCard().getSlot(), COVER);

                                game.setTurn(player);

                                game.broadcast(ChatUtil.prefixed("Игра", "&e%s&f, Ваш ход.", game.getTurn().getName()));
                            }

                            user.setLastCard(null);
                            game.setClickAble(true);
                        }, 20L);
                    } else {
                        user.setLastCard(card);

                        player.sendMessage(ChatUtil.prefixed("Игра", "&cВы можете взять другую карту."));

                        game.setClickAble(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void on(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            Game game = Memory.getGameManager().getGame(player);

            if (game != null && !game.isUpdate()) {
                game.win(game.getOpponent(player));
            }
        }
    }
}
