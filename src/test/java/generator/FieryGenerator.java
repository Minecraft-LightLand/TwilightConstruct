package generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class FieryGenerator {

	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();

	public static final JsonObject JSON = new JsonObject();

	static {
		var obj = new JsonObject();
		obj.addProperty("interpolate", true);
		obj.addProperty("frametime", 18);
		JSON.add("animation", obj);
	}

	public static void main(String[] args) throws Exception {
		File base = new File("./z_base");
		iterate(base, "");
	}

	private static void iterate(File root, String path) throws Exception {
		for (var e : root.listFiles()) {
			if (e.isDirectory()) {
				iterate(e, path + "/" + e.getName());
			} else {
				if (e.getName().endsWith("fiery.png"))
					generate(e, path + "/" + e.getName());
			}
		}
	}

	private static void generate(File file, String path) throws Exception {
		BufferedImage dark = ImageIO.read(file);
		BufferedImage light = ImageIO.read(new File("./z_highlight" + path));
		int sx = dark.getWidth();
		int sy = dark.getHeight();
		BufferedImage ans = new BufferedImage(sx, sy * 2, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < sx; x++) {
			for (int y = 0; y < sy; y++) {
				ans.setRGB(x, y, dark.getRGB(x, y));
				ans.setRGB(x, sy + y, light.getRGB(x, y));
			}
		}

		File out = new File("./z_out" + path);
		if (!out.exists()) {
			if (!out.getParentFile().exists())
				out.getParentFile().mkdirs();
			out.createNewFile();
		}
		ImageIO.write(ans, "PNG", out);
		File meta = new File("./z_out" + path + ".mcmeta");
		FileWriter w = new FileWriter(meta, StandardCharsets.UTF_8);
		w.write(GSON.toJson(JSON));
		w.close();
	}


}
