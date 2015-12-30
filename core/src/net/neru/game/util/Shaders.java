package net.neru.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Created by Akira on 2015-12-30.
 */
public class Shaders {

    public static final ShaderProgram DEFAULT = Shaders.load("default");
    public static final ShaderProgram CONTRAST = Shaders.load("contrast");
    public static final ShaderProgram LIGHTMAP = Shaders.load("light");

    public static ShaderProgram load(String name) {
        FileHandle vert = Gdx.files.internal("shaders/" + name + ".vert");
        FileHandle frag = Gdx.files.internal("shaders/" + name + ".frag");

        if (!vert.exists())
            vert = Gdx.files.internal("shaders/default.vert");

        if (!frag.exists())
            vert = Gdx.files.internal("shaders/default.frag");

        ShaderProgram sp = new ShaderProgram(vert.readString(), frag.readString());

        if (!sp.isCompiled()) {
            // Exit if error
            Gdx.app.log("SHADER", "Error compiling");
            System.out.println(sp.getLog());
            Gdx.app.exit();
        }
        return sp;
    }
}
