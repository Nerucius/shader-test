#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform vec2 u_lightPos = vec2(0,0);
uniform float u_lightRad = 1;
uniform float u_lightFalloff = 20f;

void main(){
	// TODO change this from using a texture to just being an alpha overlay

    vec4 texColor = v_color * texture2D(u_texture, v_texCoords);
    float dst = length(gl_FragCoord.xy - u_lightPos.xy);
    texColor.a = clamp(0, 1, u_lightRad / dst) * 2;
    gl_FragColor = texColor;
}