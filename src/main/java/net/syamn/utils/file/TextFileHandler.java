/**
 * SakuraUtils - Package: net.syamn.utils.file
 * Created: 2012/12/23 18:37:54
 */
package net.syamn.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * TextFileHandler (TextFileHandler.java)
 * @author syam(syamn)
 */
public class TextFileHandler {
    private final File file;
    
    public TextFileHandler(File file) throws IOException {
        if (file == null){
            throw new IllegalArgumentException("file must be not null!");
        }
        
        this.file = file;
        if (!file.exists()) {
            file.createNewFile();
        }
    }
    
    public TextFileHandler(String path) throws IOException {
        this(new File(path));
    }

    /**
     * ファイルを行ごとに全取得
     * 
     * @return 行ごとのList&lt;String&gt;
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<String> readLines() throws FileNotFoundException, IOException {
        BufferedReader inputStream = null;
        List<String> data = new ArrayList<String>();
        try {
            inputStream = new BufferedReader(new FileReader(file));
            String l;

            while ((l = inputStream.readLine()) != null) {
                data.add(l);
            }
            /* 最終処理 */
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    /**
     * ファイルを書き込み
     * 
     * @param data
     * @throws IOException
     */
    public void writeLines(List<String> data) throws IOException {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileWriter(file));
            while (!data.isEmpty()) {
                outputStream.println(data.remove(0));
            }
            /* 最終処理 */
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * ファイルの最終行に追記
     * 
     * @param line
     * @throws IOException
     */
    public void appendLine(String line) throws IOException {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileWriter(file, true));
            outputStream.println(line);
            /* 最終処理 */
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}