/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.cloud.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import io.geekidea.cloud.generator.config.*;
import io.geekidea.cloud.generator.config.query.ScpMySqlQuery;
import io.geekidea.cloud.generator.config.query.ScpSqlServerQuery;
import io.geekidea.cloud.generator.constant.GeneratorConstant;
import io.geekidea.cloud.generator.exception.GeneratorException;
import io.geekidea.cloud.generator.properties.GeneratorProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * spring-cloud-plus代码生成器
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
@Data
@Component
@Accessors(chain = true)
public class CodeGenerator {

    public void generator(GeneratorProperties generatorProperties) {
        if (generatorProperties == null) {
            throw new GeneratorException("generatorProperties不能为空");
        }
        List<TableConfig> tableConfig = generatorProperties.getTableConfig();
        if (CollectionUtils.isEmpty(tableConfig)) {
            throw new GeneratorException("tableConfig不能为空");
        }

        // 循环生成
        for (TableConfig generatorTable : tableConfig) {
            String tableName = generatorTable.getTableName();
            String pkIdName = generatorTable.getPkIdName();
            // 生成代码
            generator(tableName, pkIdName, generatorProperties);
        }
    }

    /**
     * 初始化变量
     */
    public void init(String tableName, GeneratorProperties generatorProperties) {
        String parentPackage = generatorProperties.getParentPackage();
        if (StringUtils.isBlank(parentPackage)) {
            throw new GeneratorException("项目父包不能为空");
        }
        // 如果包路径为空，转换包名称路径
        if (StringUtils.isNotBlank(parentPackage)) {
            generatorProperties.setParentPackagePath(parentPackage.replaceAll("\\.", Matcher.quoteReplacement(File.separator)));
        }

        String mavenModuleName = generatorProperties.getMavenModuleName();
        if (StringUtils.isBlank(mavenModuleName)) {
            throw new GeneratorException("mavenModuleName不能为空");
        }
        if (StringUtils.isBlank(tableName)) {
            throw new GeneratorException("tableName不能为空");
        }

        String mavenApiModuleName = generatorProperties.getMavenApiModuleName();
        String mavenServerModuleName = generatorProperties.getMavenServerModuleName();
        if (StringUtils.isBlank(mavenApiModuleName)) {
            mavenApiModuleName = mavenModuleName + "-api";
        }
        if (StringUtils.isBlank(mavenServerModuleName)) {
            mavenServerModuleName = mavenModuleName + "-server";
        }

    }


