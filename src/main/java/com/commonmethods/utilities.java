package com.commonmethods;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class utilities {
   public static File createFolder(String foldername) {
		String projectDir = System.getProperty("user.dir");
		System.out.println(projectDir);

		File folder = new File(projectDir + File.separator + "TestLogs" + File.separator + foldername);

		if (folder.exists()) {
			System.out.println(folder + " already exists in current project " + projectDir);
		} else {
			System.out.println(folder + " doesnt exists in current project " + projectDir);
			if (folder.mkdirs()) {
				System.out.println(folder + " created in current project " + projectDir);
			} else {
				System.out.println("Failed to create directory: " + folder.getAbsolutePath());
			}
		}
		return folder;
	}
	public static Map<String, Object> readYamlData(String fileName) {
		Yaml yaml = new Yaml();
		Map<String, Object> map;
		try {
			FileInputStream yamlFile = new FileInputStream(fileName);
			map = yaml.load(yamlFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return map;
	}




}





