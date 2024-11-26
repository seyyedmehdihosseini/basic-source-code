package com.basicsourcecode.utils.codeGeneratorUtils;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

class DynamicOutPutCodeGenerator extends ConstantBasicCodeGenerator {


    private static Path findSourceRoot(String classPath) {
        Path currentPath = Paths.get(classPath);

        // پیمایش به سمت بالا برای پیدا کردن پوشه `src`
        while (currentPath != null) {
            File currentFile = currentPath.toFile();

            // اگر پوشه `src` وجود دارد
            if (new File(currentFile, baseJavaPath).exists()) {
                return currentPath;
            }

            // رفتن به مسیر والد
            currentPath = currentPath.getParent();
        }

        throw new IllegalStateException("Could not determine source root from class path: " + classPath);
    }

    private static String findSpringBootApplicationClass(Path sourceRoot) throws ClassNotFoundException {
        File srcDir = new File(sourceRoot.toFile(), baseJavaPath);

        // بازگشتی فایل‌های جاوا را جستجو می‌کند
        return searchForAnnotatedClass(srcDir);
    }

    private static String searchForAnnotatedClass(File directory) throws ClassNotFoundException {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                // جستجوی بازگشتی درون دایرکتوری‌ها
                String result = searchForAnnotatedClass(file);
                if (result != null) {
                    return result;
                }
            } else if (file.getName().endsWith(".java")) {
                // بررسی فایل‌های جاوا
                String className = extractClassName(file);
                if (isAnnotatedWith(className)) {
                    return className.replace(removeNameClass(file, directory), "");
                }
            }
        }
        return null;
    }


    private static String removeNameClass(File classname, File rootPath) throws ClassNotFoundException {
        String relativePath = classname.getAbsolutePath().replace(rootPath.getAbsolutePath(), "").replace(File.separator, ".");
        return relativePath.substring(1, relativePath.length() - 5);
    }

    private static String extractClassName(File javaFile) {
        String relativePath = javaFile.getAbsolutePath().replace(projectRootPath.resolve(baseJavaPath).toString(), "").replace(File.separator, ".");
        return relativePath.substring(1, relativePath.length() - 5); // حذف ".java"
    }

    private static boolean isAnnotatedWith(String className) throws ClassNotFoundException {
        // بارگذاری کلاس و بررسی انوتیشن
        Class<?> clazz = Class.forName(className);
        return clazz.isAnnotationPresent(SpringBootApplication.class);
    }


    protected static Path findBaseDirProject() {
        try {
            // پیدا کردن ریشه سورس کد
            Path sourceRoot = findSourceRoot(projectRootPath.toString());
            System.out.println("Source Root Path: " + sourceRoot);

            // پیدا کردن کلاس دارای @SpringBootApplication
            String mainClass = findSpringBootApplicationClass(sourceRoot);
            return projectRootPath.resolve(baseJavaPath).resolve(mainClass.replace(".", "\\"));
        } catch (Exception e) {
            System.out.println("ERROR WHEN READ DIRECTORY BASE PROJECT .... " + e.getMessage());
            return null;
        }
    }

}
