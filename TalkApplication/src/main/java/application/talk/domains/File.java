package application.talk.domains;

import java.io.FileOutputStream;
import java.time.LocalDate;

public class File extends BaseEntity {
    private String _name;
    private long _size;
    private LocalDate _createdDate;
    private Type _type;
    private String _filePath;

    public enum Type {
        IMAGE, VIDEO, AUDIO;
    }

    public File(long size, Type type) {
        super();
        _size = size;
        _createdDate = LocalDate.now();
        _type = type;
        _filePath = generateFilePath();
    }

    private String generateFilePath() {
        String baseDirectory = "src/test/java/files/";
        String typeFolder;

        switch (_type) {
            case IMAGE: {
                typeFolder = "images";
                break;
            }
            case VIDEO: {
                typeFolder = "videos";
                break;
            }
            case AUDIO: {
                typeFolder = "audios";
                break;
            }
            default: {
                throw new IllegalArgumentException("Incorrect File Type!");
            }
        }

        return baseDirectory + typeFolder + "/" + _name;
    }

    public void writeFileFromByte(byte[] bytes) {
        try (FileOutputStream fos = new FileOutputStream(_filePath)) {
            fos.write(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return _name;
    }

    public String setName(String name) {
        return _name = name;
    }

    public long getSize() {
        return _size;
    }

    public LocalDate getDate() {
        return _createdDate;
    }

    public Type getType() {
        return _type;
    }

    public String getFilePath() {
        return _filePath;
    }

}
