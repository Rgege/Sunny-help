package org.blue.helper.test.util;

import org.blue.helper.StringHelper.common.exception.HelperException;
import org.blue.helper.StringHelper.utils.CmdComandUtil;
import org.blue.helper.StringHelper.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateMybatisConfig {//
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateMybatisConfig.class);
    private static final String rootPart = "  </context>\n" +
            "</generatorConfiguration>";
    private static final String xmlUrl = "D:\\XR@aa.com\\IdeaWorkspace\\sun\\src\\main\\resources\\GeneratorConfig.xml";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>input [1:createPersistence   |  2:copyToYourProject]");
        int i=sc.nextInt();
        System.out.println(i);
        run(i,"jvm_oeder","JvmOeder");
    }

    private static void run(int i,String tableName,String objName){
        CreateMybatisConfig tool = new CreateMybatisConfig();
        CreateMybatisConfig.Entity entity = tool.creatEntity("jdbc:mysql://localhost:3306/test2", "root", null);
        entity.setTbName(tableName);
        entity.setObjName(objName);
        entity.setDriverClass("com.mysql.jdbc.Driver");
        entity.setLocation("D:/XR@aa.com/devsoft/mvn/repository/mysql/mysql-connector-java/5.1.46/mysql-connector-java-5.1.46.jar");
        entity.setJavaModelPath("org.blue.helper.StringHelper.persistence.entity.model");
        entity.setSqlMapPath("org.blue.helper.StringHelper.persistence");
        if (i==1){
            createPersistence(entity, "D:\\XR@aa.com\\IdeaWorkspace\\sun");
        }else if (i==2){
            copyToYourProject(entity, "D:\\XR@aa.com\\IdeaWorkspace\\sun");
        }
    }

    public static void createPersistence(Entity entity, String projectUrl) {
        String[] paths = {entity.getJavaModelPath(), entity.getSqlMapPath()};
        createPackageStructure(projectUrl, paths);

        boolean success = creatConfigXml(entity);
        if (success) {
            LOGGER.info("Start executing the Maven command");
            try {
                CmdComandUtil.mvnBuild(projectUrl);
                LOGGER.info("Executing the Maven command end");
            } catch (HelperException e) {
                LOGGER.error("Executing the Maven command  failed", e);
            }
        }
        LOGGER.info("Finish");
    }

    private static boolean creatConfigXml(Entity entity) {
        LOGGER.info("Start building the XML file");
        String topPart = getTopPart(entity);
        StringBuilder xmlBuilder = new StringBuilder(topPart);
        String title = "<table  tableName=\"" + entity.getTbName() + "\" domainObjectName=\"" + entity.getObjName() + "\">\n";
        xmlBuilder.append(title);
        //加载驱动
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //链接数据库
            conn = DriverManager.getConnection(entity.getUrl(), entity.getUserName(), entity.getPwd());
            String sql = "DESC " + entity.getTbName() + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("show full columns from " + entity.getTbName());
            while (rs.next()) {
                String filed = rs.getString("Field");
                String type = rs.getString("Type");
                if (type.indexOf("(") != -1) {
                    type = type.substring(0, type.indexOf("("));
                }
                String line = "\t\t<columnOverride column=\"" + filed + "\" property=\"" + humpNamed(filed) + "\" javaType=\"" + JavaType.getType(type) + "\"  jdbcType=\"" + JdbcType.getType(type) + "\" />\n";
                xmlBuilder.append(line);
            }
        } catch (ClassNotFoundException e1) {
            LOGGER.error("An Exception occurred while building the XML file , build failed", e1);
            return false;
        } catch (SQLException e) {
            LOGGER.error("An Exception occurred while building the XML file , build failed", e);
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String end = "</table>\n";
        xmlBuilder.append(end);
        xmlBuilder.append(rootPart);
        try {
            writeFile(xmlBuilder.toString());
            LOGGER.info("The XML file was successfully built");
            return true;
        } catch (IOException e) {
            LOGGER.error("An Exception occurred while building the XML file , build failed", e);
            return false;
        }
    }

    private static void copyToYourProject(Entity entity,String projectUrl){//Example.java
        final String common = "\\src\\main\\java\\";
        String javaModel=entity.getJavaModelPath().replaceAll("\\.", "\\\\")+"\\"+entity.getObjName()+".java";
        String javaExample=entity.getJavaModelPath().replaceAll("\\.", "\\\\")+"\\"+entity.getObjName()+"Example.java";
        String mapperIntf=entity.getSqlMapPath().replaceAll("\\.", "\\\\")+"\\"+entity.getObjName()+"Mapper.java";
        String mapperXml=entity.getSqlMapPath().replaceAll("\\.", "\\\\")+"\\"+entity.getObjName()+"Mapper.xml";
        Map<String,String> map=new HashMap<String, String>();
        StringBuilder source = new StringBuilder(projectUrl).append(common);
        StringBuilder local = new StringBuilder(FileUtils.getProjectFilePath()).append(common);

        map.put(new StringBuilder(source).append(javaModel).toString(),new StringBuilder(local).append(javaModel).toString());
        map.put(new StringBuilder(source).append(javaExample).toString(),new StringBuilder(local).append(javaExample).toString());
        map.put(new StringBuilder(source).append(mapperIntf).toString(),new StringBuilder(local).append(mapperIntf).toString());
        map.put(new StringBuilder(source).append(mapperXml).toString(),new StringBuilder(local).append(mapperXml).toString());
        FileUtils.copyAll(map);
    }

    /**
     * 根据包路径构建包结构
     *
     * @param packageUrl
     */
    public static void createPackageStructure(String projectUrl, String... packageUrls) {
        final String common = "\\src\\main\\java\\";
        StringBuilder sb = new StringBuilder();
        for (String packageUrl : packageUrls) {
            String fileUrl = packageUrl.replaceAll("\\.", "\\\\");
            String path = sb.append(projectUrl).append(common).append(fileUrl).toString();
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                delAllFile(path);
            }
            sb.setLength(0);
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
        }
        return flag;
    }

    private static void writeFile(String line) throws IOException {
        File file = new File(xmlUrl);

        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(line);
            bw.flush();
        }

    }

    public Entity creatEntity() {
        return new Entity();
    }

    public Entity creatEntity(String url, String user) {
        Entity entity = this.creatEntity();
        entity.setUrl(url);
        entity.setUserName(user);
        return entity;
    }

    public Entity creatEntity(String url, String user, String password) {
        Entity entity = this.creatEntity();
        entity.setUrl(url);
        entity.setUserName(user);
        entity.setPwd(password);
        return entity;
    }

    private static String humpNamed(String str) {
        if (str == null) return null;
        String rsp = "";
        if (str.indexOf("_") != -1) {
            String[] s = str.split("_");
            StringBuilder sb = new StringBuilder(s[0]);
            sb.append(initcap(s[1]));
            rsp = sb.toString();
        } else rsp = str;
        return rsp;
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    private static String getTopPart(Entity entity) {
        String pwd = entity.getPwd() != null ? "\" password=\"" + entity.getPwd() : "";
        String topPart = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE generatorConfiguration PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\" \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\" >\n" +
                "<generatorConfiguration >\n" +
                "  <classPathEntry location=\"" + entity.getLocation() + "\" />\n" +
                "  <context id=\"context1\" >\n" +
                "    <jdbcConnection driverClass=\"" + entity.getDriverClass() + "\" connectionURL=\"" + entity.getUrl() + "\" userId=\"" + entity.getUserName() + pwd + "\" />\n" +
                "    <javaModelGenerator  targetPackage=\"" + entity.getJavaModelPath() + "\" targetProject=\"src/main/java\" />\n" +
                "    <sqlMapGenerator targetPackage=\"" + entity.getSqlMapPath() + "\" targetProject=\"src/main/java\" />\n" +
                "    <javaClientGenerator targetPackage=\"" + entity.getSqlMapPath() + "\" targetProject=\"src/main/java\" type=\"XMLMAPPER\" ></javaClientGenerator>\n";
        return topPart;
    }

    class Entity {
        private String url;
        private String userName;
        private String pwd;

        private String tbName;
        private String objName;

        private String location;
        private String driverClass;
        private Entity entity;
        private String javaModelPath;
        private String sqlMapPath;


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getTbName() {
            return tbName;
        }

        public void setTbName(String tbName) {
            this.tbName = tbName;
        }

        public String getObjName() {
            return objName;
        }

        public void setObjName(String objName) {
            this.objName = objName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDriverClass() {
            return driverClass;
        }

        public void setDriverClass(String driverClass) {
            this.driverClass = driverClass;
        }

        public Entity getEntity() {
            return entity;
        }

        public void setEntity(Entity entity) {
            this.entity = entity;
        }

        public String getJavaModelPath() {
            return javaModelPath;
        }

        public void setJavaModelPath(String javaModelPath) {
            this.javaModelPath = javaModelPath;
        }

        public String getSqlMapPath() {
            return sqlMapPath;
        }

        public void setSqlMapPath(String sqlMapPath) {
            this.sqlMapPath = sqlMapPath;
        }

    }

    enum JdbcType {
        bigint_type("bigint", "BIGINT"),
        varchar_type("varchar", "VARCHAR"),
        double_type("double", "DOUBLE"),
        int_type("int", "INTEGER"),
        date_type("datetime", "DATE"),
        text_type("text","LONGVARCHAR")
        ;
        String org;
        String show;

        JdbcType(String org, String show) {
            this.org = org;
            this.show = show;
        }

        public static String getType(String org) {
            for (JdbcType jdbcType : JdbcType.values()) {
                if (jdbcType.getOrg().equals(org)) {
                    return jdbcType.getShow();
                }
            }
            return null;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }
    }

    enum JavaType {
        bigint_type("bigint", "Long"),
        varchar_type("varchar", "String"),
        double_type("double", "Double"),
        int_type("int", "Integer"),
        date_type("datetime", "java.sql.Timestamp"),
        text_type("text","String");
        String org;
        String show;

        JavaType(String org, String show) {
            this.org = org;
            this.show = show;
        }

        public static String getType(String org) {
            for (JavaType javaType : JavaType.values()) {
                if (javaType.getOrg().equals(org)) {
                    return javaType.getShow();
                }
            }
            return null;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }
    }
}