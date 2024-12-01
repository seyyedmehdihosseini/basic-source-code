package com.basicsourcecode.codeGenerator.e_controller;

import com.basicsourcecode.codeGenerator.BasicProcessCodeGenerator;
import com.basicsourcecode.codeGenerator.xmlClass.ModelXml;
import com.basicsourcecode.controller.BasicController;
import com.basicsourcecode.utils.textutils.TextUtility;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ControllerClassGenerator extends BasicProcessCodeGenerator {

    public static void buildClassController(String packageName, ModelXml model, String nameEntityClass, String nameInterfaceService) {
        String nameClassController = TextUtility.toCapitalize(model.getName()) + "Controller";
        String bodyClassController = writeClassController(model, packageName, nameClassController, nameEntityClass, nameInterfaceService);
        writeClassToFile(packageName, nameClassController, bodyClassController);
    }

    private static String writeClassController(ModelXml model, String packageName, String nameClassController, String nameEntityClass
            , String nameInterfaceService) {
        StringBuilder classController = new StringBuilder();

        appendPackageDeclaration(packageName, classController);
        appendImportDeclaration(classController);
        appendAnnotations(nameEntityClass, classController);
        appendClassServiceImplDeclaration(nameClassController, nameEntityClass, model.getTypeOfPrimaryKey().name(), nameInterfaceService, classController);

        appendClassClosing(classController);

        return classController.toString();
    }

    private static void appendImportDeclaration(StringBuilder classContent) {
        classContent.append("import ").append(BasicController.class.getName()).append(";\n");
        classContent.append("import ").append(RestController.class.getName()).append(";\n");
        classContent.append("import ").append(RequestMapping.class.getName()).append(";\n\n");
    }


    /**
     * Appends annotations to the class content.
     *
     * @param classContent The StringBuilder for class content.
     */
    private static void appendAnnotations(String nameEntity, StringBuilder classContent) {
        classContent.append("@").append(RestController.class.getSimpleName()).append("\n");
        classContent.append("@").append(RequestMapping.class.getSimpleName()).append("(\"/").append(nameEntity).append("/\")\n");
    }


    private static void appendClassServiceImplDeclaration(String nameClassController, String nameEntityClass,
                                                          String typeOfPrimaryKey, String nameInterfaceService,
                                                          StringBuilder classContent) {
        classContent.append("public class ").append(nameClassController)
                .append(" extends ").append(BasicController.class.getSimpleName()).append("<").append(nameEntityClass).append(" , ")
                .append(typeOfPrimaryKey).append(" , ").append(nameInterfaceService).append(">")
                .append(" {\n");
    }

}