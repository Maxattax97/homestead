package com.maxocull.homestead;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.command.args.GenericArguments;

import com.google.inject.Inject;
import com.maxocull.homestead.command.AbandonCommand;
import com.maxocull.homestead.command.ClaimCommand;
import com.maxocull.homestead.command.InfoCommand;
import com.maxocull.homestead.command.MarketBuyCommand;
import com.maxocull.homestead.command.MarketSellCommand;
import com.maxocull.homestead.command.PlotCommand;
import com.maxocull.homestead.command.ShareAddCommand;
import com.maxocull.homestead.command.ShareRemoveCommand;
import com.maxocull.homestead.command.WarpJumpCommand;
import com.maxocull.homestead.command.WarpListCommand;
import com.maxocull.homestead.command.WarpSetCommand;

import org.slf4j.Logger;

@Plugin(id = "homestead", name = "Homestead", version = "0.0.1", description = "A flexible land protection plugin")
public class HomesteadPlugin {
    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Homestead version 0.0.1 is now online");

        bindCommands();
    }

    private void bindCommands() {
        /*
         * Overview:
         *   /homestead
         *   /homestead plot
         *   /homestead claim
         *   /homestead abandon
         *   /homestead warp jump [name]
         *   /homestead warp set <name>
         *   /homestead warp delete <name>  To remove old warps
         *   /homestead warp list
         *   /homestead info
         *   /homestead config ?            To set per-chunk variables.
         *   /homestead share add <User>
         *   /homestead share remove <User>
         *   /homestead market buy
         *   /homestead market sell
         */

        CommandSpec plotCmd = CommandSpec.builder()
            .description(Text.of("Display information about the current chunk"))
            .permission("homestead.chunk")
            .executor(new PlotCommand())
            .build();

        CommandSpec claimCmd = CommandSpec.builder()
            .description(Text.of("Claim the current chunk"))
            .permission("homestead.claim")
            .executor(new ClaimCommand())
            .build();

        CommandSpec abandonCmd = CommandSpec.builder()
            .description(Text.of("Abandon the current or specified chunk"))
            .permission("homestead.claim") // Uses the same permission.
            .executor(new AbandonCommand())
            .build();


        CommandSpec warpJumpCmd = CommandSpec.builder()
            .description(Text.of("Warp to a saved and owned location"))
            .permission("homestead.warp")
            .executor(new WarpJumpCommand())
            .build();

        CommandSpec warpSetCmd = CommandSpec.builder()
            .description(Text.of("Save a warp point in an owned region"))
            .permission("homestead.warp")
            .executor(new WarpSetCommand())
            .build();

        CommandSpec warpListCmd = CommandSpec.builder()
            .description(Text.of("List owned warp points"))
            .permission("homestead.warp")
            .executor(new WarpListCommand())
            .build();

        CommandSpec infoCmd = CommandSpec.builder()
            .description(Text.of("Receive information on all claimed plots"))
            .permission("homestead.info")
            .executor(new InfoCommand())
            .build();

        CommandSpec shareAddCmd = CommandSpec.builder()
            .description(Text.of("Include an entity in the ownership of a plot"))
            .permission("homestead.share")
            .executor(new ShareAddCommand())
            .build();

        CommandSpec shareRemoveCmd = CommandSpec.builder()
            .description(Text.of("Remove an entity from the ownership of a plot"))
            .permission("homestead.share")
            .executor(new ShareRemoveCommand())
            .build();

        CommandSpec marketBuyCmd = CommandSpec.builder()
            .description(Text.of("Buy a plot which is for sale"))
            .permission("homestead.market.buy")
            .executor(new MarketBuyCommand())
            .build();

        CommandSpec marketSellCmd = CommandSpec.builder()
            .description(Text.of("Sell a plot which you own"))
            .permission("homestead.market.sell")
            .executor(new MarketSellCommand())
            .build();

        CommandSpec marketCmd = CommandSpec.builder()
            .description(Text.of("Buy, sell, and transfer the ownership of a plot"))
            .permission("homestead.market")
            .child(marketBuyCmd, "buy", "b")
            .child(marketSellCmd, "sell", "s")
            .build();

        CommandSpec shareCmd = CommandSpec.builder()
            .description(Text.of("Manage entities which share the full rights of a plot"))
            .permission("homestead.share")
            .child(shareAddCmd, "add", "a")
            .child(shareRemoveCmd, "remove", "r")
            .build();

        CommandSpec warpCmd = CommandSpec.builder()
            .description(Text.of("Warp between owned property"))
            .permission("homestead.warp")
            .child(warpJumpCmd, "jump", "j")
            .child(warpSetCmd, "set", "s")
            .child(warpListCmd, "list", "l")
            .build();

        CommandSpec rootCommandSpec = CommandSpec.builder()
            .description(Text.of("Display usage information for Homestead"))
            .permission("homestead.command")
            .child(plotCmd, "plot", "p")
            .child(claimCmd, "claim", "c")
            .child(abandonCmd, "abandon", "a")
            .child(warpCmd, "warp", "w")
            .child(shareCmd, "share", "s")
            .child(marketCmd, "market", "m")
            .build();

        Sponge.getCommandManager().register(this, rootCommandSpec, "homestead", "hs");
    }
}
