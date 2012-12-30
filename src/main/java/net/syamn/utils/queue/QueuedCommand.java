/**
 * SakuraUtils - Package: net.syamn.utils.queue
 * Created: 2012/12/26 9:21:35
 */
package net.syamn.utils.queue;

import java.util.Calendar;
import java.util.List;

import net.syamn.utils.Util;

import org.bukkit.command.CommandSender;

/**
 * QueuedCommand (QueuedCommand.java)
 * @author syam(syamn)
 */
public class QueuedCommand {
    private CommandSender sender;
    private Queueable queueable;
    private List<String> args;
    private int timeoutSec;
    private Calendar requestDate;
    private boolean already = false;

    public QueuedCommand(CommandSender sender, Queueable queueable, List<String> args, int timeoutSec) {
        this.sender = sender;
        this.queueable = queueable;
        this.args = args;
        this.timeoutSec = timeoutSec;

        this.requestDate = Calendar.getInstance();
    }

    public void execute() {
        // タイムアウトチェック
        this.requestDate.add(13, this.timeoutSec);
        if (!this.requestDate.after(Calendar.getInstance())) {
            Util.message(sender, "&cこのコマンドは時間切れです！元のコマンドをもう一度入力してください！");
            return;
        }

        // 多重進入防止
        if (already) {
            Util.message(sender, "&cこのコマンドは既に実行されています！再度実行するには元のコマンドから入力し直してください！");
            return;
        }
        already = true;
        this.queueable.executeQueue(this.args); // 実行
    }

    public CommandSender getSender() {
        return this.sender;
    }
}
