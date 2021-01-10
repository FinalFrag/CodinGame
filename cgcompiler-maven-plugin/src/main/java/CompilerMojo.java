import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mojo(name = "compile")
public class CompilerMojo extends AbstractMojo {

    public void execute() throws MojoExecutionException {
        File directoryToCompile = new File("src/main/java");
        File[] filesToCompile = directoryToCompile.listFiles(file -> !file.isDirectory());
        Set<String> imports = new HashSet<>();
        List<String> code = new ArrayList<>();

        for (File file : filesToCompile) {
            try {
                for (String line : Files.readAllLines(file.toPath())) {
                    if (line.startsWith("import ")) {
                        imports.add(line);
                    } else if (line.startsWith("public class ")) {
                        // Remove public from classes
                        code.add(line.substring("public ".length()));
                    } else {
                        code.add(line);
                    }
                }
            } catch (IOException e) {
                throw new MojoExecutionException("", e);
            }
        }

        String output = String.join("\n", imports);
        output += String.join("\n", code);

        try {
            FileWriter fileWriter = new FileWriter("../Solution.java");
            fileWriter.write(output);
            fileWriter.flush();
        } catch (IOException e) {
            throw new MojoExecutionException("", e);
        }

        getLog().info("Compiled " + filesToCompile.length + " files into Solution.java");
    }
}