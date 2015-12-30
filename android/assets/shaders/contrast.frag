#version 130
#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float u_strenght = 1;

void main(){
    vec4 texColor = v_color * texture2D(u_texture, v_texCoords);

    texColor.r = smoothstep(0, u_strenght, texColor.r);
    texColor.g = smoothstep(0, u_strenght, texColor.g);
    texColor.b = smoothstep(0, u_strenght, texColor.b);
    //texColor.rgb = clamp(texColor.rgb, vec3(0,0,0), vec3(1,1,1));
    texColor.rgb = texColor.rgb / (u_strenght);

    gl_FragColor = texColor;
}