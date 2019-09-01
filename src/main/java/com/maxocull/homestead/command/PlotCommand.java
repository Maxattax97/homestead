package com.maxocull.homestead.command;

import com.flowpowered.math.vector.Vector3i;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class PlotCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src.getCommandSource().isPresent() && src.getCommandSource().get() instanceof Player) {
            Player caller = (Player) src.getCommandSource().get();

            Vector3i chunk = caller.getLocation().getChunkPosition();

            src.sendMessage(Text.of("You are at chunk ("
                        + chunk.getX() + ", "
                        + chunk.getY() + ", "
                        + chunk.getZ()
                        + ") in " + caller.getLocation().getExtent().getName()
                        + " (" + caller.getLocation().getExtent().getUniqueId()
                        + ")"));

            return CommandResult.success();
        }

        throw new CommandException(Text.of("Only players may use this command"));
    }
}
