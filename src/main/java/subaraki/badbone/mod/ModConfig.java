package subaraki.badbone.mod;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = BadBone.MODID)
public class ModConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 1, max = 24_000 * 10)
    public static int frequencyArthritis = 24_000;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 24_000 * 10)
    public static int frequencyHurt = 24_000 / 3;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1000)
    public static int chanceHurt = 10;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1000)
    public static int frequencyKnee = 1;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 24_000 * 30)
    public static int frequencyEyes = 24_000;
}
