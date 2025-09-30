package com.jefferson.org.utils;

import java.net.URL;

public class PathFXML 
{
    public static URL getPath(String nomeDoArquivo) 
    {
        return PathFXML.class.getResource("/com/jefferson/org/view/" + nomeDoArquivo);
    }
}