    /**
     * 生成代码
     */
    public void generator(String tableName, String pkIdName, GeneratorProperties generatorProperties) {
        // 初始化变量
        init(tableName, generatorProperties);

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        MybatisPlusGeneratorConfig mybatisPlusGeneratorConfig = generatorProperties.getMybatisPlusGeneratorConfig();
        if (mybatisPlusGeneratorConfig == null) {
            throw new GeneratorException("mybatisPlusGeneratorConfig不能为空");
        }

        // 全局配置
        GlobalConfig globalConfig = mybatisPlusGeneratorConfig.getGlobalConfig();
        if (globalConfig == null) {
            throw new GeneratorException("globalConfig不能为空");
        }

        String mavenModuleName = generatorProperties.getMavenModuleName();
        String mavenApiModuleName = generatorProperties.getMavenApiModuleName();
        String mavenServerModuleName = generatorProperties.getMavenServerModuleName();
        String moduleName = generatorProperties.getModuleName();
        String outputDir = generatorProperties.getOutputDir();

        String mavenModuleOutputDir = null;
        String mavenApiModuleOutputDir = null;
        String mavenServerModuleOutputDir = null;

        String apiJavaOutputDir = null;
        String serverJavaOutputDir = null;
        String serverResourcesOutputDir = null;
        String serverMapperXmlOutputDir = null;

        if (StringUtils.isBlank(outputDir)) {
            String rootProjectFile = System.getProperty(GeneratorConstant.USER_DIR);
            outputDir = rootProjectFile + File.separator + mavenModuleName;
            mavenModuleOutputDir = outputDir;
        }
        log.info("outputDir: {}", outputDir);

        mavenApiModuleOutputDir = mavenModuleOutputDir + File.separator + mavenApiModuleName;
        mavenServerModuleOutputDir = mavenModuleOutputDir + File.separator + mavenServerModuleName;

        apiJavaOutputDir = mavenApiModuleOutputDir + GeneratorConstant.JAVA_DIR;
        serverJavaOutputDir = mavenServerModuleOutputDir + GeneratorConstant.JAVA_DIR;

        serverResourcesOutputDir = mavenServerModuleOutputDir + GeneratorConstant.RESOURCES_DIR;
        serverMapperXmlOutputDir = mavenServerModuleOutputDir + GeneratorConstant.MAPPER_XML_DIR;

        // 设置java代码输出目录绝对路径
        globalConfig.setOutputDir(serverJavaOutputDir);

        // 是否覆盖文件
        globalConfig.setFileOverride(generatorProperties.isFileOverride());

        // 设置作者
        globalConfig.setAuthor(generatorProperties.getAuthor());

        if (StringUtils.isBlank(globalConfig.getServiceName())) {
            //  %sService 生成 UserService,%s 为占位符
            globalConfig.setServiceName(GeneratorConstant.SERVICE_NAME);
        }

        // 设置全局配置
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = generatorProperties.getDataSourceConfig();
        if (dataSourceConfig == null) {
            throw new GeneratorException("dataSourceConfig不能为空");
        }

        // 设置元数据自定义查询
        DbType dbType = dataSourceConfig.getDbType();
        if (DbType.MYSQL == dbType) {
            // 设置MySQL元数据自定义查询
            dataSourceConfig.setDbQuery(new ScpMySqlQuery());
        } else if (DbType.SQL_SERVER2005 == dbType || DbType.SQL_SERVER == dbType) {
            // 设置SQLServer元数据自定义查询
            dataSourceConfig.setDbQuery(new ScpSqlServerQuery());
        }

        // 设置数据源
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = mybatisPlusGeneratorConfig.getPackageConfig();
        packageConfig.setModuleName(moduleName);
        packageConfig.setParent(generatorProperties.getParentPackage());

        // 设置包信息
        autoGenerator.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategyConfig = mybatisPlusGeneratorConfig.getStrategyConfig();
        if (strategyConfig == null) {
            throw new GeneratorException("strategyConfig不能为空");
        }
        // 设置生成的表
        strategyConfig.setInclude(tableName);
        // 表前缀
        List<String> tablePrefixList = generatorProperties.getTablePrefix();
        if (CollectionUtils.isNotEmpty(tablePrefixList)) {
            strategyConfig.setTablePrefix(tablePrefixList.toArray(new String[]{}));
        }

        if (StringUtils.isBlank(strategyConfig.getSuperEntityClass())) {
            // 自定义继承的Entity类全称，带包名
            strategyConfig.setSuperEntityClass(GeneratorConstant.SUPER_ENTITY_CLASS);
        }
        if (StringUtils.isBlank(strategyConfig.getSuperControllerClass())) {
            // 自定义继承的Controller类全称，带包名
            strategyConfig.setSuperControllerClass(GeneratorConstant.SUPER_CONTROLLER_CLASS);
        }
        // 自定义继承的Service类全称，带包名
        strategyConfig.setSuperServiceClass(GeneratorConstant.SUPER_SERVICE_CLASS);
        // 自定义继承的ServiceImpl类全称，带包名
        strategyConfig.setSuperServiceImplClass(GeneratorConstant.SUPER_SERVICE_IMPL_CLASS);

        // 设置策略
        autoGenerator.setStrategy(strategyConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                List<TableInfo> tableInfos = this.getConfig().getTableInfoList();
                if (CollectionUtils.isEmpty(tableInfos)) {
                    throw new GeneratorException(tableName + "表不存在");
                }

                String finalModuleName = "";
                if (StringUtils.isNotBlank(moduleName)) {
                    finalModuleName = StringPool.DOT + moduleName;
                }
                TableInfo tableInfo = this.getConfig().getTableInfoList().get(0);
                String entityName = tableInfo.getEntityName();
                String entityObjectName = toCamel(entityName);
                String colonTableName = underlineToColon(tableName);

                String parentPackage = generatorProperties.getParentPackage();
                ProjectConfig projectConfig = generatorProperties.getProjectConfig();

                String superPageQueryClass = projectConfig.getSuperPageQueryClass();
                String idParamClass = projectConfig.getIdParamClass();
                String pagingClass = projectConfig.getPagingClass();
                String pageInfoClass = projectConfig.getPageInfoClass();
                String apiResultClass = projectConfig.getApiResultClass();
                String superPageOrderQueryClass = projectConfig.getSuperPageOrderQueryClass();

                GeneratorConfig generatorConfig = generatorProperties.getGeneratorConfig();

                GeneratorStrategy generatorStrategy = generatorConfig.getGeneratorStrategy();
                String generatorStrategyName = generatorStrategy.name();
                boolean paramValidation = generatorConfig.isParamValidation();
                boolean requiresPermissions = generatorConfig.isRequiresPermissions();

                String voClassName;
                String voObjectName;
                if (generatorStrategy == GeneratorStrategy.ALL) {
                    voClassName = entityName + GeneratorConstant.VO_SUFFIX;
                    voObjectName = entityObjectName + GeneratorConstant.VO_SUFFIX;
                } else {
                    voClassName = entityName;
                    voObjectName = entityObjectName;
                }

                boolean isImportVoPackage = false;
                if (generatorStrategy == GeneratorStrategy.ALL) {
                    isImportVoPackage = true;
                }

                String feignClientPackage = parentPackage + StringPool.DOT + moduleName + StringPool.DOT + GeneratorConstant.API + StringPool.DOT + GeneratorConstant.FEIGN;
                String feignClientFactoryPackage = parentPackage + StringPool.DOT + moduleName + StringPool.DOT + GeneratorConstant.API + StringPool.DOT + GeneratorConstant.FEIGN + StringPool.DOT + GeneratorConstant.FACTORY;
                String feignClientFallbackPackage = parentPackage + StringPool.DOT + moduleName + StringPool.DOT + GeneratorConstant.API + StringPool.DOT + GeneratorConstant.FEIGN + StringPool.DOT + GeneratorConstant.FALLBACK;

                Map<String, Object> map = new HashMap<>();
                // 查询参数包路径
                String pageQueryPackage = parentPackage + finalModuleName + StringPool.DOT + GeneratorConstant.API + StringPool.DOT + GeneratorConstant.QUERY;
                map.put("pageQueryPackage", pageQueryPackage);
                // 查询参数类路径
                map.put("pageQueryClass", pageQueryPackage + StringPool.DOT + entityName + GeneratorConstant.PAGE_QUERY);
                // 查询参数共公包路径
                map.put("superPageQueryClass", superPageQueryClass);
                map.put("superPageOrderQueryClass", superPageOrderQueryClass);
                // 查询参数共公包路径
                map.put("idParamClass", idParamClass);
                // 响应结果包路径
                String voPackage = parentPackage + finalModuleName + StringPool.DOT + GeneratorConstant.API + StringPool.DOT + GeneratorConstant.VO;
                map.put("voPackage", voPackage);
                // 响应结果类路径
                map.put("voClass", voPackage + StringPool.DOT + entityName + GeneratorConstant.VO_SUFFIX);
                // 实体对象名称
                map.put("entityObjectName", entityObjectName);
                // service对象名称
                map.put("serviceObjectName", entityObjectName + GeneratorConstant.SERVICE);
                // mapper对象名称
                map.put("mapperObjectName", entityObjectName + GeneratorConstant.MAPPER);
                // 主键ID列名
                map.put("pkIdName", pkIdName);
                // 主键ID驼峰名称
                map.put("pkIdCamelName", underlineToCamel(pkIdName));
                // 导入分页类
                map.put("pagingClass", pagingClass);
                map.put("pageInfoClass", pageInfoClass);
                // ApiResult
                map.put("apiResultClass", apiResultClass);
                // 代码生成策略
                map.put("generatorStrategy", generatorStrategyName);
                // 代码Validation校验
                map.put("paramValidation", paramValidation);
                // 冒号连接的表名称
                map.put("colonTableName", colonTableName);
                // 是否生成Shiro RequiresPermissions注解
                map.put("requiresPermissions", requiresPermissions);
                // 设置常量值
                map.put("pageQuery", GeneratorConstant.PAGE_QUERY);
                map.put("validatorAddPackage", GeneratorConstant.VALIDATOR_ADD_PACKAGE);
                map.put("validatorUpdatePackage", GeneratorConstant.VALIDATOR_UPDATE_PACKAGE);
                map.put("voSuffix", GeneratorConstant.VO_SUFFIX);
                map.put("voClassName", voClassName);
                map.put("voObjectName", voObjectName);
                map.put("isImportVoPackage", isImportVoPackage);
                map.put("generatorPageQuery", generatorConfig.isGeneratorPageQuery());
                map.put("pageListOrder", generatorConfig.isPageListOrder());
                map.put("swaggerTags", generatorConfig.isSwaggerTags());
                map.put("operationLog", generatorConfig.isOperationLog());
                map.put("module", moduleName);
                map.put("feignClientPackage", feignClientPackage);
                map.put("feignConfigPackage", GeneratorConstant.FEIGN_CONFIG_PACKAGE);
                map.put("feignClientFactoryPackage", feignClientFactoryPackage);
                map.put("feignClientFallbackPackage", feignClientFallbackPackage);
                map.put("serverName", generatorProperties.getServerName());
                this.setMap(map);
            }
        };
        List<FileOutConfig> fileOutConfigs = new ArrayList<>();

