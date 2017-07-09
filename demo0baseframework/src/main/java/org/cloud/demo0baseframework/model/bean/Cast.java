package org.cloud.demo0baseframework.model.bean;

/**
 * @author d05660ddw
 * @version 1.0 2016/9/22
 */

public class Cast {
    private String id;
    private String name;
    private String alt;
    private Avatar avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return name;
    }
}
