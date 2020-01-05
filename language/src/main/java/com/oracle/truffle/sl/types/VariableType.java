package com.oracle.truffle.sl.types;

import java.util.Arrays;

public enum VariableType {
    VEC3("vec3", Vector3.class),
    VEC4("vec4", Vector4.class),
    MAT("mat", Matrix.class),
    MAT3("mat3", Matrix3.class),
    MAT4("mat4", Matrix4.class),
    SCAL("scal", Scalar.class),
    CONSTANT("const", Constant.class);

    private final String text;
    private final Class clazz;

    VariableType(String text, Class clazz) {
        this.text = text;
        this.clazz = clazz;
    }

    public static VariableType getTypeForText(String text) {
        return Arrays.stream(VariableType.values())
                .filter(type -> type.getText().equals(text))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Type " + text + " not known."));
    }

    public String getText() {
        return text;
    }

    public Class getVariableClass() {
        return clazz;
    }
}
