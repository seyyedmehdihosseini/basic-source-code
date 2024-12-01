package com.basicsourcecode.codeGenerator;

import com.basicsourcecode.codeGenerator.sysCodeGenerator.DataEntity;
import com.basicsourcecode.codeGenerator.sysCodeGenerator.DataEntityRepository;
import com.basicsourcecode.codeGenerator.sysCodeGenerator.DataFiledRepository;
import com.basicsourcecode.utils.codeGeneratorUtils.DynamicCodeGeneratorConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class BasicProcessCodeGenerator {

    // \src\main\java\***
    protected static final Path PROJECT_SOURCE_DIR = DynamicCodeGeneratorConfig.getPathOutPutCodeProjectSourceDir();

    // ****/***/**** location of project
    protected static final Path BASE_PACKAGE_PATH = DynamicCodeGeneratorConfig.getOutPutCodeGeneratorBasePackagePath();

    /**
     * Appends the package declaration to the class content.
     *
     * @param packageName  The package name.
     * @param classContent The StringBuilder for class content.
     */
    protected static void appendPackageDeclaration(String packageName, StringBuilder classContent) {
        classContent.append("package ").append(BASE_PACKAGE_PATH.toString().replace(File.separator, ".")).append(".").append(packageName).append(";\n\n");
    }

    /**
     * Appends the closing of the class to the class content.
     *
     * @param classContent The StringBuilder for class content.
     */
    protected static void appendClassClosing(StringBuilder classContent) {
        classContent.append("}\n");
    }

    /**
     * Appends the closing of the class to the class content.
     *
     * @param packageName The package name.
     * @param nameClass   The name class .
     * @param body        The StringBuilder for class content.
     */
    protected static void writeClassToFile(String packageName, String nameClass, String body) {
        File file = new File(PROJECT_SOURCE_DIR + File.separator + packageName, nameClass + ".java");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(body);
            System.out.println("create " + nameClass + " generated in package " + packageName);
        } catch (IOException e) {
            System.err.println("Error writing " + nameClass + " to file: " + e.getMessage());
        }
    }

    /**
     * @param annotationClass call annotation for add in the property
     * @param attributes      attribute for value annotation
     *                        <p>
     *                        EN : add annotation for any property by value xml
     *                        FA : اضافه کردن انوتیسشن برای فیلد ها بر اساس مقادیری که در xml اضافه شده است.
     */
    protected static String addAnnotation(Class<?> annotationClass, Map<String, String> attributes, Set<String> imports) {
        // اضافه کردن مسیر import انوتیشن
        imports.add(annotationClass.getName());

        // ساخت رشته انوتیشن
        StringBuilder annotationBuilder = new StringBuilder();
        annotationBuilder.append("@").append(annotationClass.getSimpleName());

        if (attributes != null && !attributes.isEmpty()) {
            annotationBuilder.append("(");
            String attributesString = attributes.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + " = " + entry.getValue())
                    .collect(Collectors.joining(", "));
            annotationBuilder.append(attributesString);
            annotationBuilder.append(")");
        }

        return annotationBuilder.toString();
    }

    /**
     * EN :
     * FA : import کردن تمام انوتیشن ها بر اساس مقدایری که اضافه شده است.
     */
    protected static String generateImports(Set<String> imports) {
        return imports.stream()
                .filter(importName -> !importName.startsWith("java.lang")) // حذف java.lang
                .sorted() // مرتب‌سازی
                .map(importName -> "import " + importName + ";")
                .collect(Collectors.joining("\n"))
                + "\n\n";
    }

}