        GeneratorConfig generatorConfig = generatorProperties.getGeneratorConfig();

        String parentPackagePath = generatorProperties.getParentPackagePath();
        String finalJavaOutputDir = serverJavaOutputDir;
        String finalMapperXmlOutputDir = serverMapperXmlOutputDir;

        String modulePath = "";
        if (StringUtils.isNotBlank(moduleName)) {
            modulePath = File.separator + moduleName;
        }

        final String finalModulePath = modulePath;
        final String finalEntityModulePath = mavenApiModuleOutputDir;

        // /Users/geekidea/cloud/spring-cloud-plus/scp-example/scp-example-api/com/example/foobar/api/entity/%s.java
        String entityOutputPath = mavenApiModuleOutputDir + GeneratorConstant.JAVA_DIR + File.separator + parentPackagePath + modulePath + File.separator + GeneratorConstant.API_ENTITY_PACKAGE_PATH + File.separator + "%s" + StringPool.DOT_JAVA;
        // /Users/geekidea/cloud/spring-cloud-plus/scp-example/scp-example-server/com/example/foobar/mapper/%sMapper.xml
        String mapperXmlOutputPath = mavenServerModuleOutputDir + GeneratorConstant.RESOURCES_DIR + File.separator + GeneratorConstant.MAPPER_DIR + File.separator + "%s" + GeneratorConstant.MAPPER + StringPool.DOT_XML;
        // /Users/geekidea/cloud/spring-cloud-plus/scp-example/scp-example-api/com/example/foobar/param/%sPageParam.java
        String pageQueryOutputPath = mavenApiModuleOutputDir + GeneratorConstant.JAVA_DIR + File.separator + parentPackagePath + modulePath + File.separator + GeneratorConstant.API_QUERY_PACKAGE_PATH + File.separator + "%s" + GeneratorConstant.PAGE_QUERY + StringPool.DOT_JAVA;
        // /Users/geekidea/cloud/spring-cloud-plus/scp-example/scp-example-api/com/example/foobar/vo/%sVo.java
        String voOutputPath = mavenApiModuleOutputDir + GeneratorConstant.JAVA_DIR + File.separator + parentPackagePath + modulePath + File.separator + GeneratorConstant.API_VO_PACKAGE_PATH + File.separator + "%s" + GeneratorConstant.VO_SUFFIX + StringPool.DOT_JAVA;
        // /Users/geekidea/cloud/spring-cloud-plus/scp-example/scp-example-api/src/main/java/com/example/foobar/api/feign/%sClient.java
        String feignClientOutputPath = mavenApiModuleOutputDir + GeneratorConstant.JAVA_DIR + File.separator + parentPackagePath + modulePath + File.separator + GeneratorConstant.API_FEIGN_CLIENT_PACKAGE_PATH + File.separator + "%sClient" + StringPool.DOT_JAVA;
        String feignClientFactoryOutputPath = mavenApiModuleOutputDir + GeneratorConstant.JAVA_DIR + File.separator + parentPackagePath + modulePath + File.separator + GeneratorConstant.API_FEIGN_CLIENT_FACTORY_PACKAGE_PATH + File.separator + "%sClientFallbackFactory" + StringPool.DOT_JAVA;
        String feignClientFallbackOutputPath = mavenApiModuleOutputDir + GeneratorConstant.JAVA_DIR + File.separator + parentPackagePath + modulePath + File.separator + GeneratorConstant.API_FEIGN_CLIENT_FALLBACK_PACKAGE_PATH + File.separator + "%sClientFallback" + StringPool.DOT_JAVA;
        log.info("entityOutputPath = " + entityOutputPath);
        log.info("mapperXmlOutputPath = " + mapperXmlOutputPath);
        log.info("pageQueryOutputPath = " + pageQueryOutputPath);
        log.info("voOutputPath = " + voOutputPath);
        log.info("feignClientOutputPath = " + feignClientOutputPath);
        System.out.println("feignClientFactoryOutputPath = " + feignClientFactoryOutputPath);
        System.out.println("feignClientFallbackOutputPath = " + feignClientFallbackOutputPath);

