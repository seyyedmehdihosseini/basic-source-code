package com.basicsourcecode.utils.codeGeneratorUtils;

import com.basicsourcecode.utils.textutils.TextUtility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DynamicCodeGeneratorConfig {
    /**
     * EN :
     * FA :
     *
     * @example :
     * */
    public static Path getOutPutCodeGeneratorFullPath() {
        return DynamicResourceCodeGenerator.getDynamicOutPutPathGenerateCode();
    }
    /**
     * EN :
     * FA :
     *
     * @example :
     * */
    public static Path getReadDirForGenerateCode() {
        return DynamicResourceCodeGenerator.getDynamicResourceCodeGenerator();
    }
    /**
     * EN :
     * FA :
     *
     * @example :
     * */
    public static Path getOutPutCodeGeneratorBasePackagePath() {
        Path dynamicOutPutPathGenerateCode = DynamicResourceCodeGenerator.getDynamicOutPutPathGenerateCode();
        return Paths.get(TextUtility.removeBeforeAndIncludingKeyword(String.valueOf(dynamicOutPutPathGenerateCode), "src"+File.separator+"main"+File.separator+"java"+File.separator));
    }
    /**
     * EN :
     * FA :
     *
     * @example :
     * */
    public static Path getPathOutPutCodeProjectSourceDir(){
        Path dynamicOutPutPathGenerateCode = DynamicResourceCodeGenerator.getDynamicOutPutPathGenerateCode();
        return Paths.get(TextUtility.removeTextBeforeKeyword(String.valueOf(dynamicOutPutPathGenerateCode), "src"+ File.separator));
    }


}
