package apps.sami.wallpaperv2;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelLiveWallpaper {
    private int id;
    private String pictures;
    private int id1;
    private String quality;
    private String file_type;
    private String link1;

    public ModelLiveWallpaper(int id, String pictures, int id1, String quality, String file_type, String link1) {
        this.id = id;
        this.pictures = pictures;
        this.id1 = id1;
        this.quality = quality;
        this.file_type = file_type;
        this.link1 = link1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }
}

