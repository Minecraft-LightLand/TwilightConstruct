package dev.xkmc.twilight_construct.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class TCModConfig {

	public static class Client {

		public final ForgeConfigSpec.BooleanValue enableRedMarkerEffect;

		Client(ForgeConfigSpec.Builder builder) {
			enableRedMarkerEffect = builder.define("enableRedMarkerEffect", true);
		}


	}

	public static class Common {

		public final ForgeConfigSpec.IntValue mysticGrowthBaseInterval;
		public final ForgeConfigSpec.DoubleValue synergyRepairChance;
		public final ForgeConfigSpec.DoubleValue twilightMiningSpeedBonus;
		public final ForgeConfigSpec.DoubleValue twilightAttackDamageBonus;
		public final ForgeConfigSpec.IntValue reappearingTicks;
		public final ForgeConfigSpec.DoubleValue primalBurstBonus;
		public final ForgeConfigSpec.DoubleValue primalInstinctThreshold;

		Common(ForgeConfigSpec.Builder builder) {
			mysticGrowthBaseInterval = builder.comment("Base interval for mystic growth repair in ticks. Should be a multiple of 6")
					.defineInRange("mysticGrowthBaseInterval", 150, 6, 150000);
			synergyRepairChance = builder.comment("Chance for Synergy to repair items per second at 1 steeleaf")
					.comment("Every time steeleaf count is doubled, increase repair chance by this number as well")
					.comment("Example: when player holds 64 steeleaf, the repair chance will be 7x this number")
					.defineInRange("synergyRepairChance", 0.05, 0, 1);
			twilightMiningSpeedBonus = builder.comment("Mining speed percentage bonus for Twilight modifier, when user is in Twilight Forest")
					.defineInRange("twilightMiningSpeedBonus", 0.2, 0, 1);
			twilightAttackDamageBonus = builder.comment("Damage percentage bonus for Twilight modifier, when user is not in Twilight Forest")
					.defineInRange("twilightAttackDamageBonus", 0.05, 0, 1);
			reappearingTicks = builder.comment("Invulnerability tick bonus for Reappearing modifier")
					.defineInRange("reappearingTicks", 5, 0, 20);
			primalBurstBonus = builder.comment("Primal Burst maximum tool speed bonus at near-zero durability")
					.defineInRange("primalBurstBonus", 0.1, 0, 1);
			primalInstinctThreshold = builder.comment("Primal Instinct health threshold")
					.defineInRange("primalInstinctThreshold", 0.5, 0, 1);
		}

	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	static {

		final Pair<Common, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = commonPair.getRight();
		COMMON = commonPair.getLeft();


		final Pair<Client, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = clientPair.getRight();
		CLIENT = clientPair.getLeft();
	}

	public static void init() {
		register(ModConfig.Type.COMMON, COMMON_SPEC);
		register(ModConfig.Type.CLIENT, CLIENT_SPEC);
	}

	private static void register(ModConfig.Type type, IConfigSpec<?> spec) {
		var mod = ModLoadingContext.get().getActiveContainer();
		String path = mod.getModId() + "-" + type.extension() + ".toml";
		ModLoadingContext.get().registerConfig(type, spec, path);
	}

}
