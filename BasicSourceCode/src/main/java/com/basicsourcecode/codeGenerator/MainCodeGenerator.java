package com.basicsourcecode.codeGenerator;

import com.basicsourcecode.codeGenerator.a_entity.EntityClassGenerator;
import com.basicsourcecode.codeGenerator.c_repository.RepositoryClassGenerator;
import com.basicsourcecode.codeGenerator.d_service.ServiceClassGenerator;
import com.basicsourcecode.codeGenerator.e_controller.ControllerClassGenerator;
import com.basicsourcecode.codeGenerator.sysCodeGenerator.*;
import com.basicsourcecode.codeGenerator.xmlClass.ModelXml;
import com.basicsourcecode.utils.codeGeneratorUtils.DynamicCodeGeneratorConfig;
import com.basicsourcecode.utils.textutils.TextUtility;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;


public class MainCodeGenerator extends BasicProcessCodeGenerator {

    public static void main(String[] args) {
        // Generate code from XML files in the specified package directory
        generateCodeFromXmlPackage();
    }

    /**
     * EN : Get all xml files from the specified folder entered in its settings based on the base code.
     * FA : دریافت تمام فایل های xml از پوشه مشخص شده که در تنظیمات آن وارد شده است بر اساس کد های پایه
     */
    public static void generateCodeFromXmlPackage() {
        File[] xmlFiles = getXmlFilesFromDirectoryXmlPackage();
        if (xmlFiles != null) {
            for (File xmlFile : xmlFiles) {
                processXmlFile(xmlFile);
            }
        }
    }

    /**
     * Retrieves XML files from the specified directory.
     *
     * @return Array of XML files.
     */
    private static File[] getXmlFilesFromDirectoryXmlPackage() {
        File directory = new File(String.valueOf(DynamicCodeGeneratorConfig.getReadDirForGenerateCode()));
        return directory.listFiles((dir, name) -> name.endsWith(".xml"));
    }

    /**
     * Processes each XML file to generate the corresponding Java class.
     *
     * @param xmlFile The XML file to be processed.
     */
    private static void processXmlFile(File xmlFile) {
        try {
            ModelXml modelXml = unmarshallXmlFile(xmlFile);
            if (modelXml.getOverwrite()) {
                generateModelClass(modelXml);
            }
        } catch (JAXBException e) {
            System.err.println("Error processing XML file " + xmlFile.getName() + ": " + e.getMessage());
        }
    }

    /**
     * Unmarshalls XML content into a Model object.
     *
     * @param xmlFile The XML file to unmarshall.
     * @return The Model object.
     * @throws JAXBException If unmarshalling fails.
     */
    private static ModelXml unmarshallXmlFile(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ModelXml.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ModelXml) unmarshaller.unmarshal(xmlFile);
    }

    /**
     * Generates a Java class based on the provided model.
     *
     * @param modelXml The model object containing class details.
     */
    private static void generateModelClass(ModelXml modelXml) {

        String packageName = TextUtility.toCamelCase(modelXml.getName());
        String nameEntity = TextUtility.toCapitalize(modelXml.getName()) + "Entity";

        createPackageDirectories(packageName);

//        if (modelXml.getEntitySaveToDB()){
//            DataEntity saveDataEntity = ServiceHelperCodeGenerator.registryDataEntity(modelXml);
//            List<DataFiled> listDataField = ServiceHelperCodeGenerator.registryListDataField(modelXml.getPropertyXmlGeneratorList(), saveDataEntity.getEntityNameEn());
//        }

        String nameEntityClass = EntityClassGenerator.buildClassEntity(packageName, nameEntity, modelXml);
        String nameInterfaceRepository = RepositoryClassGenerator.buildInterfaceRepository(packageName, nameEntityClass, modelXml);
        String nameInterfaceService = ServiceClassGenerator.buildLayerService(packageName, nameEntityClass, nameInterfaceRepository, modelXml);
        ControllerClassGenerator.buildClassController(packageName, modelXml, nameEntityClass, nameInterfaceService);

    }

    /**
     * Creates the directories for the given package.
     *
     * @param packageName The package name.
     */
    private static void createPackageDirectories(String packageName) {
        File packageDir = new File(PROJECT_SOURCE_DIR + File.separator + packageName);
        if (!packageDir.exists()) {
            packageDir.mkdirs();
        } else {
            packageDir.delete();
            packageDir.mkdirs();
        }

    }


}
