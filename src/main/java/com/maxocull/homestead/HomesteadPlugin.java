package com.maxocull.homestead;

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
    }
}
