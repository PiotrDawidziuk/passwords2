package pl.piotrdawidziuk.passwords2.lib;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileLoader implements ResourceLoaderAware
{
    private ResourceLoader resourceLoader;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public File get(String location) throws IOException{
        return resourceLoader.getResource("classpath:" + location).getFile();
    }
}