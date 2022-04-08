package customCards.actions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;
import java.util.Random;

import static customCards.DefaultMod.makeAnimationPath;
import static customCards.util.TextureLoader.getTexture;

public class EPSanimate {

    private static final Texture ae = getTexture(makeAnimationPath("ae.png"));
    private static final Texture ect = getTexture(makeAnimationPath("ect.png"));
    private static final Texture ecv = getTexture(makeAnimationPath("ecv.png"));
    private static final Texture edm = getTexture(makeAnimationPath("edm.png"));
    private static final Texture eds = getTexture(makeAnimationPath("eds.png"));
    private static final Texture one = getTexture(makeAnimationPath("oneoffix.png"));
    private static final Texture ptcpro = getTexture(makeAnimationPath("ptcpro.png"));
    private static final Texture ptcstc = getTexture(makeAnimationPath("ptcstc.png"));
    private static final Texture pte = getTexture(makeAnimationPath("pte.png"));
    private static final Texture stc = getTexture(makeAnimationPath("stc.png"));

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
        allTextures.add(ae);
        allTextures.add(ect);
        allTextures.add(ecv);
        allTextures.add(edm);
        allTextures.add(eds);
        allTextures.add(one);
        allTextures.add(ptcpro);
        allTextures.add(ptcstc);
        allTextures.add(pte);
        allTextures.add(stc);

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
