package com.dice_programming_etc.DoubleJump;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class CommandClass  implements CommandExecutor,TabCompleter {
    protected final static String WJ_KEY = "wjump";
    DoubleJump doubleJump = DoubleJump.getPlugin(DoubleJump.class);
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("wjump")) {
            if (sender instanceof Player) {
                // プレイヤーが実行した場合の処理
                Player player = (Player) sender;
                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("on")){
                        player.setMetadata(
                                WJ_KEY,
                                new FixedMetadataValue(
                                        doubleJump, // プラグイン
                                        "on" // 設定したい値
                                ));
                        player.sendMessage("二段ジャンプをONにしました。");
                    }
                    else if(args[0].equalsIgnoreCase("off")){
                        player.setMetadata(
                                WJ_KEY,
                                new FixedMetadataValue(
                                        doubleJump, // プラグイン
                                        "off" // 設定したい値
                                ));
                        player.sendMessage("二段ジャンプをOFFにしました。");
                    }
                }
                else{
                    List<MetadataValue> list = player.getMetadata(WJ_KEY);
                    player.sendMessage(" on/off を指定してください。");
                    for(MetadataValue value :list){
                        player.sendMessage(value.value().toString());
                    }
                }
            } else {
                // サーバーが実行した場合の処理
                getLogger().info("このコマンドはプレイヤーしか実行できません。");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("wjump")){
            if (args.length == 1) {
                if (args[0].length() == 0) { // /wjumpまで
                    return Arrays.asList("on","off");
                } else {
                    if ("on".startsWith(args[0]) && "off".startsWith(args[0])) {
                        return Arrays.asList("on","off");
                    }
                    else if("on".startsWith(args[0])){
                        return Collections.singletonList("on");
                    }
                    else if("off".startsWith(args[0])){
                        return Collections.singletonList("off");
                    }
                }
            }
        }
        return null;
    }
}