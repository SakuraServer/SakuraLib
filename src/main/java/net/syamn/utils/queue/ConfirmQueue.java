/**
 * SakuraUtils - Package: net.syamn.utils.queue
 * Created: 2012/12/26 9:23:09
 */
package net.syamn.utils.queue;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * ConfirmQueue (ConfirmQueue.java)
 * @author syam(syamn)
 */
public class ConfirmQueue {
    //private Plugin plugin;
    private List<QueuedCommand> queue;

    /**
     * コンストラクタ
     * 
     * @param plugin
     */
    public ConfirmQueue(/*final Plugin plugin*/) {
        //this.plugin = plugin;

        queue = new ArrayList<QueuedCommand>();
    }

    /**
     * キューにコマンドを追加する
     * 
     * @param sender
     *            CommandSender
     * @param queueable
     *            Queueable
     * @param args
     *            List<String>
     * @param seconds
     *            int
     */
    public void addQueue(CommandSender sender, Queueable queueable, List<String> args, int seconds) {
        cancelQueue(sender);
        this.queue.add(new QueuedCommand(sender, queueable, args, seconds));
    }

    /**
     * キューのコマンドを実行する
     * 
     * @param sender
     *            コマンド送信者
     */
    public boolean confirmQueue(CommandSender sender) {
        for (QueuedCommand cmd : this.queue) {
            if (cmd.getSender().equals(sender)) {
                cmd.execute();
                this.queue.remove(cmd);
                return true;
            }
        }
        return false;
    }

    /**
     * キューから指定したコマンド送信者のコマンドを削除する
     * 
     * @param sender
     *            CommandSender
     */
    public void cancelQueue(CommandSender sender) {
        QueuedCommand cmd = null;
        for (QueuedCommand check : this.queue) {
            if (check.getSender().equals(sender)) {
                cmd = check;
                break;
            }
        }
        if (cmd != null) {
            this.queue.remove(cmd);
        }
    }
}
