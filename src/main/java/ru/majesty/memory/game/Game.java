package ru.majesty.memory.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.majesty.memory.Memory;
import ru.majesty.memory.user.User;
import ru.majesty.memory.util.ChatUtil;
import ru.majesty.memory.util.Constants;

import java.util.*;

import static ru.majesty.memory.util.Constants.BORDER;
import static ru.majesty.memory.util.Constants.COVER;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class Game {
    private final Memory instance;
    private Player firstPlayer, secondPlayer;
    @Getter
    private Player turn;
    private ItemStack[] pairs;
    private List<Card> cards;
    private Inventory inventory;
    @Getter
    @Setter
    private boolean clickAble;
    @Getter
    @Setter
    private boolean update;

    public Game(Memory instance, User firstPlayer, User secondPlayer) {
        this.instance = instance;
        this.firstPlayer = firstPlayer.getHandle();
        this.secondPlayer = secondPlayer.getHandle();

        // Определяем кто играет первый
        this.turn = getRandomTurn();

        // Устанавливаем пары карт
        this.pairs = Constants.DEFAULT_PAIRS;

        // Расставляем карты
        this.cards = new ArrayList<>();
        for (int i = 0; i < pairs.length; i++) {
            cards.add(new Card(-1, pairs[i]));
        }
        for (int i = 0; i < pairs.length; i++) {
            cards.add(new Card(-1, pairs[i]));
        }

        // Перемешиваем карты
        Collections.shuffle(cards);

        // Создаём игровой "стол"
        createInventory();

        firstPlayer.sendMessage("&fВы играете против &e%s", secondPlayer.getName());
        secondPlayer.sendMessage("&fВы играете против &e%s", firstPlayer.getName());

        broadcast(ChatUtil.prefixed("Игра", "&e%s &fначинает игру!", turn.getName()));

        this.firstPlayer.openInventory(inventory);
        this.secondPlayer.openInventory(inventory);

        firstPlayer.setGame(this);
        secondPlayer.setGame(this);

        setClickAble(true);
    }

    public void broadcast(String message, Object... args) {
        instance.getUserManager().get(firstPlayer).sendMessage(message, args);
        instance.getUserManager().get(secondPlayer).sendMessage(message, args);
    }

    public void changeItem(int index, ItemStack item) {
        inventory.setItem(index, item);
    }

    public void updateTitle() {
        setUpdate(true);

        Inventory temp = inventory;
        this.inventory = Bukkit.createInventory(null, 54, "§b" + firstPlayer.getName() + " " + instance.getUserManager().get(firstPlayer).getScore() + " : " +
                instance.getUserManager().get(secondPlayer).getScore() + " " + secondPlayer.getName());

        for (int i = 0; i < temp.getSize(); i++) {
            ItemStack item = temp.getItem(i);
            if (item != null && item.getType() != Material.AIR) {
                inventory.setItem(i, item);
            }
        }

        for (Player player : getPlayers()) {
            player.openInventory(inventory);
        }
        setUpdate(false);
    }

    public void win(User user) {
        if (user == null) {
            broadcast("&cНичья! Никто не выиграл.");
        } else {
            Integer score = user.getScore();
            broadcast("&e%s &aвыиграл игру со счётом &a%d", user.getName(), score);
        }

        // Обрабатываем победу и проигрыш
        user.addWin();
        user.addLose();

        // Удаляем игру
        instance.getGameManager().end(this);

        // Закрываем "стол"
        if (firstPlayer != null) firstPlayer.closeInventory();
        if (secondPlayer != null) secondPlayer.closeInventory();
    }

    public boolean checkWin() {
        int maxPairs = 14;

        User firstPlayer = instance.getUserManager().get(this.firstPlayer);
        User secondPlayer = instance.getUserManager().get(this.secondPlayer);

        if (firstPlayer.getScore() + secondPlayer.getScore() < maxPairs) {
            return false;
        }
        if (firstPlayer.getScore() > secondPlayer.getScore()) {
            win(firstPlayer);
            return true;
        }
        if (firstPlayer.getScore() < secondPlayer.getScore()) {
            win(secondPlayer);
            return true;
        }

        win(null);
        return true;
    }

    public Card getCardBySlot(int slot) {
        for (Card card : cards) {
            if (card.getSlot() == slot) {
                return card;
            }
        }

        return null;
    }

    public Player getOpponent(Player player) {
        return player.getName().equalsIgnoreCase(firstPlayer.getName()) ? secondPlayer : firstPlayer;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        if (firstPlayer.isOnline()) players.add(firstPlayer);

        if (secondPlayer.isOnline()) players.add(secondPlayer);

        return players;
    }

    public void setTurn(Player player) {
        this.turn = getOpponent(player);
    }

    private void createInventory() {
        this.inventory = Bukkit.createInventory(null, 54, "§6" + firstPlayer.getName() + " 0 : 0 " + secondPlayer.getName());

        setBorder();

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.AIR) {
                inventory.setItem(i, COVER);

                for (Card card : cards) {
                    if (card.getSlot() == -1) {
                        card.setSlot(i);
                        break;
                    }
                }
            }
        }
    }

    private void setBorder() {
        for (int i = 0; i < 9; i++) inventory.setItem(i, BORDER);
        for (int i = 45; i < 54; i++) inventory.setItem(i, BORDER);
        for (int i = 0; i < 46; i += 9) inventory.setItem(i, BORDER);
        for (int i = 8; i < 54; i += 9) inventory.setItem(i, BORDER);
    }

    private Player getRandomTurn() {
        return new Random().nextBoolean() ? firstPlayer : secondPlayer;
    }
}