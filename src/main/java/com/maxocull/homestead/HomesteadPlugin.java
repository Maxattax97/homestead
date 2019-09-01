package com.maxocull.homestead;

import com.maxocull.homestead.command.HomesteadRootCommand;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

import com.google.inject.Inject;
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
        CommandSpec rootCommandSpec = CommandSpec.builder()
            .description(Text.of("Display usage for Homestead"))
            .permission("homestead.command")
            .executor(new HomesteadRootCommand())
            .build();

        Sponge.getCommandManager().register(this, rootCommandSpec, "homestead", "hstead", "hstd", "homestd", "hs");
    }
}
