package com.icode.util;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
public class TestIO
{
    public static void main(String[] args)
    {
        try
        {
            File source = new File("F:/workspace-64/frame/src/web-tmpl/WebContent");
            File destination = new File("D:/destinationDir");
            if (!destination.exists())
            {
                destination.mkdirs();
            }
            FileUtils.copyDirectoryToDirectory(source, destination);
//            File[] files = source.listFiles();
//            
//            for (File file : files)
//            {
//                FileUtils.copyFileToDirectory(file, destination);
//               
//            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}