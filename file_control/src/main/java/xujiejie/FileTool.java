package xujiejie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTool {
    
    public List<String> list = new ArrayList<String>();

    public FileTool() {
        getAllRootFile();
    }

    private void getAllRootFile() {
        File[] roots = File.listRoots();
        for (File file : roots) {getallFile(file);}
    }

    private void getallFile(File f) {
	    list.add(f.getAbsolutePath());
	    if (f.isDirectory()) {
		    File[] files = f.listFiles();
            if (files != null) {for (File file : files) {getallFile(file);}}
    	}
    }

    public String research(String str) throws IOException {
        File f = new File("1.txt");
        FileOutputStream ous = new FileOutputStream(f);
        if(!f.exists()) {f.createNewFile();}
        StringBuilder content = new StringBuilder();
        for (String s : list) {if (s.contains(str)) {content.append(s).append("\n");ous.write((s + "\r\n").getBytes());}}
        ous.close();
        return content.toString();
    }

}
