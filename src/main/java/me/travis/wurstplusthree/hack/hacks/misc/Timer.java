package me.travis.wurstplusthree.hack.hacks.misc;

import me.travis.wurstplusthree.event.events.PacketEvent;
import me.travis.wurstplusthree.event.processor.CommitEvent;
import me.travis.wurstplusthree.event.processor.EventPriority;
import me.travis.wurstplusthree.hack.Hack;
import me.travis.wurstplusthree.hack.HackPriority;
import me.travis.wurstplusthree.setting.type.BooleanSetting;
import me.travis.wurstplusthree.util.ClientMessage;
import net.minecraft.network.login.server.SPacketEnableCompression;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import net.minecraft.network.login.server.SPacketLoginSuccess;
import net.minecraft.network.play.client.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.status.server.SPacketPong;
import net.minecraft.network.status.server.SPacketServerInfo;

import me.travis.wurstplusthree.setting.type.DoubleSetting;

import java.util.*;
import java.util.stream.IntStream;

import java.util.Arrays;

@Hack.Registration(name = "Timer", description = "Speed ups time", category = Hack.Category.MISC, priority = HackPriority.Lowest)
public final class Timer extends Hack
{



    DoubleSetting tickSpeed = new DoubleSetting("Multiplier",  1.0, 0.1 ,12.0, this);

    Float multiplier = tickSpeed.getValue().floatValue();

    @Override
    public void onEnable() {
         Timer.mc.timer.tickLength = 50.0f/tickSpeed.getValue().floatValue();
    }

    @Override 
    public void onDisable() {
            Timer.mc.timer.tickLength = 50.0f;
        }

        @Override
        public void onUpdate() {
        multiplier = tickSpeed.getValue().floatValue();
        onEnable() ;
    }

}