        // 生成Entity
        if (generatorConfig.isGeneratorMapperXml()) {
            fileOutConfigs.add(new FileOutConfig(GeneratorConstant.ENTITY_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 指定Entity文件路径
                    return String.format(entityOutputPath, tableInfo.getEntityName());
                }
            });
        }

        // 生成Mapper XML
        if (generatorConfig.isGeneratorMapperXml()) {
            fileOutConfigs.add(new FileOutConfig(GeneratorConstant.MAPPER_XML_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 指定Mapper XML文件路径
                    return String.format(mapperXmlOutputPath, tableInfo.getEntityName());
                }
            });
        }

        // 自定义pageQuery模板
        if (generatorConfig.isGeneratorPageQuery()) {
            fileOutConfigs.add(new FileOutConfig(GeneratorConstant.PAGE_QUERY_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format(pageQueryOutputPath, tableInfo.getEntityName());
                }
            });
        }

        // 自定义vo模板
        if (generatorConfig.isGeneratorVo()) {
            fileOutConfigs.add(new FileOutConfig(GeneratorConstant.VO_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format(voOutputPath, tableInfo.getEntityName());
                }
            });
        }

        // 自定义feign client模板
        if (generatorConfig.isGeneratorFeignClient()) {
            fileOutConfigs.add(new FileOutConfig(GeneratorConstant.FEIGN_CLIENT_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format(feignClientOutputPath, tableInfo.getEntityName());
                }
            });
        }

        // 自定义feign factory模板
        if (generatorConfig.isGeneratorFeignClientFactory()) {
            fileOutConfigs.add(new FileOutConfig(GeneratorConstant.FEIGN_CLIENT_FACTORY_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format(feignClientFactoryOutputPath, tableInfo.getEntityName());
                }
            });
        }

        // 自定义feign fallback模板
        if (generatorConfig.isGeneratorFeignClientFallback()) {
            fileOutConfigs.add(new FileOutConfig(GeneratorConstant.FEIGN_CLIENT_FALLBACK_TEMPLATE_PATH) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format(feignClientFallbackOutputPath, tableInfo.getEntityName());
                }
            });
        }

        injectionConfig.setFileOutConfigList(fileOutConfigs);

        // 设置自定义配置
        autoGenerator.setCfg(injectionConfig);

        // 模版生成配置，设置为空，表示不生成
        TemplateConfig templateConfig = new TemplateConfig();
        // enttitty使用自定义输出
        templateConfig.setEntity(null);
        // xml使用自定义输出
        templateConfig.setXml(null);
        // 是否生成entity
        if (!generatorConfig.isGeneratorEntity()) {
            templateConfig.setEntity(null);
        }
        // 是否生成controller
        if (!generatorConfig.isGeneratorController()) {
            templateConfig.setController(null);
        }
        // 是否生成service
        if (!generatorConfig.isGeneratorService()) {
            templateConfig.setService(null);
        }
        // 是否生成serviceImpl
        if (!generatorConfig.isGeneratorServiceImpl()) {
            templateConfig.setServiceImpl(null);
        }
        // 是否生成mapper
        if (!generatorConfig.isGeneratorMapper()) {
            templateConfig.setMapper(null);
        }

        // 设置模板引擎
        autoGenerator.setTemplate(templateConfig);

        // 执行代码生成
        autoGenerator.execute();
    }

    /**
     * 下划线专程驼峰命名
     * sys_user --> sysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToCamel(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.underlineToCamel(underline);
        }
        return null;
    }

    /**
     * 下划线转换成帕斯卡命名
     * sys_user --> SysUser
     *
     * @param underline
     * @return
     */
    public static String underlineToPascal(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(underline));
        }
        return null;
    }

    /**
     * 下划线转换成冒号连接命名
     * sys_user --> sys:user
     *
     * @param underline
     * @return
     */
    public static String underlineToColon(String underline) {
        if (StringUtils.isBlank(underline)) {
            return null;
        }
        String string = underline.toLowerCase();
        return string.replaceAll("_", ":");
    }

    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    public static String toCamel(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

}
