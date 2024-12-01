package com.basicsourcecode.codeGenerator.c_repository;

import com.basicsourcecode.codeGenerator.BasicProcessCodeGenerator;
import com.basicsourcecode.codeGenerator.xmlClass.ModelXml;
import com.basicsourcecode.repository.BasicRepository;
import com.basicsourcecode.utils.textutils.TextUtility;
import org.springframework.stereotype.Repository;

public class RepositoryClassGenerator extends BasicProcessCodeGenerator {
    public static String buildInterfaceRepository(String packageName, String nameEntityClass, ModelXml model) {
        String nameInterfaceRepository = TextUtility.toCapitalize(model.getName()) + "Repository";
        String bodyInterfaceRepository = writeInterfaceRepository(model, packageName, nameInterfaceRepository, nameEntityClass);
        writeClassToFile(packageName, nameInterfaceRepository, bodyInterfaceRepository);
        return nameInterfaceRepository;
    }

    private static String writeInterfaceRepository(ModelXml model, String packageName, String nameInterfaceRepository, String nameEntityClass) {
        StringBuilder classContent = new StringBuilder();

        appendPackageDeclaration(packageName, classContent);
        appendImportDeclaration(classContent);
        appendAnnotations(classContent);
        appendInterfaceDeclaration(nameInterfaceRepository, nameEntityClass,
                model.getTypeOfPrimaryKey().name(), classContent);

        appendClassClosing(classContent);

        return classContent.toString();
    }

    private static void appendImportDeclaration(StringBuilder classContent) {
        classContent.append("import ").append(BasicRepository.class.getName()).append(";\n");
        classContent.append("import ").append(Repository.class.getName()).append(";\n\n");
    }


    /**
     * Appends annotations to the class content.
     *
     * @param classContent The StringBuilder for class content.
     */
    private static void appendAnnotations(StringBuilder classContent) {
        classContent.append("@").append(Repository.class.getSimpleName()).append("\n");
    }

    /**
     * Appends the class declaration to the class content.
     *
     * @param entityName              The name of the entity class name.
     * @param nameInterfaceRepository The name of the class.
     * @param classContent            The StringBuilder for class content.
     */
    private static void appendInterfaceDeclaration(String nameInterfaceRepository, String entityName
            , String typeOfPrimaryKey, StringBuilder classContent) {
        classContent.append("public interface ").append(nameInterfaceRepository)
                .append(" extends ").append(BasicRepository.class.getSimpleName()).append("<").append(entityName).append(" , ").append(typeOfPrimaryKey)
                .append(">").append(" {\n");
    }
}
