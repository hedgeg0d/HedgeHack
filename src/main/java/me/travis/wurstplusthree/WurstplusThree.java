package me.travis.wurstplusthree;

import me.travis.BuildConfig;
import me.travis.wurstplusthree.command.Commands;
import me.travis.wurstplusthree.event.Events;
import me.travis.wurstplusthree.event.processor.EventProcessor;
import me.travis.wurstplusthree.gui.WurstplusGuiNew;
import me.travis.wurstplusthree.gui.hud.WurstplusHudGui;
import me.travis.wurstplusthree.gui.hud.element.HudManager;
import me.travis.wurstplusthree.hack.Hacks;
import me.travis.wurstplusthree.manager.*;
import me.travis.wurstplusthree.manager.fonts.DonatorFont;
import me.travis.wurstplusthree.manager.fonts.GuiFont;
import me.travis.wurstplusthree.manager.fonts.MenuFont;
import me.travis.wurstplusthree.networking.chat.handler.ChatHandling;
import me.travis.wurstplusthree.networking.chat.handler.ClientHandling;
import me.travis.wurstplusthree.networking.proxy.WurstPlusProxy;
import me.travis.wurstplusthree.setting.Settings;
import me.travis.wurstplusthree.util.RenderUtil2D;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * @author travis - began work on 8th april 2021
 */
@Mod(modid = WurstplusThree.MODID, name = WurstplusThree.MODNAME, version = WurstplusThree.MODVER)
public class WurstplusThree {

    public static final String MODID = "hedgehack";
    public static final String MODNAME = "HedgeHack";
    public static final String MODVER = "0.0.1+" + BuildConfig.GitHash;

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    // events
    public static Events EVENTS;
    public static EventProcessor EVENT_PROCESSOR;

    // commands
    public static Commands COMMANDS;

    // hacks
    public static Hacks HACKS;

    // settings
    public static Settings SETTINGS;

    // gui
    public static WurstplusGuiNew GUI2;
    public static WurstplusHudGui HUDGUI;

    // managers
    public static MenuFont MENU_FONT_MANAGER;
    public static HelpManager HELP_MANAGER;
    public static GuiFont GUI_FONT_MANAGER;
    public static DonatorFont DONATOR_FONT_MANAGER;
    public static FriendManager FRIEND_MANAGER;
    public static EnemyManager ENEMY_MANAGER;
    public static PopManager POP_MANAGER;
    public static ServerManager SERVER_MANAGER;
    public static PositionManager POS_MANAGER;
    public static RotationManager ROTATION_MANAGER;
    public static ConfigManager CONFIG_MANAGER;
    public static SongManager SONG_MANAGER;
    public static CapeManager CAPE_MANAGER;
    public static CosmeticManager COSMETIC_MANAGER;
    public static AltManager ALT_MANAGER;
    public static ClientHandling CLIENT_HANDLING;
    public static ChatHandling CHAT_HANDLING;
    public static HudManager HUD_MANAGER;
    public static KDManager KD_MANAGER;
    public static WurstPlusProxy PROXY;

    // megs weird thingy
    public static RenderUtil2D RENDER_UTIL_2D;

    @Mod.Instance
    public static WurstplusThree INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("loading " + MODNAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.load();
        LOGGER.info(MODNAME + " : " + MODVER + " has been loaded");
        Display.setTitle(MODNAME);
    }

    public void load() {
        EVENT_PROCESSOR = new EventProcessor();
        EVENTS = new Events();
        SETTINGS = new Settings();
        RENDER_UTIL_2D = new RenderUtil2D();
        COMMANDS = new Commands();
        HACKS = new Hacks();
        HUD_MANAGER = new HudManager();
        this.loadManagers();
        CLIENT_HANDLING = new ClientHandling();
        CHAT_HANDLING = new ChatHandling();
        CONFIG_MANAGER.loadConfig();
        GUI2 = new WurstplusGuiNew();
        HUDGUI = new WurstplusHudGui();
        //PROXY = new WurstPlusProxy(420, "0.0.0.0", WurstPlusProxy.Mode.CLIENT);
        //PLUGIN_LOADER = new Loader();
    }

    public static void unLoad() {
        CONFIG_MANAGER.saveConfig();
    }

    public void loadManagers() {
        MENU_FONT_MANAGER = new MenuFont();
        GUI_FONT_MANAGER = new GuiFont();
        FRIEND_MANAGER = new FriendManager();
        ENEMY_MANAGER = new EnemyManager();
        POP_MANAGER = new PopManager();
        SERVER_MANAGER = new ServerManager();
        POS_MANAGER = new PositionManager();
        HELP_MANAGER = new HelpManager();
        ROTATION_MANAGER = new RotationManager();
        CONFIG_MANAGER = new ConfigManager();
        SONG_MANAGER = new SongManager();
        CAPE_MANAGER = new CapeManager();
        DONATOR_FONT_MANAGER = new DonatorFont();
        COSMETIC_MANAGER = new CosmeticManager();
        ALT_MANAGER = new AltManager();
        KD_MANAGER = new KDManager();
    }

}

