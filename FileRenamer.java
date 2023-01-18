import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileRenamer {
    public static void main(String[] args) throws IOException {
        Path sourceFolder = Paths.get("D:/files");
        Path destinationFolder = Paths.get("D:/files_copy");
    
        Files.walkFileTree(sourceFolder, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = destinationFolder.resolve(sourceFolder.relativize(dir));
                if (!Files.exists(targetDir)) {
                    Files.createDirectory(targetDir);
                }
                return FileVisitResult.CONTINUE;
            }
    
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetFile = destinationFolder.resolve(sourceFolder.relativize(file));
                String fileName = targetFile.getFileName().toString();
                String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".lolabhiq";
                Files.copy(file, targetFile.resolveSibling(newFileName), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}    
