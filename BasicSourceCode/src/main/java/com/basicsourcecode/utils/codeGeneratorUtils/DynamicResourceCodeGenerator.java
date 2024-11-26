package com.basicsourcecode.utils.codeGeneratorUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

class DynamicResourceCodeGenerator extends ConstantBasicCodeGenerator {

    private static final Path pathConfigurationFile = checkExistFileConfigCodeGenerator();

    protected static Path getDynamicResourceCodeGenerator() {
        Path codeGeneratorPath = getResourcesPath(pathConfigurationFile);
        return getDirName(pathConfigurationFile, codeGeneratorPath);
    }

    protected static Path getDynamicOutPutPathGenerateCode(){
        return getCodeOutputDir(pathConfigurationFile);
    }


    private static Path checkExistFileConfigCodeGenerator() {
        try {
            // جستجوی اولین فایل
            Optional<Path> foundFile = Files.walk(projectRootPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equalsIgnoreCase("configCodeGenerator.properties")
                    )
                    .findFirst();
            // در صورت نبودن از فایل های پیکر بندی اصلی استفاده میشود
            return foundFile.orElse(Files.walk(projectRootPath).filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equals("application.properties")
                            || path.getFileName().toString().equals("application.yml")).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("is not exist any file setting (application.properties ,application.yml , codeGenerator.properties )")));
        } catch (Exception e) {
            throw new IllegalArgumentException("is not exist any file setting (application.properties ,application.yml , codeGenerator.properties )");
        }
    }

    private static Path getResourcesPath(Path configPath) {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(configPath.toFile())) {
            properties.load(file);
            String propertyPath = properties.getProperty("code-generator.resource.path");

            // اگر چنین فیلدی درون فایل های پیکربندی وجود نداشته باشد مقدار پیش فرض جایگزین آن میشود
            if (propertyPath == null || propertyPath.isEmpty() || propertyPath.isBlank()) {
                return projectRootPath.resolve(Paths.get("src/main/resources"));
            }

            Path dirCodeGenerator = Paths.get(checkPropertyPath(propertyPath));

            String resourcePath = projectRootPath.resolve(dirCodeGenerator).toString();
            Path customResourcesPath = Paths.get(resourcePath);
            if (!Files.exists(customResourcesPath))
                throw new IllegalArgumentException("Custom resources path not found: " + customResourcesPath);
            return customResourcesPath;
        } catch (IOException e) {
            throw new RuntimeException("Error to reading configuration file: " + configPath);
        }
    }

    private static String checkPropertyPath(String propertyPath) {
        return (!propertyPath.startsWith(baseJavaPath) ? baseJavaPath +
                (!propertyPath.startsWith(".") ? "/" + propertyPath : propertyPath)
                : propertyPath).replace(".", "/");
    }

    private static Path getDirName(Path configPath, Path resourcePath) {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(configPath.toFile())) {
            properties.load(file);
            String dirName = properties.getProperty("code-generator.directory.name");
            // اگر چنین فیلدی درون فایل های پیکربندی وجود نداشته باشد مقدار پیش فرض جایگزین آن میشود
            Path dirPath = resourcePath.resolve(Paths.get(dirName != null
                    && !dirName.isBlank()
                    ? dirName
                    : "code-generator"));

            // بررسی میکنیم که اگر فایل دایرکتوری مورد نظر درون path نباشد از آن در آن path ایجاد شود.
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
                System.out.println("create directory by name " + dirPath.getFileName());
            }

            return dirPath;
        } catch (IOException e) {
            throw new RuntimeException("Error to reading configuration file: " + configPath);
        }
    }

    private static Path getCodeOutputDir(Path configPath) {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(configPath.toFile())) {
            properties.load(file);
            String outPutDir = properties.getProperty("code-generator.out-put.directory.path");

            return (outPutDir != null && outPutDir.isBlank()) ? Paths.get(checkPropertyPath(outPutDir)) : DynamicOutPutCodeGenerator.findBaseDirProject();
        } catch (IOException e) {
            throw new RuntimeException("Error to reading configuration file for : " + configPath);
        }
    }

}
