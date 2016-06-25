package common;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileKit extends com.jfinal.kit.FileKit
{

    /**
     * 把内容写到文件中
     * @param filename 文件路径，包括文件名
     * @param content 文件内容
     */
    public static void write(String filename, String content)
    {
        try
        {
            FileUtils.writeStringToFile(new File(filename), content, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
