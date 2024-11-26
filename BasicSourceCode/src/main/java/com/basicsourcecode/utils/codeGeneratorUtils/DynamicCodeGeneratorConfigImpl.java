package com.basicsourcecode.utils.codeGeneratorUtils;

import java.nio.file.Path;

public class DynamicCodeGeneratorConfigImpl {
    public static Path getOutPutCodeGeneratorPath() {
        return DynamicResourceCodeGenerator.getDynamicOutPutPathGenerateCode();
    }

    public static Path getReadDirForGenerateCode() {
        return DynamicResourceCodeGenerator.getDynamicResourceCodeGenerator();
    }
}
