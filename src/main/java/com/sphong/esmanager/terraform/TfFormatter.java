package com.sphong.esmanager.terraform;

import org.springframework.stereotype.Component;

@Component
public class TfFormatter {
    public static String convertToTf(TerraformVariable variable) {
        return createTfVariable(getFieldStr(variable.getName()), getTypeStr(variable.getType()), getDefaultValueStr(variable.getDefaultValue()));
    }

    private static String getFieldStr(String name) {
        return "variable \""+ name + "\"";
    }

    private static String getTypeStr(String type) {
        return "type = " + type;
    }

    private static String getDefaultValueStr(String defaultValue) {
        return "default = \"" + defaultValue + "\"";
    }

    private static String createTfVariable(String fieldStr, String typeStr, String defaultValueStr) {
        return fieldStr + "{\n\t" + typeStr + "\n\t" + defaultValueStr + "\n}\n";
    }
}
