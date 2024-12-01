package com.basicsourcecode.codeGenerator.d_service;

import com.basicsourcecode.codeGenerator.BasicProcessCodeGenerator;
import com.basicsourcecode.codeGenerator.xmlClass.ModelXml;
import com.basicsourcecode.service.BasicService;
import com.basicsourcecode.service.BasicServiceImpl;
import com.basicsourcecode.utils.textutils.TextUtility;
import org.springframework.stereotype.Service;

public class ServiceClassGenerator extends BasicProcessCodeGenerator {

    public static String buildLayerService(String packageName, String nameEntityClass, String nameInterfaceRepository
            , ModelXml model) {
        String nameInterfaceService = buildInterfaceService(packageName, model, nameEntityClass);
        buildClassServiceImplementation(model, packageName, nameInterfaceService, nameEntityClass, nameInterfaceRepository);
        return nameInterfaceService;
    }

    private static String buildInterfaceService(String packageName, ModelXml model, String nameEntityClass) {
        String nameInterfaceService = TextUtility.toCapitalize(model.getName()) + "Service";
        String bodyInterfaceService = writeInterfaceService(packageName, model, nameInterfaceService, nameEntityClass);
        writeClassToFile(packageName, nameInterfaceService, bodyInterfaceService);

        return nameInterfaceService;
    }

    private static String writeInterfaceService(String packageName, ModelXml model, String nameInterfaceService, String nameEntityClass) {
        StringBuilder interfaceContent = new StringBuilder();

        appendPackageDeclaration(packageName, interfaceContent);
        appendImportDeclarationForInterface(interfaceContent);
        appendInterfaceDeclaration(nameInterfaceService, nameEntityClass, model.getTypeOfPrimaryKey().name()
                , interfaceContent);
        appendClassClosing(interfaceContent);

        return interfaceContent.toString();
    }

    private static void appendImportDeclarationForInterface(StringBuilder classContent) {
        classContent.append("import ").append(BasicService.class.getName()).append(";\n");
    }

    /**
     * Appends the interface declaration to the interface content.
     *
     * @param nameInterfaceService The name of the interface service.
     * @param nameEntityClass      The name of the class entity.
     * @param typeOfPrimaryKey     data type primary key .
     * @param interfaceContent     The StringBuilder for interface Content.
     */
    private static void appendInterfaceDeclaration(String nameInterfaceService, String nameEntityClass, String typeOfPrimaryKey,
                                                   StringBuilder interfaceContent) {
        interfaceContent.append("public interface ").append(nameInterfaceService)
                .append(" extends ").append(BasicService.class.getSimpleName()).append("<").append(nameEntityClass).append(" , ").append(typeOfPrimaryKey).append(">")
                .append(" {\n");
    }

    private static void buildClassServiceImplementation(ModelXml model, String packageName, String nameInterfaceService, String nameEntityClass
            , String nameInterfaceRepository) {
        String nameClassServiceImpl = nameInterfaceService + "Impl";
        String bodyInterfaceRepository = writeClassServiceImplementation(model, packageName, nameClassServiceImpl, nameEntityClass, nameInterfaceRepository, nameInterfaceService);
        writeClassToFile(packageName, nameClassServiceImpl, bodyInterfaceRepository);
    }

    private static String writeClassServiceImplementation(ModelXml model, String packageName, String nameClassServiceImpl, String nameEntityClass
            , String nameInterfaceRepository, String nameInterfaceService) {
        StringBuilder classContent = new StringBuilder();

        appendPackageDeclaration(packageName, classContent);
        appendImportDeclaration(classContent);
        appendAnnotations(classContent);
        appendClassServiceImplDeclaration(nameClassServiceImpl, nameEntityClass, model.getTypeOfPrimaryKey().name(), nameInterfaceRepository
                , nameInterfaceService, classContent);

        appendClassClosing(classContent);

        return classContent.toString();
    }

    private static void appendImportDeclaration(StringBuilder classContent) {
        classContent.append("import ").append(BasicServiceImpl.class.getName()).append(";\n");
        classContent.append("import ").append(Service.class.getName()).append(";\n\n");
    }

    /**
     * Appends annotations to the class content.
     *
     * @param classContent The StringBuilder for class content.
     */
    private static void appendAnnotations(StringBuilder classContent) {
        classContent.append("@").append(Service.class.getSimpleName()).append("\n");
    }

    private static void appendClassServiceImplDeclaration(String nameClassServiceImpl, String nameEntityClass,
                                                          String typeOfPrimaryKey, String nameInterfaceRepository,
                                                          String nameInterfaceService, StringBuilder classContent) {
        classContent.append("public class ").append(nameClassServiceImpl)
                .append(" extends ").append(BasicServiceImpl.class.getSimpleName()).append("<").append(nameEntityClass).append(" , ")
                .append(typeOfPrimaryKey).append(" , ").append(nameInterfaceRepository).append(">")
                .append(" implements ").append(nameInterfaceService)
                .append(" {\n");
    }


}