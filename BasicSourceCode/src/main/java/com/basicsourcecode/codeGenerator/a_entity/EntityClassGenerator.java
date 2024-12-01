package com.basicsourcecode.codeGenerator.a_entity;

import com.basicsourcecode.codeGenerator.BasicProcessCodeGenerator;
import com.basicsourcecode.codeGenerator.xmlClass.ModelXml;
import com.basicsourcecode.codeGenerator.xmlClass.property.PropertyXmlGenerator;
import com.basicsourcecode.entity.BasicEntity;
import com.basicsourcecode.utils.textutils.TextUtility;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

public class EntityClassGenerator extends BasicProcessCodeGenerator {

    public static String buildClassEntity(String packageName , String nameEntityClass, ModelXml modelXml) {
        String bodyClassEntity = writeClassEntity(modelXml, packageName, nameEntityClass);

        writeClassToFile(packageName, nameEntityClass, bodyClassEntity);
        return nameEntityClass;
    }

    private static String writeClassEntity(ModelXml model, String packageName, String nameEntityClass) {
        StringBuilder classContent = new StringBuilder();

        Set<String> imports = new HashSet<>();

        String bodyClass = bodyClass(model, nameEntityClass, imports);
        String headerClass = headerClass(model, packageName, imports);

        classContent.append(headerClass);
        classContent.append(bodyClass);

        return classContent.toString();
    }


    private static String headerClass(ModelXml model, String packageName, Set<String> imports) {
        StringBuilder headerClass = new StringBuilder();
        appendPackageDeclaration(packageName, headerClass);
        String annotationsHeader = appendAnnotationsHeader(TextUtility.toSnakeCase(model.getName()).toUpperCase(), imports);
        appendImportDeclaration(headerClass, imports);
        headerClass.append(annotationsHeader);

        return headerClass.toString();
    }

    private static void appendImportDeclaration(StringBuilder classContent, Set<String> imports) {
        classContent.append(generateImports(imports));
    }

    private static String appendAnnotationsHeader(String nameClass, Set<String> imports) {
        StringBuilder headerClass = new StringBuilder();

        headerClass.append(addAnnotation(Entity.class, null, imports)).append("\n");
        headerClass.append(addAnnotation(Table.class, new HashMap<>() {{
            put("name", "\"TBL_" + nameClass + "\"");
        }}, imports)).append("\n");
        headerClass.append(addAnnotation(Setter.class, null, imports)).append("\n");
        headerClass.append(addAnnotation(Getter.class, null, imports)).append("\n");
        headerClass.append(addAnnotation(AllArgsConstructor.class, null, imports)).append("\n");
        headerClass.append(addAnnotation(NoArgsConstructor.class, null, imports)).append("\n");
        return headerClass.toString();
    }

    private static String bodyClass(ModelXml model, String nameEntityClass, Set<String> imports) {
        StringBuilder bodyClass = new StringBuilder();
        appendClassDeclaration(nameEntityClass, model.getTypeOfPrimaryKey().name(), bodyClass, imports);
        appendFields(model.getPropertyXmlGeneratorList(), bodyClass, imports);
        appendClassClosing(bodyClass);
        return bodyClass.toString();
    }

    /**
     * Appends the class declaration to the class content.
     *
     * @param nameEntityClass The name of the class.
     * @param classContent    The StringBuilder for class content.
     */
    private static void appendClassDeclaration(String nameEntityClass, String typeOfPrimaryKey,
                                               StringBuilder classContent, Set<String> imports) {
        classContent.append("public class ").append(nameEntityClass)
                .append(" extends ").append(BasicEntity.class.getSimpleName()).append("<").append(typeOfPrimaryKey)
                .append(">").append(" {\n");
        imports.add(BasicEntity.class.getName());
    }

    /**
     * Appends fields to the class content.
     *
     * @param properties   The list of fields.
     * @param classContent The StringBuilder for class content.
     */
    private static void appendFields(List<PropertyXmlGenerator> properties, StringBuilder classContent, Set<String> imports) {
        if (properties != null && !properties.isEmpty()) {
            for (PropertyXmlGenerator property : properties) {
                processAnyProperty(property, classContent, imports);
                classContent.append("    private ").append(property.getType().name()).append(" ")
                        .append(property.getName()).append(";\n");
            }
        }
    }

    private static void processAnyProperty(PropertyXmlGenerator property, StringBuilder classContent, Set<String> imports) {
        checkPropertyForColumn(property, classContent, imports);
        checkPropertyForNotNull(property, classContent, imports);

    }

    private static void checkPropertyForColumn(PropertyXmlGenerator property, StringBuilder classContent, Set<String> imports) {
        Map<String, String> attributes = new HashMap<>();

        if (property.getColumnName() != null)
            attributes.put("name", "\"" + property.getName() + "\"");
        if (property.getUnique() != null && property.getUnique())
            attributes.put("unique", "true");
        if (property.getLength() != null)
            attributes.put("length", String.valueOf(property.getLength()));

        classContent.append(addAnnotation(Column.class, attributes, imports)).append("\n");
    }

    private static void checkPropertyForNotNull(PropertyXmlGenerator property, StringBuilder classContent, Set<String> imports) {
        if (property.getRequired() != null && property.getRequired())
            classContent.append(addAnnotation(NotNull.class, null, imports));
    }

    /**
     * Appends getter and setter methods to the class content.
     *
     * @param fields       The list of fields.
     * @param classContent The StringBuilder for class content.
     */
    private static void appendGettersAndSetters(List<PropertyXmlGenerator> fields, StringBuilder classContent) {
        if (fields != null) {
            for (PropertyXmlGenerator field : fields) {
                appendGetterMethod(field, classContent);
                appendSetterMethod(field, classContent);
            }
        }
    }

    /**
     * Appends a getter method for a field.
     *
     * @param field        The field to generate the getter for.
     * @param classContent The StringBuilder for class content.
     */
    private static void appendGetterMethod(PropertyXmlGenerator field, StringBuilder classContent) {
        classContent.append("    public ").append(field.getType().name()).append(" get")
                .append(TextUtility.toCapitalize(field.getName())).append("() {\n")
                .append("        return ").append(field.getName()).append(";\n")
                .append("    }\n");
    }

    /**
     * Appends a setter method for a field.
     *
     * @param field        The field to generate the setter for.
     * @param classContent The StringBuilder for class content.
     */
    private static void appendSetterMethod(PropertyXmlGenerator field, StringBuilder classContent) {
        classContent.append("    public void set").append(TextUtility.toCapitalize(field.getName())).append("(")
                .append(field.getType().name()).append(" ").append(field.getName()).append(") {\n")
                .append("        this.").append(field.getName()).append(" = ").append(field.getName()).append(";\n")
                .append("    }\n");
    }

}
