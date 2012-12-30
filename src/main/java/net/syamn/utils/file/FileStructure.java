/**
 * SakuraUtils - Package: net.syamn.utils.file
 * Created: 2012/12/23 18:44:36
 */
package net.syamn.utils.file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * FileStructure (FileStructure.java)
 * @author syam(syamn)
 */
public class FileStructure {
    private static final String LOGGER_NAME = "Minecraft";
    
    /**
     * ディレクトリを作成する
     * @param dir
     */
    public static void createDir(final File dir){
        if (dir.isDirectory()){
            return;
        }
        if (!dir.mkdir()){
            Logger.getLogger(LOGGER_NAME).warning("Can't create directory: " + dir.getName());
        }
    }
    
    /**
     * ファイルを srcPath から destpath にFileChannel#transferToを使ってコピーする
     * @param srcPath コピー元のファイルパス
     * @param destPath コピー先のファイルパス
     * @throws IOException
     */
    public static void copyTransfer(String srcPath, String destPath) throws IOException{
        FileChannel srcChannel = null, destChannel = null;
        try {
            srcChannel = new FileInputStream(srcPath).getChannel();
            destChannel = new FileOutputStream(destPath).getChannel();
            
            srcChannel.transferTo(0, srcChannel.size(), destChannel);
        } finally {
            try{ srcChannel.close(); }catch(Exception ex){ ex.printStackTrace(); }
            try{ destChannel.close(); }catch(Exception ex){ ex.printStackTrace(); }
        }
    }
    
    /**
     * Jarファイル内のリソースを展開する
     * @param from
     * @param to
     * @param force
     * @param useBuffer
     * @param clazz
     */
    public static void extractResource(String from, File to, boolean force, boolean useBuffer, JavaPlugin plugin/*Class<? extends JavaPlugin> clazz*/){
        File of = to;
        
        // If to path is directory, cas to File. return if not file or directory
        if (to.isDirectory()){
            String fileName = new File(from).getName();
            of = new File(to, fileName);
        } else if (!of.isFile()) {
            Logger.getLogger(LOGGER_NAME).warning("Not a file: " + of);
            return;
        }
        
        // If file exist, check force flag
        if (of.exists() && !force){
            return;
        }
        
        OutputStream out = null;
        InputStream in = null;
        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        DataInputStream dis = null;
        
        try {
            // get inside jar resource uri
            URL res = plugin.getClass().getResource(from);
            if (res == null){
                Logger.getLogger(LOGGER_NAME).warning("Can't find " + from + " in plugin Jar file");
                return;
            }
            URLConnection resConn = res.openConnection();
            resConn.setUseCaches(false);
            
            // input resource
            in = resConn.getInputStream();
            if (in == null){
                Logger.getLogger(LOGGER_NAME).warning("Can't get input stream from " + res);
                return;
            }
            
            // output resource
            if (useBuffer) {
                out = new FileOutputStream(of);
                byte[] buf = new byte[1024]; // buffer size
                int len = 0;
                while ((len = in.read(buf)) >= 0){
                    out.write(buf, 0, len);
                }
            }else{
                reader = new InputStreamReader(in, "UTF-8");
                writer = new OutputStreamWriter(new FileOutputStream(of)); // not specify output encode
                int text;
                while ((text = reader.read()) != -1){
                    writer.write(text);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            // close stream
            try{
                if (out != null) out.close();
            }catch (Exception ignored){}
            try{
                if (in != null) in.close();
            }catch (Exception ignored){}
            try {
                if (reader != null) reader.close();
            }catch (Exception ignored){}
            try{
                if (writer != null) writer.close();
            }catch (Exception ignored){}
        }
    }
    
    /**
     * Jarファイル内のリソースを展開する
     * @param from
     * @param to
     * @param force
     * @param clazz
     */
    public static void extractResource(String from, File to, boolean force, JavaPlugin plugin){
        extractResource(from, to, force, false, plugin);
    }
}
