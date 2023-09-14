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

    private Player first, second;
    @Getter
    private Player turn;
    private ItemStack[] pairs;
    private List<User> users;
    private List<Card> cards;
    private Inventory inventory;
    @Getter @Setter
    private boolean clickAble;
    @Getter @Setter
    private boolean update;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.first = firstPlayer;
        this.second = secondPlayer;
        this.users = Arrays.asList(
                new User(firstPlayer),
                new User(secondPlayer)
        );

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

        firstPlayer.sendMessage(ChatUtil.prefixed("Игра", "&fВы играете против &e%s", secondPlayer.getName()));
        secondPlayer.sendMessage(ChatUtil.prefixed("Игра", "&fВы играете против &e%s", firstPlayer.getName()));

        broadcast(ChatUtil.prefixed("Игра", "&e%s &fначинает игру!", turn.getName()));

        firstPlayer.openInventory(inventory);
        secondPlayer.openInventory(inventory);

        setClickAble(true);
    }

    public void broadcast(String message) {
        first.sendMessage(message);
        second.sendMessage(message);
    }

    public void changeItem(int index, ItemStack item) {
        inventory.setItem(index, item);
    }

    public void updateTitle() {
        setUpdate(true);

        Inventory temp = inventory;
        this.inventory = Bukkit.createInventory(null, 54, "§6" + first.getName() + " " + getUser(first).getScore() + " : " +
                getUser(second).getScore() + " " + second.getName());

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

    public void win(Player player) {
        if (player == null) {
            broadcast(ChatUtil.prefixed("Игра", "&cНичья! Никто не выиграл."));
        } else {
            Integer score = getUser(player).getScore();
            broadcast(ChatUtil.prefixed("Игра", "&e%s &aвыиграл игру со счётом &a%d", player.getName(), score));
        }

        // Удаляем игру
        Memory.getGameManager().end(this);

        if (first != null) first.closeInventory();
        if (second != null) second.closeInventory();
    }

    public boolean checkWin() {
        int maxPairs = 14;

        if (getUser(first).getScore() + getUser(second).getScore() < maxPairs) {
            return false;
        }
        if (getUser(first).getScore() > getUser(second).getScore()) {
            win(first);
            return true;
        }
        if (getUser(first).getScore() < getUser(second).getScore()) {
            win(second);
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
        return player.getName().equalsIgnoreCase(first.getName()) ? second : first;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        if (first.isOnline()) players.add(first);

        if (second.isOnline()) players.add(second);

        return players;
    }

    public User getUser(Player player) {
        for (User user : users) {
            if (player.equals(user.getHandle())) {
                return user;
            }
        }
        return null;
    }
    
    public void setTurn(Player player) {
        this.turn = getOpponent(player);
    }

    private void createInventory() {
        this.inventory = Bukkit.createInventory(null, 54, "§6" + first.getName() + " 0 : 0 " + second.getName());

        setBorder();

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == null || item.getType() == Material.AIR) {
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
        return new Random().nextBoolean() ? first : second;
    }
}