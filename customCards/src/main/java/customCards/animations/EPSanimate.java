package customCards.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;
import java.util.Random;

import static customCards.DefaultMod.makeAnimationPath;
import static customCards.util.TextureLoader.getTexture;

public class EPSanimate {

    private static final float RIGHT_X = Settings.WIDTH + 100 * Settings.scale;
    private static final float LEFT_X = -100 * Settings.scale;
    private static final float TOP_Y = Settings.HEIGHT - 50.0f * Settings.scale;

    private static final int IMG_WIDTH = 142;
    private static final int IMG_HEIGHT = 142;
    private static final float OFFSET = IMG_WIDTH / 2.0f;

    private final ArrayList<StarPosition> appPositions;
    private final ArrayList<Texture> allTextures;
    private final Random random;

    public EPSanimate() {
        appPositions = new ArrayList<>();

        allTextures = new ArrayList<>();
        allTextures.add(getTexture(makeAnimationPath("ae.png")));
        allTextures.add(getTexture(makeAnimationPath("ect.png")));
        allTextures.add(getTexture(makeAnimationPath("ecv.png")));
        allTextures.add(getTexture(makeAnimationPath("edm.png")));
        allTextures.add(getTexture(makeAnimationPath("eds.png")));
        allTextures.add(getTexture(makeAnimationPath("oneoffix.png")));
        allTextures.add(getTexture(makeAnimationPath("ptcpro.png")));
        allTextures.add(getTexture(makeAnimationPath("ptcstc.png")));
        allTextures.add(getTexture(makeAnimationPath("pte.png")));
        allTextures.add(getTexture(makeAnimationPath("stc.png")));

        random = new Random();
    }

    public void addShootingStars(int stars) {
        if (stars < appPositions.size()) {
            for (int i = stars; i < appPositions.size(); i++) {
                appPositions.get(i).isDone = true; //start fade
            }
        }
        while (appPositions.size() < stars) {
            appPositions.add(new StarPosition(random.nextInt(10)));
        }
    }

    public void render(SpriteBatch sb, boolean fading) {
        for (StarPosition pos : appPositions) {
            if (fading) {
                pos.isDone = true;
            }
            pos.update();
        }

        appPositions.removeIf(star -> star.isDone && star.renderColor.a <= 0.0f);

        for (StarPosition pos : appPositions) {
            float x = MathUtils.lerp(LEFT_X, RIGHT_X, (pos.progress + 1) / 2.0f);
            float progress = pos.progress + 1 / 2.0f;

            sb.setColor(pos.renderColor);
            sb.draw(allTextures.get(pos.position), x - OFFSET, TOP_Y - (progress * TOP_Y) + pos.shift,
                    OFFSET, OFFSET, IMG_WIDTH, IMG_HEIGHT,
                    Settings.scale, Settings.scale, pos.angle, 0, 0,
                    IMG_WIDTH, IMG_HEIGHT, false, false);
        }
    }
}
