package com.jackshenorion.smarts.generator;

import com.jackshenorion.smarts.generator.cfg.Cfg;
import com.jackshenorion.smarts.generator.cfg.Prop;
import com.jackshenorion.smarts.util.io.IOUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class EnxtCfgGenerator {
    public final static String enxtTemplateFile = "templates/enxt-template.vm";
    public final static String outputDir = System.getProperty("user.home") + "/Documents/Temp/gen";

    public static void main(String[] args) throws Exception {
        new EnxtCfgGenerator().generate();
    }

    public void generate() throws Exception {
        List<Prop> props = new ArrayList<>();

        props.add(new Prop("equ.1201.10", "1201", "10"));
        props.add(new Prop("equ.1202.10", "1202", "10"));
        props.add(new Prop("equ.1203.10", "1203", "10"));
        props.add(new Prop("equ.1204.10", "1204", "10"));
        props.add(new Prop("equ.1205.10", "1205", "10"));
        props.add(new Prop("etf.2201.20", "2201", "20"));
        props.add(new Prop("etf.2202.20", "2202", "20"));
        props.add(new Prop("fxi.3201.30", "3201", "30"));
        props.add(new Prop("war.4201.40", "4201", "40"));
        props.add(new Prop("war.4202.40", "4202", "40"));
        props.add(new Prop("war.4203.40", "4203", "40"));
        props.add(new Prop("war.4204.40", "4204", "40"));
        props.add(new Prop("war.4205.40", "4205", "40"));
        Cfg cfg = new Cfg();
        cfg.setProps(props);

        Template template = loadTemplate(enxtTemplateFile);
        String s = getStringFromTemplate(template, cfg);
        String filePath = outputDir + "/" + "SmartsControl.cfg";
        writeFile(filePath, s);

    }
    private void writeFile(String filePath, String pojoString) {
        try {
            System.out.println("Write file " + filePath + " successfully!");
            IOUtil.writeTextFile(filePath, pojoString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringFromTemplate(Template t, Cfg cfg) {
        VelocityContext ctx = new VelocityContext();
        ctx.put("smarts", cfg);
        ctx.put("version", "aaaaa");
        ctx.put("date", "dddddd");
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        return sw.toString();
    }

    private Template loadTemplate(String templateFile) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        return ve.getTemplate(templateFile);
    }


}
